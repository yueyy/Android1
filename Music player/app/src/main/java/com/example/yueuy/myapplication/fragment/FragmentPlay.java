package com.example.yueuy.myapplication.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.yueuy.myapplication.R;

/**
 * Created by yueuy on 18-1-14.
 */

public class FragmentPlay extends Fragment {
    private ImageButton btnStart,btnPrevious,btnNext,btnFavorite;
    private SeekBar mSeekBar;
    private TextView tvFinish, tvLyric;
    private boolean ifFavorite = true;
    private boolean ifStart = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_play,null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        btnStart = getActivity().findViewById(R.id.btn_start);
        btnPrevious = getActivity().findViewById(R.id.btn_previous);
        btnNext = getActivity().findViewById(R.id.btn_next);
        btnFavorite = getActivity().findViewById(R.id.btn_favorite);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ifStart){
                    btnStart.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    ifStart = false;
                }else {
                    btnStart.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    ifStart = true;
                }
            }
        });
        btnStart.setScaleType(ImageView.ScaleType.FIT_XY);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ifFavorite){
                    btnFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    ifFavorite = false;
                }else {
                    btnFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                    ifFavorite = true;
                }
            }
        });
        btnFavorite.setScaleType(ImageView.ScaleType.FIT_XY);

    }
}
