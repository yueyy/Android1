package com.example.yue.demo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yue.demo.R;
import com.example.yue.demo.adapter.HotelAdapter;
import com.example.yue.demo.data.Hotel;
import com.example.yue.demo.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by yue on 2018/2/26.
 */

public class FragmentHotel extends Fragment {
    private static final String TAG = "fragmentHotel";
    private HotelAdapter mHotelAdapter;
    private LinearLayoutManager mManager;
    private RecyclerView mRecycleView;
    private List<Hotel> mHotelList = new ArrayList<>();
    private Realm mRealm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_hotel,null);
        mRecycleView = view.findViewById(R.id.recycle_hotel);
        mManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(mManager);
        mRealm = Realm.getDefaultInstance();
        mHotelList = mRealm.where(Hotel.class).findAll();
        Log.i(TAG, "onCreateView: "+mHotelList.toString());
        mHotelAdapter = new HotelAdapter(getContext(),mHotelList);
        mRecycleView.setAdapter(mHotelAdapter);
        mRecycleView.addItemDecoration(new SpaceItemDecoration(10));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView(){
        mRealm.close();
        super.onDestroyView();
    }
}
