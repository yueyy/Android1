package com.example.yueuy.broadcastbest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yueuy on 17-11-21.
 */

public class BaseActivity extends AppCompatActivity{
    private ForceOfflineReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ControlActivity.addActivity(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcast.FORCE_OFFLINE");
        mReceiver = new ForceOfflineReceiver();
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (mReceiver != null){
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ControlActivity.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline.Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    ControlActivity.finishAll();
                    Intent intent = new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
