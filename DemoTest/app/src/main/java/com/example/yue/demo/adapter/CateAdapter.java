package com.example.yue.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yue.demo.R;
import com.example.yue.demo.data.Cate;
import com.example.yue.demo.ui.activity.PageCate;

import java.util.List;

/**
 * Created by yue on 2018/2/28.
 */

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Cate> mCateList;

    public CateAdapter(Context context,List<Cate> list){
        this.mContext = context;
        this.mCateList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount(){
        return mCateList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cate,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.name.setText(mCateList.get(position).getName());
        viewHolder.intro.setText(mCateList.get(position).getIntro());
        if (mCateList.get(position).getImage()!=null) {
            Glide.with(mContext)
                    .load(mCateList.get(position).getImage())
                    .into(viewHolder.pic);
        }
        viewHolder.cateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PageCate.class);
                intent.putExtra("name",mCateList.get(position).getName());
                v.getContext().startActivity(intent);
            }
        });
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,intro;
        ImageView pic;
        LinearLayout cateItem;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.cate_name);
            intro = itemView.findViewById(R.id.cate_intro);
            pic = itemView.findViewById(R.id.cate_pic);
            cateItem = itemView.findViewById(R.id.ll_cate);
        }
    }

}
