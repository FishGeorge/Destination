package com.seuse16.destination;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private boolean isRegisteringSuccessful = false;
    private Handler mMainHandler;

    EditText text_account;
    EditText text_password;
    EditText text_password_repeat;
    EditText text_nickname;
    Switch gender;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 元件注册
        Toolbar toolbar = findViewById(R.id.toolbar_register);
        text_account = findViewById(R.id.edit_account_r);
        text_password = findViewById(R.id.edit_password_r);
        text_password_repeat = findViewById(R.id.edit_password_r2);
        text_nickname = findViewById(R.id.edit_nickname);
        gender = findViewById(R.id.switch_gender);
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
                if (text_account.getText().length() == 0) Toast_inRegistering(4);
                else {
                    if (text_password.getText().length() == 0) Toast_inRegistering(5);
                    else {
                        if (text_nickname.getText().length() == 0) Toast_inRegistering(6);
                        else {
                            if (!text_password.getText().toString().equals(text_password_repeat.getText().toString()))
                                Toast_inRegistering(2);
                            else {
                                try {
                                    // 此处调用注册函数
                                    new register_thread().start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
//                // 返回登陆界面
//                finish();
            }
        });
        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Toast_inRegistering((Integer) msg.obj);
                if (isRegisteringSuccessful)
                    // 注册成功返回登录页面
                    finish();
            }
        };
    }

    // 用于发送toast提示
    protected void Toast_inRegistering(int type) {
        switch (type) {
            case 1:
                Toast.makeText(getApplicationContext(), "已存在同名帐户！", Toast.LENGTH_SHORT).show();
                text_account.setText("");
                text_password.setText("");
                text_password_repeat.setText("");
                text_nickname.setText("");
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "两次密码不一致！", Toast.LENGTH_SHORT).show();
                text_password.setText("");
                text_password_repeat.setText("");
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "帐户不能为空！", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getApplicationContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(getApplicationContext(), "昵称不能为空！", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(getApplicationContext(), "未知错误", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    class register_thread extends Thread {
        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            // 初始化消息循环队列，需要在Handler创建之前
            Looper.prepare();
            try {
                int int_gender;
                if (gender.isChecked()) int_gender = 0;
                else int_gender = 1;
                Message msg = mMainHandler.obtainMessage();
                msg.obj = register_account(text_account.getText().toString(), text_password.getText().toString(), text_nickname.getText().toString(), int_gender);
                sleep(100);
                mMainHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    public int register_account(String account, String password, String nickname, int gender) throws
            Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("table_name", "AccountInfo");
        map.put("a_type", 1);
        map.put("a_id", account);
        Map<String, Object> map_query = HttpClient.getSingleAnswerObject(HttpClient.readObject(map));
        if (!map_query.containsKey("QueryResult")) {
//            Toast_inRegistering(1);
            return 1;
//            return false;
        } else {
            map.put("a_password", password);
            map.put("nickname", nickname);
            map.put("gender", gender);
//            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put("scheduled_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            HttpClient.createObject(map);
            if (!HttpClient.getSingleAnswerObject(HttpClient.readObject(map)).containsKey("QueryResult")) {
                isRegisteringSuccessful = true;
//                Toast_inRegistering(3);
                return 3;
//                return true;
            } else {
//                Toast_inRegistering(0);
                return 0;
//                return false;
            }
        }
    }
}
