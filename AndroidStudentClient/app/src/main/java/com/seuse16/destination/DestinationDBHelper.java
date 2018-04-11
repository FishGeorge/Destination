package com.seuse16.destination;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 37280 on 2018/4/11.
 *
 * @author GeOrange
 * @version ${version}
 */
public class DestinationDBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    private static final String TAG = "Destination_SQLite";
    private Map<String, Object> account_logined = new HashMap<>();

    public DestinationDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, Map<String, Object> a) {
        super(context, name, factory, version);
        account_logined.putAll(a);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //如果初次运行，建立一张SportsRecord_表，建表的时候注意，自增是AUTOINCREMENT，而不是mysql的AUTO_INCREMENT
        String sql =
                "create table if not exists SportsRecord_" +
                        account_logined.get("a_id") + "(" +
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "date VARCHAR(127)," +
                        "start_time VARCHAR(127)," +
                        "time VARCHAR(127)," +
                        "location VARCHAR(127)," +
                        "distance INTEGER DEFAULT 0," +
                        "cost INTEGER DEFAULT 0," +
                        "isDel INTEGER DEFAULT 0" +
                        ")";
        Log.i(TAG, "create Database-------->");
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
