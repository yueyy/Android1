package com.example.yue.demo.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.yue.demo.R;
import com.example.yue.demo.data.View;
import com.example.yue.demo.databinding.PageViewBinding;

import io.realm.Realm;

/**
 * Created by yue on 2018/3/1.
 */

public class PageView extends AppCompatActivity {

    public static final String TAG = "PageCate";
    private TextView name;
    private TextView intro;
    private TextView ticket;
    private TextView route;
    private TextView strategy;
    private Button showRoute;
    private ImageView img;
    private Realm mRealm;
    private View mView;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        PageViewBinding cateBinding = DataBindingUtil.setContentView(this, R.layout.page_view);
        initView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mView = new View();
        mView = initData(name);
        if (mView.getImage() != null) {
            Glide.with(getApplicationContext()).load(mView.getImage()).into(img);
        }
        cateBinding.setView(mView);
        getRoute(mView);
    }

    private void initView(){
        name = findViewById(R.id.view_name);
        intro = findViewById(R.id.view_intro);
        ticket = findViewById(R.id.view_ticket);
        strategy = findViewById(R.id.view_strategy);
        showRoute = findViewById(R.id.view_show_route);
        route = findViewById(R.id.view_route);
        img = findViewById(R.id.view_img);
        showRoute.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                route.setVisibility(android.view.View.VISIBLE);
            }
        });
        mToolbar = findViewById(R.id.view_toolbar);
        mToolbar.setTitle(R.string.tab_view);


    }

    private View initData(String name){
        mRealm = Realm.getDefaultInstance();
        mView = mRealm.where(View.class).equalTo("name",name).findFirst();
        return mView;
    }

    private void getRoute(View view){

        String routeContent = view.getRoute();
        String [] r;
        String string = "";
        r = routeContent.split("——");
        for (int i = 0; i < r.length; i++) {
            String s = r[i];
            if (string.isEmpty()) {
                string = s;
            }else {
                if (i == r.length-1){
                    string = string + "\n->\n" + s +"\n";
                }else {
                    string = string + "\n->\n" + s;
                }
            }
        }
        route.setText(string);
    }
}
