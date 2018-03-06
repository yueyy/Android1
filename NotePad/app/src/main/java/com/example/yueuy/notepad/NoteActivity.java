package com.example.yueuy.notepad;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by yueuy on 17-12-9.
 */

public class NoteActivity extends Activity {

    private TextView mTitle;
    private TextView mContent;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_note);

        mTitle = findViewById(R.id.title);
        mContent = findViewById(R.id.content);
    }

}
