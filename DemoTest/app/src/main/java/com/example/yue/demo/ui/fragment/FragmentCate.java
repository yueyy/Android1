package com.example.yue.demo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yue.demo.R;
import com.example.yue.demo.adapter.ItemAdapter;
import com.example.yue.demo.data.Cate;
import com.example.yue.demo.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by yue on 2018/2/26.
 */

public class FragmentCate extends Fragment {

    private ItemAdapter mAdapter;
    private LinearLayoutManager mManager;
    private RecyclerView mRecycleView;
    private List<Cate> mCateList = new ArrayList<>();
    private Realm mRealm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cate,null);
        mRecycleView = view.findViewById(R.id.recycle_cate);
        mManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(mManager);
        mRealm = Realm.getDefaultInstance();
        mCateList = mRealm.where(Cate.class).findAll();
        mAdapter = new ItemAdapter(getContext(),mCateList);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new SpaceItemDecoration(10));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mRealm.close();
        super.onDestroyView();

    }
}
