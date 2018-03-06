package com.example.yueuy.notepad;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Note> notesList;
    private MyOpenHelper mOpenHelper;
    private SQLiteDatabase db;
    private Button mBtnSave;
    private Button mBtnList;
    private SqlDao dao;
    private EditText edtTitle;
    private EditText edtContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenHelper = new MyOpenHelper(this);
        db = mOpenHelper.getWritableDatabase();

        mBtnList = (Button) findViewById(R.id.btn_noteList);
        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PreviousText.class);
                startActivity(i);
            }
        });

        initData();

    }
    private void initData(){
        final EditText mTitleEdt = (EditText) findViewById(R.id.title_edt);
        final EditText mContentEdt = (EditText) findViewById(R.id.main_edt);

        dao = new SqlDao(MainActivity.this);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTitleEdt!=null||mContentEdt!=null) {
                    mOpenHelper = new MyOpenHelper(MainActivity.this);
                    Note note = new Note();
                    note.setTitle(mTitleEdt.getText().toString());
                    note.setContent(mContentEdt.getText().toString());
                    dao.add(note);
                    notesList = dao.search();

                    Toast.makeText(MainActivity.this, "saved successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
