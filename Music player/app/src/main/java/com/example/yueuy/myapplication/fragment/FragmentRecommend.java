package com.example.yueuy.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yueuy.myapplication.R;

/**
 * Created by yueuy on 18-1-14.
 */

public class FragmentRecommend extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_recommend,null);
        return view;
    }
}
