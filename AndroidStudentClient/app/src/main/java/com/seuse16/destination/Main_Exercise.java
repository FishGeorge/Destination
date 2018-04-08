package com.seuse16.destination;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;


public class Main_Exercise extends Fragment {

    private boolean isMorningRunning = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // 元件注册
        Button btn_start = Objects.requireNonNull(getView()).findViewById(R.id.btn_startrunning);
        TextView text_score = getView().findViewById(R.id.text_score);
        TextView text_location = getView().findViewById(R.id.text_location);
        final TextView text_morning = getView().findViewById(R.id.text_morning);
        final TextView text_usual = getView().findViewById(R.id.text_usual);
        ImageView img_menu = getView().findViewById(R.id.image_exercise_menu);
        ImageView img_timetable = getView().findViewById(R.id.image_exercise_timetable);
        ImageView img_info = getView().findViewById(R.id.image_exercise_info);
        // 跑步模式切换
        text_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMorningRunning = true;
                text_morning.setTextColor(getResources().getColorStateList(R.color.Black));
                text_usual.setTextColor(getResources().getColorStateList(R.color.DarkGray));
            }
        });
        text_usual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMorningRunning = false;
                text_morning.setTextColor(getResources().getColorStateList(R.color.DarkGray));
                text_usual.setTextColor(getResources().getColorStateList(R.color.Black));
            }
        });
        // 开始跑步
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RunningActivity.class));
            }
        });
        // 附近地点
        text_location.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        text_location.setText("梅园田径场");
    }
}