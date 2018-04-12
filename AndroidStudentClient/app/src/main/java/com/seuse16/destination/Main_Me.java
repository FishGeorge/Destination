package com.seuse16.destination;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;


public class Main_Me extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // 注册
        ImageView img_edit= Objects.requireNonNull(getView()).findViewById(R.id.img_me_edit);
        //
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Function coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
