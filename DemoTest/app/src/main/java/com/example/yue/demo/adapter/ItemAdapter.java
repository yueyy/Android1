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
import com.example.yue.demo.data.Hotel;
import com.example.yue.demo.ui.activity.PageCate;
import com.example.yue.demo.ui.activity.PageHotel;
import com.example.yue.demo.ui.activity.PageView;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by yue on 2018/2/28.
 */

public class ItemAdapter <T extends RealmObject> extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    private Context mContext;
    private List<T> mList;

    public ItemAdapter(Context context, List<T> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, final int position){
        if (mList.get(position) instanceof Cate) {
            viewHolder.name.setText(((Cate) mList.get(position)).getName());
            viewHolder.intro.setText(((Cate) mList.get(position)).getIntro());
            if (((Cate) mList.get(position)).getImage() != null) {
                Glide.with(mContext)
                        .load(((Cate) mList.get(position)).getImage())
                        .into(viewHolder.pic);
            }

            viewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PageCate.class);
                    intent.putExtra("name", ((Cate) mList.get(position)).getName());
                    v.getContext().startActivity(intent);
                }
            });
        } else if (mList.get(position) instanceof Hotel){
            viewHolder.name.setText(((Hotel) mList.get(position)).getName());
            viewHolder.intro.setText(((Hotel) mList.get(position)).getIntro());
            if (((Hotel) mList.get(position)).getImage() != null) {
                Glide.with(mContext)
                        .load(((Hotel) mList.get(position)).getImage())
                        .into(viewHolder.pic);
            }

            viewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PageHotel.class);
                    intent.putExtra("name", ((Hotel) mList.get(position)).getName());
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            viewHolder.name.setText(((com.example.yue.demo.data.View) mList.get(position)).getName());
            viewHolder.intro.setText(((com.example.yue.demo.data.View) mList.get(position)).getIntro());
            if (((com.example.yue.demo.data.View) mList.get(position)).getImage() != null) {
                Glide.with(mContext)
                        .load(((com.example.yue.demo.data.View) mList.get(position)).getImage())
                        .into(viewHolder.pic);
            }

            viewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PageView.class);
                    intent.putExtra("name", ((com.example.yue.demo.data.View) mList.get(position)).getName());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,intro,ticket;
        ImageView pic;
        LinearLayout item;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            intro = itemView.findViewById(R.id.item_intro);
            ticket = itemView.findViewById(R.id.item_ticket);
            pic = itemView.findViewById(R.id.item_pic);
            item = itemView.findViewById(R.id.ll_item);
        }
    }
}
