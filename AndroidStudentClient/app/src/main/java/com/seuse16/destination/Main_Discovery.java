package com.seuse16.destination;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class Main_Discovery extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private List<Fragment> list;
    private View view;
    private ViewPager viewPager;
    private Button bt1, bt2, bt3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_discovery, container, false);
        initView();
        return view;
    }

    //初始化Fragment
    private void initView() {
        viewPager = view.findViewById(R.id.viewpager);

        bt1 = view.findViewById(R.id.frag1);
        bt2 = view.findViewById(R.id.frag2);
        bt3 = view.findViewById(R.id.frag3);

        list = new ArrayList<>();

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

        bt1.setBackgroundColor(Color.parseColor("#92D050"));
        bt2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        bt3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        bt1.setTextColor(getResources().getColorStateList(R.color.White));
        bt2.setTextColor(getResources().getColorStateList(R.color.DarkGray));
        bt3.setTextColor(getResources().getColorStateList(R.color.DarkGray));

        //尚未实现子Fragment
        list.add(new Main_Discovery_state());
        list.add(new Main_Discovery_appoint());
        list.add(new Main_Discovery_nearby());

        viewPager.setAdapter(new FragmentAdapter(getFragmentManager(), list));
        viewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) this);
        viewPager.setCurrentItem(0);

    }

    //点击事件
    @Override
    public void onClick(View v) {
        initBtnListener();
        switch (v.getId()) {
            case R.id.frag1:
                bt1.setTextColor(getResources().getColorStateList(R.color.White));
                bt2.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt3.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                viewPager.setCurrentItem(0);
                break;
            case R.id.frag2:
                bt1.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt2.setTextColor(getResources().getColorStateList(R.color.White));
                bt3.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                viewPager.setCurrentItem(1);
                break;
            case R.id.frag3:
                bt1.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt2.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt3.setTextColor(getResources().getColorStateList(R.color.White));
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //滑动界面同步按钮
    @Override
    public void onPageSelected(int position) {
        initBtnListener();
        switch (position) {
            case 0:
                bt1.setBackgroundResource(R.color.colorPrimary);
                bt1.setTextColor(getResources().getColorStateList(R.color.White));
                bt2.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt3.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                break;
            case 1:
                bt2.setBackgroundResource(R.color.colorPrimary);
                bt1.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt2.setTextColor(getResources().getColorStateList(R.color.White));
                bt3.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                break;
            case 2:
                bt3.setBackgroundResource(R.color.colorPrimary);
                bt1.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt2.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                bt3.setTextColor(getResources().getColorStateList(R.color.White));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //初始化按钮
    private void initBtnListener() {
        bt1.setBackgroundResource(R.color.White);
        bt2.setBackgroundResource(R.color.White);
        bt3.setBackgroundResource(R.color.White);
    }
}
