package com.example.yueuy.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by yueuy on 18-1-15.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "songs";

    public MyOpenHelper(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table songs(" +
        "id integer primary key autoincrement, " +
        "title text," +
        "singer text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion){
        Toast.makeText(mContext,"you have updated",Toast.LENGTH_SHORT).show();
    }
}
