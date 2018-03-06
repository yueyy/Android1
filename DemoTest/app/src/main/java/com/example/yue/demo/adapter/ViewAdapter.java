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
import com.example.yue.demo.ui.activity.PageView;

import java.util.List;

/**
 * Created by yue on 2018/2/28.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {


    private Context mContext;
    private List<com.example.yue.demo.data.View> mViewList;

    public ViewAdapter(Context context, List<com.example.yue.demo.data.View> viewList){
        this.mContext = context;
        this.mViewList = viewList;
    }

    @Override
    public int getItemCount(){
        return mViewList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.name.setText(mViewList.get(position).getName());
        viewHolder.intro.setText(mViewList.get(position).getIntro());
        if (mViewList.get(position).getImage()!=null) {
            Glide.with(mContext)
                    .load(mViewList.get(position).getImage())
                    .into(viewHolder.pic);
        }

        viewHolder.ticket.setText(mViewList.get(position).getTicket());
        viewHolder.viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PageView.class);
                intent.putExtra("name",mViewList.get(position).getName());
                v.getContext().startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,intro,ticket;
        ImageView pic;
        LinearLayout viewItem;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.view_name);
            intro = itemView.findViewById(R.id.view_intro);
            ticket = itemView.findViewById(R.id.view_ticket);
            pic = itemView.findViewById(R.id.view_pic);
            viewItem = itemView.findViewById(R.id.ll_view);
        }
    }
}
