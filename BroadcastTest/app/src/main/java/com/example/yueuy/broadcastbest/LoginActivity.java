package com.example.yueuy.broadcastbest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by yueuy on 17-11-21.
 */

public class LoginActivity extends BaseActivity {
    private EditText accountEditText;
    private EditText passwordEditText;
    private CheckBox mRemember;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        accountEditText = (EditText) findViewById(R.id.account);
        passwordEditText = (EditText) findViewById(R.id.password);
        mRemember = (CheckBox) findViewById(R.id.remember);
        boolean isRemember = mPreferences.getBoolean("remember_password",false);
        Button login = (Button) findViewById(R.id.login);
        if (isRemember){
            String account = mPreferences.getString("account","");
            String password = mPreferences.getString("password","");
            accountEditText.setText(account);
            passwordEditText.setText(password);
            mRemember.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    mEditor = mPreferences.edit();
                    if (mRemember.isChecked()){
                        mEditor.putBoolean("remember_password",true);
                        mEditor.putString("account",account);
                        mEditor.putString("password",password);
                    }else{
                        mEditor.clear();
                    }
                    mEditor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"please check your account and password",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
