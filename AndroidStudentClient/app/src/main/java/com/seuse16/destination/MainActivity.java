package com.seuse16.destination;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Handler mMainHandler;
    private android.support.v4.app.FragmentTransaction transaction;
    private android.support.v4.app.FragmentManager fragmentManager;

    public static Map<String, Object> account_login = new HashMap<>();

    public static SportsRecordDB mSportsRecordDB;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultFragment();
        // 元件注册
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.menu_navigation);
        // 菜单
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 从LoginActivity/RunningActivity接受信息，保存数据
        Bundle bundle = MainActivity.this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("account_login_id")) {
//                System.out.println("-----------------"+bundle.getString("account_login_id"));
                account_login.put("table_name", "AccountInfo");
                account_login.put("a_id", bundle.getString("account_login_id"));
                new accountquery_thread().start();
            }
            if (bundle.containsKey("SportsRecord")) {
                Map<String, Object> tmp = new HashMap<>(((SerializableMap) (Objects.requireNonNull(bundle.getSerializable("SportsRecord")))).getMap());
                // 保存至本地数据库
                mSportsRecordDB.InsertSportsRecord(tmp);
            }
        }
        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                account_login.putAll((Map<String, Object>) msg.obj);
                mSportsRecordDB = new SportsRecordDB(MainActivity.this, account_login);
//                System.out.println("-----------------done1");
            }
        };
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

    class accountquery_thread extends Thread {
        @Override
        public void run() {
            Looper.prepare();
            try {
                Message msg = mMainHandler.obtainMessage();
                msg.obj = HttpClient.getSingleAnswerObject(HttpClient.readObject(account_login));
                sleep(200);
                mMainHandler.sendMessage(msg);
//                System.out.println("-----------------done2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
