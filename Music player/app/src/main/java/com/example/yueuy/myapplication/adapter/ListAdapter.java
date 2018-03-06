package com.example.yueuy.myapplication.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yueuy.myapplication.SqlDao;
import com.example.yueuy.myapplication.bean.Songs;
import java.util.List;

/**
 * Created by yueuy on 18-1-15.
 */

public class ListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Songs> mSongs;
    private Context mContext;
    private SqlDao mSqlDao;

    public ListAdapter(Context context, List<Songs> list){
        mInflater = LayoutInflater.from(context);
        mSongs = list;
        mContext = context;
    }

    @Override
    public Object getItem(int position){
        return mSongs.get(position);
    }

    @Override
    public int getCount(){
        return mSongs.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent){





        return convertView;
    }

    class ViewHolder{
        TextView title;
        TextView singer;
    }
}
