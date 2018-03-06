package com.example.yue.demo.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yue on 2018/3/1.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<Fragment> mFragments;
    private String[] title = {"美食" ,"酒店" ,"景点"};

    public FragmentAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.fm=fm;
        this.mFragments = list;
    }

    @Override
    public Fragment getItem(int position){
        return mFragments.get(position);
    }

    @Override
    public int getCount(){
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return title[position];
    }
}
