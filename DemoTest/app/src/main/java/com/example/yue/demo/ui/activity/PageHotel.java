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
import com.example.yue.demo.data.Hotel;
import com.example.yue.demo.databinding.PageHotelBinding;

import io.realm.Realm;

/**
 * Created by yue on 2018/3/1.
 */

public class PageHotel extends AppCompatActivity {
    private Hotel mHotel;
    public static final String TAG = "PageHotel";
    private TextView name;
    private TextView intro;
    private ImageView img;
    private Toolbar mToolbar;
    private Realm mRealm;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        PageHotelBinding cateBinding = DataBindingUtil.setContentView(this, R.layout.page_hotel);
        initView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mHotel = new Hotel();
        initData(name);
        if (mHotel.getImage() != null) {
            Glide.with(getApplicationContext()).load(mHotel.getImage()).into(img);
        }
        cateBinding.setHotel(mHotel);
    }

    private void initView(){
        name = findViewById(R.id.hotel_name);
        intro = findViewById(R.id.hotel_intro);
        img = findViewById(R.id.hotel_img);
        mToolbar = findViewById(R.id.hotel_toolbar);
        mToolbar.setTitle(R.string.tab_hotel);
    }

    private Hotel initData(String name){
        mRealm = Realm.getDefaultInstance();
        Log.i(TAG, "initData: "+mRealm.where(Hotel.class).findAll().toString());
        mHotel = mRealm.where(Hotel.class).equalTo("name",name).findFirst();
        return mHotel;
    }
}
