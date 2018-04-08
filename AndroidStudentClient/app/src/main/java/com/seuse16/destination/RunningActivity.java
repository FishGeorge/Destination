package com.seuse16.destination;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RunningActivity extends AppCompatActivity {
    private boolean isRunnning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        // 元件注册
        TextView Distance = findViewById(R.id.runningdistance);
        TextView TimepKm = findViewById(R.id.timepkm);
        TextView Time = findViewById(R.id.totaltime);
        TextView Cost = findViewById(R.id.calorie);
        final Button btn_pause = findViewById(R.id.btn_pause);
        final Button btn_continue = findViewById(R.id.btn_continue);
        final Button btn_finish = findViewById(R.id.btn_finish);
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
}