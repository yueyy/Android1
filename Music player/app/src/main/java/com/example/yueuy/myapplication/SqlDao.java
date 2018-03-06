package com.example.yueuy.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yueuy.myapplication.bean.Songs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yueuy on 18-1-15.
 */

public class SqlDao {
    private MyOpenHelper mMyOpenHelper;
    public SqlDao (Context context){
        mMyOpenHelper = new MyOpenHelper(context);
    }

    public void add(Songs songs){
        SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",songs.getTitle());
        values.put("singer",songs.getSinger());
        db.insert("songs",null,values);
        db.close();
    }

    public List<Songs> search(){
        SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
        List<Songs> list = new ArrayList<>();
        Cursor cursor = db.query("songs", null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Songs songs = new Songs();
            songs.setTitle(cursor.getString(0));
            songs.setSinger(cursor.getColumnName(1));
            list.add(songs);
        }
        db.close();
        cursor.close();
        return list;
    }
    public void delete(int position,String title){
        SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
        db.delete("title","title=?",new String[]{title});
        db.close();
    }

}
