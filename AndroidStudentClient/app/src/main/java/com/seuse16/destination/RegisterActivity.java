package com.seuse16.destination;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toolbar;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 元件注册
        Toolbar toolbar=findViewById(R.id.toolbar_register);
        final EditText account = findViewById(R.id.edit_account_r);
        final EditText password = findViewById(R.id.edit_password_r);
        final EditText password_repeat = findViewById(R.id.edit_password_r2);
        final EditText nickname = findViewById(R.id.edit_nickname);
        final Switch gender = findViewById(R.id.switch_gender);
        // 将toolbar配置给Activity
        setActionBar(toolbar);
        // 将菜单栏配置给toolbar
        toolbar.setOnCreateContextMenuListener(this);
        // 设置回退按钮，及回退按钮点击事件
        getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
