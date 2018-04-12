package com.seuse16.destination;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Handler mMainHandler;
    protected Map<String, Object> account_map = new HashMap<>();
    private boolean isLogginginSuccessful = false;

    Toolbar toolbar;
    EditText text_account;
    EditText text_password;
    TextView clickable_text_register;
    CheckBox cb_password;
    Button btn_login;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 元件注册
        toolbar = findViewById(R.id.toolbar_login);
        text_account = findViewById(R.id.edit_account);
        text_password = findViewById(R.id.edit_password);
        clickable_text_register = findViewById(R.id.login_register);
        cb_password = findViewById(R.id.checkBox);
        btn_login = findViewById(R.id.btn_login);
        // 密码明文密文设置
        cb_password.setChecked(false);
        cb_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if (cb_password.isChecked())
                    // 显示密码
                    text_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    // 隐藏密码
                    text_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        // login button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 此处调用登陆确认函数
                try {
                    if (text_account.getText().length() == 0) Toast_inLoggingin(4);
                    else {
                        if (text_password.getText().length() == 0) Toast_inLoggingin(5);
                        else {
                            new login_thread().start();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Intent intent_login = new Intent(LoginActivity.this, MainActivity.class);
//                // 跳转
//                startActivity(intent_login);
//                // 关闭页面
//                finish();
            }
        });
        // “注册”的下划线
        clickable_text_register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        // 点击左下方“注册”，跳转注册界面
        clickable_text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Toast_inLoggingin((Integer) msg.obj);
                if (isLogginginSuccessful) {
                    // 页面跳转
                    Intent intent_login = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle login = new Bundle();
                    login.putString("account_login_id", String.valueOf(account_map.get("a_id")));
                    intent_login.putExtras(login);
                    // 跳转
                    startActivity(intent_login);
                    // 关闭页面
                    finish();
                }
            }
        };
    }

    // 用于发送toast提示
    protected void Toast_inLoggingin(int type) {
        switch (type) {
            case 1:
                Toast.makeText(getApplicationContext(), "不存在此帐户！", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "密码与帐户不一致！", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(), account_map.get("nickname") + "，欢迎回来！", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "帐户不能为空！", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getApplicationContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(getApplicationContext(), "未知错误", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    class login_thread extends Thread {
        @Override
        public void run() {
            Looper.prepare();
            try {
                Message msg = mMainHandler.obtainMessage();
                msg.obj = login_account(text_account.getText().toString(), text_password.getText().toString());
                sleep(100);
                mMainHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int login_account(String account, String password) throws Exception {
        account_map.clear();
        account_map.put("table_name", "AccountInfo");
        account_map.put("a_id", account);
        Map<String, Object> map_query = HttpClient.getSingleAnswerObject(HttpClient.readObject(account_map));
        if (!map_query.containsKey("QueryResult")) {
            if (map_query.get("a_password").equals(password)) {
                account_map.clear();
                account_map.putAll(map_query);
                isLogginginSuccessful = true;
//                Toast_inLoggingin(3);
                return 3;
//                return true;
            } else {
//                Toast_inLoggingin(2);
                return 2;
//                return false;
            }
        } else {
//            Toast_inLoggingin(1);
            return 1;
//            return false;
        }
    }
}
