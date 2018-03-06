package com.example.yueuy.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yueuy on 18-1-14.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public FragmentAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int arg0){
        return mList.get(arg0);
    }

    @Override
    public int getCount(){
        return mList.size();
    }

}
