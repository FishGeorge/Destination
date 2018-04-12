package com.seuse16.destination;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Main_Discovery_state extends Fragment {

    protected Main_Discovery_state_Adapter adapter = new Main_Discovery_state_Adapter();
    private ListView stateList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery_state, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        stateList= Objects.requireNonNull(getView()).findViewById(R.id.listview_state);
        ArrayList<Map<String,Object>> RecordList=MainActivity.mSportsRecordDB.dbQueryAll((String) MainActivity.account_login.get("a_id"));
        // ListView的适配器
//        System.out.println("---------------------1");
        adapter = new Main_Discovery_state_Adapter(this.getContext(), RecordList);
//        System.out.println("---------------------2");
        stateList.setAdapter(adapter);
//        System.out.println("---------------------3");
    }

}
