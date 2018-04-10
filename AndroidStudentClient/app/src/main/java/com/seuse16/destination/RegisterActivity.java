package com.seuse16.destination;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 元件注册
        Toolbar toolbar = findViewById(R.id.toolbar_register);
        final EditText account = findViewById(R.id.edit_account_r);
        final EditText password = findViewById(R.id.edit_password_r);
        final EditText password_repeat = findViewById(R.id.edit_password_r2);
        final EditText nickname = findViewById(R.id.edit_nickname);
        final Switch gender = findViewById(R.id.switch_gender);
        Button btn_register = findViewById(R.id.btn_register);
        // 将toolbar配置给Activity
        setActionBar(toolbar);
        // 将菜单栏配置给toolbar
        toolbar.setOnCreateContextMenuListener(this);
        // 设置回退按钮，及回退按钮点击事件
        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (account.getText().length() == 0) Toast_inRegistering(4);
                else {
                    if (password.getText().length() == 0) Toast_inRegistering(5);
                    else {
                        if (nickname.getText().length() == 0) Toast_inRegistering(6);
                        else {
                            int int_gender;
                            if (gender.isChecked())
                                int_gender = 0;
                            else
                                int_gender = 1;
                            // 此处调用注册函数
                            try {
                                register_account(account.getText().toString(), password.getText().toString(), nickname.getText().toString(), int_gender);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
//                // 返回登陆界面
//                finish();
            }
        });
    }

    // 用于发送toast提示
    protected void Toast_inRegistering(int type) {
        switch (type) {
            case 1:
                Toast.makeText(getApplicationContext(), "已存在同名帐户！", Toast.LENGTH_SHORT).show();
            case 2:
                Toast.makeText(getApplicationContext(), "两次密码不一致！", Toast.LENGTH_SHORT).show();
            case 3:
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
            case 4:
                Toast.makeText(getApplicationContext(), "帐户不能为空！", Toast.LENGTH_SHORT).show();
            case 5:
                Toast.makeText(getApplicationContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
            case 6:
                Toast.makeText(getApplicationContext(), "昵称不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    public void register_account(String account, String password, String nickname, int gender) throws
            Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", "AccountInfo");
        map.put("a_type", 1);
        map.put("a_name", account);
        ArrayList<Map<String, Object>> maplist_query = HttpClient.getAnswerObjects(HttpClient.readObject(map));
        if (maplist_query.size() > 0) {
            Toast_inRegistering(1);
//            maplist_query.clear();
        } else {
            map.put("a_password", password);
            map.put("gender", gender);
//            map.put("scheduled_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            HttpClient.createObject(map);
            if (HttpClient.getAnswerObjects(HttpClient.readObject(map)).size() > 0) {
                Toast_inRegistering(3);
                finish();
            }
        }
    }
}
