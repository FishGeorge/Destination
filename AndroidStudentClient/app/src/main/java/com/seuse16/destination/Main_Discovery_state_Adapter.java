package com.seuse16.destination;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 37280 on 2018/4/12.
 *
 * @author GeOrange
 * @version ${version}
 */
public class Main_Discovery_state_Adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Map<String, Object>> statelist;

    public Main_Discovery_state_Adapter() {
    }

    public Main_Discovery_state_Adapter(Context construct_context, ArrayList<Map<String, Object>> slist) {
        context = construct_context;
        this.statelist = slist;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // number of states
        return statelist.size();
    }

    @Override
    public Object getItem(int i) {
        // return states
//        System.out.println("---------------------i" + i);
        return statelist.get(i);
    }

    @Override
    public long getItemId(int i) {
        // id of an states
        return i;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = (ConstraintLayout) layoutInflater.inflate(R.layout.listview_state, null);

        // 获取控件
        TextView text_distance = (TextView) view.findViewById(R.id.record_distance);
        TextView text_time = (TextView) view.findViewById(R.id.record_time);
        TextView text_location = (TextView) view.findViewById(R.id.record_location);
        // 数据适配
        String time = (String) ((Map<String, Object>) getItem(i)).get("time");
        String[] time2 = time.split("_");
        // 控件与数据绑定
        text_distance.setText(String.format("%s km", ((Map<String, Object>) getItem(i)).get("distance")));
        text_time.setText(String.format("%s:%s:%s", time2[0], time2[1], time2[2]));
//        System.out.println("---------------------size" + statelist.size());
        text_location.setText((String) ((Map<String, Object>) getItem(i)).get("location"));
        // 返回一个视图
        return view;
    }
//
//    /**
//     * 主页面选择日期改变时，刷新ListView（的Adapter）
//     */
//    public void SelectedDateChange_refresh(ArrayList<Map<String,Object>> newList) {
//        statelist = newList;
//        notifyDataSetChanged();
//    }
}
