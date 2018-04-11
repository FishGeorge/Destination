package com.seuse16.destination;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 37280 on 2018/1/5.
 *
 * @author GeOrange
 * @version ${version}
 */

public class SportsRecordDB {
    public static SQLiteDatabase sqLiteDatabase;
    private DestinationDBHelper destinationDBHelper;
    private Map<String, Object> account_logined = new HashMap<>();

    public SportsRecordDB(Context context, Map<String, Object> account) {
        destinationDBHelper = new DestinationDBHelper(context, "Destination_SportsRecord", null, 1, account);
        account_logined.putAll(account);
    }

    // （增）插入一个SportsRecord
    public void InsertSportsRecord(Map<String, Object> p) {
        // 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
        String sql = "insert into SportsRecord_" +
                account_logined.get("a_id") + "(id,date,start_time,time,location,distance,cost,isDel) values (null,?,?,?,?,?,?,0)";
        // 传递过来的Map<String, Object>下的各值分别按顺序替换上面sql语句的六个?，自动转换类型，下同
        Object bindArgs[] = new Object[]{p.get("date"), p.get("start_time"), p.get("time"), p.get("location"), p.get("distance"), p.get("cost")};
        // 执行这条无返回值的sql语句
        sqLiteDatabase.execSQL(sql, bindArgs);
    }

    //（查）根据a_id查阅SportsRecord，返回一个ArrayList
    public ArrayList<Map<String,Object>> dbQueryAll(String a_id) {
        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
        // isDel=0表示该事件存在
        String sql = "select * from SportsRecord_" +
                a_id + " where isDel=0";
        String[] selectionArgs = new String[]{};
        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        ArrayList<Map<String,Object>> out = new ArrayList<>();
        // 判断Cursor中是否有数据
        while (cursor.moveToNext()) {
            // 如果有，则把查到的值填充到一个实体
            Map<String,Object> add = new HashMap<>();
            add.put("date",cursor.getString(cursor.getColumnIndex("date")));
            add.put("start_time",cursor.getString(cursor.getColumnIndex("start_time")));
            add.put("time",cursor.getString(cursor.getColumnIndex("time")));
            add.put("location",cursor.getString(cursor.getColumnIndex("location")));
            add.put("distance",cursor.getInt(cursor.getColumnIndex("distance")));
            add.put("cost",cursor.getInt(cursor.getColumnIndex("cost")));
            //
            out.add(add);
        }
        return out;
    }

//    // （删）根据数据库id删除event
//    public void DeleteDateEvent(int id) {
//        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
//        //
//        String sql = "update Events_" +
//                Cal_account + " set isDel=1 where id=?";
//        String[] selectionArgs = new String[]{String.valueOf(id)};
//        // 执行
//        sqLiteDatabase.execSQL(sql, selectionArgs);
//    }
//
//    // （删）删除所有event
//    public void DeleteAllDateEvent() {
//        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
//        //
//        String sql = "update Events_" +
//                Cal_account + " set isDel=1 where isDel=0";
//        // 执行
//        sqLiteDatabase.execSQL(sql);
//    }
//
//    // （查）根据Date查阅DateEvent，返回一个List
//    public List<DateEvent> dbQueryOneByDate(int date) {
//        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
//        // isDel=0表示该事件存在
//        String sql = "select * from Events_" +
//                Cal_account + " where date=? and isDel=0";
//        String[] selectionArgs = new String[]{String.valueOf(date)};
//        @SuppressLint("Recycle")
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
//        List<DateEvent> out = new ArrayList<DateEvent>();
//        // 判断Cursor中是否有数据
//        while (cursor.moveToNext()) {
//            // 如果有DateEvent，则把查到的值填充到一个DateEvent实体
//            DateEvent addEvent = new DateEvent();
//            addEvent.setTitle(cursor.getString(cursor.getColumnIndex("title")));
//            addEvent.setDate(cursor.getInt(cursor.getColumnIndex("date")));
//            addEvent.setPeriod(new int[]{cursor.getInt(cursor.getColumnIndex("period_start")), cursor.getInt(cursor.getColumnIndex("period_finish"))});
//            addEvent.setContent(cursor.getString(cursor.getColumnIndex("content")));
//            addEvent.setLocation(cursor.getString(cursor.getColumnIndex("location")));
//            addEvent.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
//            //
//            out.add(addEvent);
//        }
//        return out;
//    }
//
//    // （查）根据DateEvent，返回一个id
//    public int QueryIdByDateEvent(DateEvent p) {
//        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
//        // isDel=0表示该事件存在
//        String sql = "select * from Events_" +
//                Cal_account + " where title=? and date=? and isDel=0";
//        String[] selectionArgs = new String[]{p.getTitle(), String.valueOf(p.getDate())};
//        @SuppressLint("Recycle")
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
//        int out = 0;
//        // 判断Cursor中是否有数据
//        if (cursor.moveToNext())
//            out = cursor.getInt(cursor.getColumnIndex("id"));
//        return out;
//    }
//
//    // （查）根据id，返回一个DateEvent
//    public DateEvent QueryDateEventById(int id) {
//        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
//        // isDel=0表示该事件存在
//        String sql = "select * from Events_" +
//                Cal_account + " where id=? and isDel=0";
//        String[] selectionArgs = new String[]{String.valueOf(id)};
//        @SuppressLint("Recycle")
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
//        DateEvent out = new DateEvent();
//        // 判断Cursor中是否有数据
//        while (cursor.moveToNext()) {
//            // 如果有DateEvent，则把查到的值填充到一个DateEvent实体
//            out.setTitle(cursor.getString(cursor.getColumnIndex("title")));
//            out.setDate(cursor.getInt(cursor.getColumnIndex("date")));
//            out.setPeriod(new int[]{cursor.getInt(cursor.getColumnIndex("period_start")), cursor.getInt(cursor.getColumnIndex("period_finish"))});
//            out.setContent(cursor.getString(cursor.getColumnIndex("content")));
//            out.setLocation(cursor.getString(cursor.getColumnIndex("location")));
//            out.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
//        }
//        return out;
//    }
//
//    // （查）返回所有未删事件
//    public List<DateEvent> getAllDateEvents() {
//        sqLiteDatabase = destinationDBHelper.getWritableDatabase();
//        // isDel=0表示该事件存在
//        String sql = "select * from Events_" +
//                Cal_account + " where isDel=?";
//        String[] selectionArgs = new String[]{"0"};
//        @SuppressLint("Recycle")
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
//        List<DateEvent> out = new ArrayList<DateEvent>();
//        // 判断Cursor中是否有数据
//        while (cursor.moveToNext()) {
//            // 如果有DateEvent，则把查到的值填充到一个DateEvent实体
//            DateEvent addEvent = new DateEvent();
//            addEvent.setTitle(cursor.getString(cursor.getColumnIndex("title")));
//            addEvent.setDate(cursor.getInt(cursor.getColumnIndex("date")));
//            addEvent.setPeriod(new int[]{cursor.getInt(cursor.getColumnIndex("period_start")), cursor.getInt(cursor.getColumnIndex("period_finish"))});
//            addEvent.setContent(cursor.getString(cursor.getColumnIndex("content")));
//            addEvent.setLocation(cursor.getString(cursor.getColumnIndex("location")));
//            addEvent.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
//            //
//            out.add(addEvent);
//        }
//        return out;
//    }
}
