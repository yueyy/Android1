package com.example.yueuy.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yueuy on 17-12-4.
 */

public class SqlDao {
    private MyOpenHelper mHelper;
    public SqlDao(Context context){
        mHelper = new MyOpenHelper(context);
    }

    public void add(Note note){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle());
        values.put("content",note.getContent());
        db.insert("note",null,values);
        db.close();
    }

    public void delete(int position,String title){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete("name","title=?",new String[]{title+""});
        db.close();
    }

    public List<Note> search(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<Note> list = new ArrayList<Note>();
        Cursor cursor = db.query("note",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Note note = new Note();
            note.setTitle(cursor.getString(0));
            note.setContent(cursor.getString(1));
            list.add(note);
        }
        db.close();
        cursor.close();
        return list;
    }
    public void update(Note note){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content",note.getContent());
        db.update("note",values,"title=?",new String[]{note.getTitle()+""});
        db.close();
    }
}
