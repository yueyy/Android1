package com.example.yue.demo.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.yue.demo.R;
import com.example.yue.demo.data.Cate;
import com.example.yue.demo.databinding.PageCateBinding;

import io.realm.Realm;

/**
 * Created by yue on 2018/3/1.
 */

public class PageCate extends AppCompatActivity{

    public static final String TAG = "PageCate";
    private TextView name;
    private TextView intro;
    private ImageView img;
    private Realm mRealm;
    private Cate mCate;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageCateBinding cateBinding = DataBindingUtil.setContentView(this, R.layout.page_cate);
        initView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mCate = new Cate();
        initData(name);
        if (mCate.getImage() != null) {
            Glide.with(getApplicationContext()).load(mCate.getImage()).into(img);
        }
        cateBinding.setCate(mCate);
    }

    private void initView(){
        name = findViewById(R.id.cate_name);
        intro = findViewById(R.id.cate_intro);
        img = findViewById(R.id.cate_img);
        mToolbar = findViewById(R.id.cate_toolbar);
        mToolbar.setTitle(R.string.tab_cate);
    }

    private Cate initData(String name){
        mRealm = Realm.getDefaultInstance();
        Log.i(TAG, "initData: "+mRealm.where(Cate.class).findAll().toString());
        mCate = mRealm.where(Cate.class).equalTo("name",name).findFirst();
        return mCate;

    }
}
