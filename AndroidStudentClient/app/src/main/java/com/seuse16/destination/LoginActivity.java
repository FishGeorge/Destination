package com.seuse16.destination;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {
    public static String Cal_account = "admin";
    public static String Cal_password = "admin";

    public static boolean isLoggedin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar_login);
        final EditText account = findViewById(R.id.edit_account);
        final EditText password = findViewById(R.id.edit_password);
        TextView register = findViewById(R.id.login_register);
        Button login = findViewById(R.id.btn_login);
        final CheckBox cb_password = findViewById(R.id.checkBox);

        // 密码明文密文设置
        cb_password.setChecked(false);
        cb_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if (cb_password.isChecked())
                    // 显示密码
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    // 隐藏密码
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        // 下划线
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        // 点击左下方“注册”，跳转注册界面
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //假设密码用户名匹配，点击登陆进入主界面
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}
