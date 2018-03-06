package com.example.yueuy.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yueuy.myapplication.adapter.FragmentAdapter;
import com.example.yueuy.myapplication.fragment.FragmentList;
import com.example.yueuy.myapplication.fragment.FragmentPlay;
import com.example.yueuy.myapplication.fragment.FragmentRecommend;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yueuy on 18-1-14.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private FragmentAdapter mAdapter;
    private List<Fragment> mList = new ArrayList<>();

    private Button btnPlay, btnList, btnRecommend;
    private ImageView cursor;
    private int[] widthArgs;
    private Button[] btnArgs;
    float cursorX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPage();

    }
    private void initView(){
        mViewPager = findViewById(R.id.viewPager);
        btnPlay = findViewById(R.id.btn_play);
        btnList = findViewById(R.id.btn_list);
        btnRecommend = findViewById(R.id.btn_recommend);
        btnArgs = new Button[]{btnPlay,btnList,btnRecommend};
        cursor = findViewById(R.id.btn_cursor);
        cursor.setBackgroundColor(Color.WHITE);

        btnPlay.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnRecommend.setOnClickListener(this);

        btnPlay.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)cursor.getLayoutParams();
                layoutParams.width = btnPlay.getWidth() - btnPlay.getPaddingLeft()*2;
                cursor.setLayoutParams(layoutParams);
                cursor.setX(btnPlay.getPaddingLeft());
            }
        });

        resetButtonColor();
        btnPlay.setTextColor(Color.WHITE);

    }

    private void resetButtonColor(){
        btnList.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimaryDark));
        btnPlay.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimaryDark));
        btnRecommend.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimaryDark));
        btnList.setTextColor(ContextCompat.getColor(getBaseContext(),R.color.colorUnCheckedButton));
        btnPlay.setTextColor(ContextCompat.getColor(getBaseContext(),R.color.colorUnCheckedButton));
        btnRecommend.setTextColor(ContextCompat.getColor(getBaseContext(),R.color.colorUnCheckedButton));
    }

    private void initPage(){
        FragmentPlay fmPlay = new FragmentPlay();
        FragmentList fmList = new FragmentList();
        FragmentRecommend fmRecommend = new FragmentRecommend();
        mList.add(fmList);
        mList.add(fmPlay);
        mList.add(fmRecommend);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(),mList);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnClickListener(this);
    }

    public void cursorA(int cursorItem){
        cursorX = 0;
        if (widthArgs ==null){
            widthArgs = new int[]{btnPlay.getWidth(),btnList.getWidth(),btnRecommend.getWidth()};
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)cursor.getLayoutParams();
        layoutParams.width = widthArgs[cursorItem]-btnArgs[0].getPaddingLeft()*2;
        cursor.setLayoutParams(layoutParams);
        for (int i = 0; i < cursorItem; i++) {
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        cursor.setX(cursorX + btnArgs[cursorItem].getPaddingLeft());
    }

    @Override
    public void onPageScrollStateChanged(int arg0){

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2){

    }

    @Override
    public void onPageSelected(int arg0){
        resetButtonColor();
        btnArgs[arg0].setTextColor(Color.RED);
        cursorA(arg0);

    }

    @Override
    public void onClick(View btn){
        switch (btn.getId()){
            case R.id.btn_list:
                mViewPager.setCurrentItem(0);
                cursorA(0);
                break;
            case R.id.btn_play:
                mViewPager.setCurrentItem(1);
                cursorA(1);
                break;
            case R.id.btn_recommend:
                mViewPager.setCurrentItem(2);
                cursorA(2);
                break;
        }
    }
}
