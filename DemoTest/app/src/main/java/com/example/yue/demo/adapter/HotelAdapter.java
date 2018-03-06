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
import com.example.yue.demo.data.Hotel;
import com.example.yue.demo.ui.activity.PageHotel;
import java.util.List;

/**
 * Created by yue on 2018/2/28.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Hotel> mHotelList;

    public HotelAdapter(Context context,List<Hotel> list){
        this.mContext = context;
        this.mHotelList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount(){
        return mHotelList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.name.setText(mHotelList.get(position).getName());
        viewHolder.intro.setText(mHotelList.get(position).getIntro());
        if (mHotelList.get(position).getImage()!=null) {
            Glide.with(mContext)
                    .load(mHotelList.get(position).getImage())
                    .into(viewHolder.pic);
        }
        viewHolder.hotelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PageHotel.class);
                intent.putExtra("name",mHotelList.get(position).getName());
                v.getContext().startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,intro;
        ImageView pic;
        LinearLayout hotelItem;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.hotel_name);
            intro = itemView.findViewById(R.id.hotel_intro);
            pic = itemView.findViewById(R.id.hotel_pic);
            hotelItem = itemView.findViewById(R.id.ll_hotel);
        }
    }

}
