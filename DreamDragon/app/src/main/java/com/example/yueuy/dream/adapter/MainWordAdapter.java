package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yueuy on 18-2-1.
 */

public class MainWordAdapter extends RecyclerView.Adapter<MainWordAdapter.ViewHolder> {

    private static final String TAG = "newstory";
    private Context mContext;
    private LayoutInflater mInflater;
    private SharedPreferencesUtils mPreferencesUtils;
    private List<StoryData.KeywordsBean> mList;

    public MainWordAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mList = new ArrayList<>(8);
    }

    public MainWordAdapter(Context context,List<StoryData.KeywordsBean> keywords){
        this.mContext = context;
        this.mList = keywords;
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_keywords_btn,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.page.setText(mList.get(position).toString());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextInputEditText page;

        public ViewHolder(View itemView){
            super(itemView);
            page = itemView.findViewById(R.id.edt_keyword);
        }
    }
}
