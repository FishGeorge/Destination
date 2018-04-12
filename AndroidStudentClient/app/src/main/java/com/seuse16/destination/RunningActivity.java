package com.seuse16.destination;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class RunningActivity extends AppCompatActivity {
    private Map<String, Object> RunningRecord = new HashMap<>();
    private boolean isRunnning = true;
    int hour = 0, minute = 0, second = 0;
    private int step_count = 0;

    Handler mhandler;
    TimerTask task = new TimerTask() {
        public void run() {
            Message message = mhandler.obtainMessage();
            message.what = 1;
            mhandler.sendMessage(message);
        }
    };

    TextView text_Distance;
    TextView text_TimepKm;
    TextView text_Time;
    TextView text_Cost;

    private SensorManager sensorManager;
    private Sensor stepCounter;//步伐总数传感器
    private Sensor stepDetector;//单次步伐传感器
    private SensorEventListener stepCounterListener;//步伐总数传感器事件监听器
    private SensorEventListener stepDetectorListener;//单次步伐传感器事件监听器
    private SimpleDateFormat simpleDateFormat;

    @SuppressLint({"HandlerLeak", "SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        // 元件注册
        text_Distance = findViewById(R.id.runningdistance);
        text_TimepKm = findViewById(R.id.timepkm);
        text_Time = findViewById(R.id.totaltime);
        text_Cost = findViewById(R.id.calorie);
        final Button btn_pause = findViewById(R.id.btn_pause);
        final Button btn_continue = findViewById(R.id.btn_continue);
        final Button btn_finish = findViewById(R.id.btn_finish);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器系统服务
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);//获取计步总数传感器
        stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);//获取单次计步传感器
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        // 计步传感器初始化与注册
        initListener();
        registerSensor();
        // 写入开始时间及地点？
        RunningRecord.put("date", new SimpleDateFormat("yyyy_MM_dd").format(new Date()));
        RunningRecord.put("start_time", new SimpleDateFormat("HH_mm_ss").format(new Date()));
        RunningRecord.put("location", "梅园田径场");
        // 时间++
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1 && isRunnning) {
                    Running_ChangeText();
                }
                super.handleMessage(msg);
            }
        };
        Running_Timer_1sec();
        // pause button
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 此处调用暂停函数
                isRunnning = false;
                unregisterSensor();
                // 使暂停按钮不可见
                btn_pause.setVisibility(View.INVISIBLE);
                // 使结束和继续按钮可见
                btn_continue.setVisibility(View.VISIBLE);
                btn_finish.setVisibility(View.VISIBLE);
            }
        });
        // continue button
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 此处调用继续函数
                isRunnning = true;
                registerSensor();
                // 使结束和继续按钮不可见
                btn_continue.setVisibility(View.INVISIBLE);
                btn_finish.setVisibility(View.INVISIBLE);
                // 使暂停按钮可见
                btn_pause.setVisibility(View.VISIBLE);
            }
        });
        // finish button
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 此处调用结束函数
                startActivity(finish_running());
                // 退出activity
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            System.out.println("返回键执行！");
        }
        //
        if (isRunnning)
            Toast.makeText(getApplicationContext(), "还在计时呢！", Toast.LENGTH_SHORT).show();
        if (!isRunnning)
            Toast.makeText(getApplicationContext(), "要放弃本次记录吗？", Toast.LENGTH_SHORT).show();
        // 返回键无效化
        return false;
    }

    // 计时函数，单位s
    private void Running_Timer_1sec() {
        Timer timer = new Timer(true);
        timer.schedule(task, 1000, 1000);
    }

    // 数据显示变动函数
    @SuppressLint("DefaultLocale")
    private void Running_ChangeText() {
        // 时间
        second++;
        if (second == 60) {
            minute++;
            second = 0;
        }
        if (minute == 60) {
            hour++;
            minute = 0;
        }
        text_Time.setText(String.format("%02d:%02d:%02d", hour, minute, second));
        // 配速
        double speed_sec = (hour * 3600 + minute * 60 + second) / Double.valueOf(String.valueOf(text_Distance.getText()));
        if ((int) speed_sec / 60 > 99)
            text_TimepKm.setText("--'--''  ");
        else
            text_TimepKm.setText(String.format("%02d'%02d''", (int) speed_sec / 60, ((int) speed_sec) % 60));
        // 公里，通过计步计算里程，曲线救国，嘻嘻
        int distance_m = (int) (0.80 * step_count);
        int distance_km = distance_m / 1000;
        int distance_100m = distance_m / 100;
        int distance_10m = distance_m / 10;
        text_Distance.setText(String.format("%01d.%02d", distance_km, distance_10m % 100));
        // 卡路里消耗
        // 跑步热量（kcal）＝体重（kg）×运动时间（小时）×指数K
        // 指数K＝30÷速度（分钟400米）
        int cal = (int) (1000 * 60 * (hour + minute / 60) * 30 / (speed_sec / 60 / 2.5));
        int kcal = cal / 1000;
        int Ten_cal = cal / 10;
        text_Cost.setText(String.format("%01d.%02d", kcal, Ten_cal % 100));
    }

    @SuppressLint({"SimpleDateFormat", "DefaultLocale"})
    private Intent finish_running() {
        RunningRecord.put("time", String.format("%02d_%02d_%02d", hour, minute, second));
        RunningRecord.put("distance", (int) (Double.valueOf(String.valueOf(text_Distance.getText())) * 1000));
        RunningRecord.put("cost", (int) (Double.valueOf(String.valueOf(text_Cost.getText())) * 1));
        // 页面跳转
        Intent intent_login = new Intent(RunningActivity.this, MainActivity.class);
        Bundle login = new Bundle();
        login.putSerializable("SportsRecord", new SerializableMap(RunningRecord));
        intent_login.putExtras(login);
        return intent_login;
    }

//    protected void initData() {
//        isSupportStepCounter.setText("是否支持StepCounter:"+
//                String.valueOf(getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)));
//        isSupportStepDetector.setText("是否支持StepDetector:"+
//                String.valueOf(getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)));
//    }

    protected void initListener() {
        stepCounterListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e("Counter-SensorChanged", event.values[0] + "---" + event.accuracy + "---" + event.timestamp);
//                stepCounterText.setText("总步伐计数:"+event.values[0]);
//                text_Cost.setText(String.valueOf(event.values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.e("Counter-Accuracy", sensor.getName() + "---" + accuracy);

            }
        };

        stepDetectorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e("Detector-SensorChanged", event.values[0] + "---" + event.accuracy + "---" + event.timestamp);
//                stepDetectorText.setText("当前步伐计数:"+event.values[0]);
//                stepDetectorTimeText.setText("当前步伐时间:"+simpleDateFormat.format(event.timestamp/1000000));
//                text_Cost.setText(String.valueOf(event.values[0]));
                step_count++;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.e("Detector-Accuracy", sensor.getName() + "---" + accuracy);

            }
        };
    }

    private void registerSensor() {
        //注册传感器事件监听器
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER) &&
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)) {
            sensorManager.registerListener(stepDetectorListener, stepDetector, SensorManager.SENSOR_DELAY_FASTEST);
            sensorManager.registerListener(stepCounterListener, stepCounter, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    private void unregisterSensor() {
        //解注册传感器事件监听器
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER) &&
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)) {
            sensorManager.unregisterListener(stepCounterListener);
            sensorManager.unregisterListener(stepDetectorListener);
        }
    }
}