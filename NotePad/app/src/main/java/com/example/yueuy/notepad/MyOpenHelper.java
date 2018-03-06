package com.example.yueuy.notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by yueuy on 17-11-28.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "note";

    public MyOpenHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table note(" +
                "id integer primary key autoincrement, " +
                "title text, " +
                "date integer, " +
                "content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        Toast.makeText(mContext,"onUpgrade",Toast.LENGTH_SHORT).show();
    }
}
