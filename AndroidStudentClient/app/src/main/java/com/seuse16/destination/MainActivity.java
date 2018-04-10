package com.seuse16.destination;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private android.support.v4.app.FragmentTransaction transaction;
    private android.support.v4.app.FragmentManager fragmentManager;

    private Map<String, Object> account_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultFragment();
        // 元件注册
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.menu_navigation);
        // 菜单
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 从DateEventAddActivity/DateEventEditActivity接受信息，保存数据
        Bundle bundle_account = MainActivity.this.getIntent().getExtras();
        if (bundle_account != null) {
//            account_login.put("a_name", bundle_account.get("account_login_name"));
//            account_login.putAll(HttpClient.getSingleAnswerObject(HttpClient.readObject(account_login)));
            // 并保存至数据库

        }
    }

    //初始化运动界面
    private void setDefaultFragment() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new Main_Exercise());
        transaction.commit();
    }

    //导航按钮切换三个界面
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_exercise:
                    transaction.replace(R.id.content, new Main_Exercise());
                    transaction.commit();
                    return true;
                case R.id.navigation_discovery:
                    transaction.replace(R.id.content, new Main_Discovery());
                    transaction.commit();
                    return true;
                case R.id.navigation_me:
                    transaction.replace(R.id.content, new Main_Me());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    private void setAccount_login(Map<String, Object> a) {
        account_login = a;
    }

    protected Map<String, Object> getAccount_login() {
        return account_login;
    }
}
