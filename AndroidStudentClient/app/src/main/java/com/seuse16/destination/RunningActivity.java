package com.seuse16.destination;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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
        if((int) speed_sec / 60>99)
            text_TimepKm.setText("--:--   ");
            else
        text_TimepKm.setText(String.format("%02d:%02d", (int) speed_sec / 60, ((int) speed_sec) % 60));
        // 公里

        // 卡路里消耗

    }

    @SuppressLint({"SimpleDateFormat", "DefaultLocale"})
    private Intent finish_running() {
        RunningRecord.put("time", String.format("%02d_%02d_%02d", hour, minute, second));
        RunningRecord.put("distance", (int) (Double.valueOf(String.valueOf(text_Distance.getText())) * 1000));
        RunningRecord.put("cost", Integer.valueOf(String.valueOf(text_Cost.getText())));
        // 页面跳转
        Intent intent_login = new Intent(RunningActivity.this, MainActivity.class);
        Bundle login = new Bundle();
        login.putSerializable("SportsRecord", new SerializableMap(RunningRecord));
        intent_login.putExtras(login);
        return intent_login;
    }
}