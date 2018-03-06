package com.example.yueuy.notepad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yueuy on 17-11-28.
 */

public class PreviousText extends Activity {

    private SQLiteDatabase db;
    private Button mBtnSearch;
    private Button mBtnAdd;
    private Button mBtnDelete;
    private Button mBtnUpdate;
    private ListView lv;
    private SqlDao dao;
    private NoteAdapter mAdapter;
    private TextView tvTitle;
    private TextView tvContent;
    private List<Note> notesList;
    private LayoutInflater mInflater;
    private Boolean isDelete;
    private Boolean isUpdate;
    private Context mContext = this;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);
        init();
        initBtn();
        operate();
    }

    private void init(){
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        lv = findViewById(R.id.list_view);
        mInflater = getLayoutInflater();

        dao = new SqlDao(PreviousText.this);
        notesList = dao.search();
        mAdapter = new NoteAdapter(this,notesList);
        lv.setAdapter(mAdapter);

    }
    private void initBtn(){
        mBtnSearch = findViewById(R.id.btn_search);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnUpdate = findViewById(R.id.btn_update);

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.search();
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PreviousText.this,MainActivity.class);
                startActivity(i);
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dao.update();
            }
        });
    }

    private void operate() {
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String toBeDelete = notesList.get(position).getTitle();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Warning");
                builder.setMessage("Do you want to delete "+toBeDelete+"?");
                builder.setPositiveButton("Yes" ,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        if ( notesList.remove(position)!=null){
                            dao.delete(position,toBeDelete);
                            Toast.makeText(mContext,"succeed!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext,"failed!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                return false;
            }
        });

        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,NoteActivity.class);
                startActivity(i);
            }
        });
    }
}
