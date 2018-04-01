package com.seuse16.destination;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Timer timer = new Timer();    // 创建Timer对象，用于设置启动界面显示的时间
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 从启动界面跳转到日历主界面
                startActivity(new Intent(InitialActivity.this, LoginActivity.class));
                finish();   // 关闭启动界面
            }
        };
        timer.schedule(timerTask, 1000); // 设置显示启动界面1秒后，跳转
    }
}
