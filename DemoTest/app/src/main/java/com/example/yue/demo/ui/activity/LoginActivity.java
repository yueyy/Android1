package com.example.yue.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yue.demo.R;

/**
 * Created by yue on 2018/2/24.
 */

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "login";
    private EditText edtAccount;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvSignUp;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sp = getSharedPreferences("user", Activity.MODE_PRIVATE);
        if (sp.getBoolean("login",false)){
            onLoginSucceed();
        }else {
            initView();
        }
    }

    private void initView(){
        edtAccount = findViewById(R.id.edt_account);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        tvSignUp = findViewById(R.id.to_sign_up);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void login(){
        if (!validate()){
            onLoginFailed();
            btnLogin.setEnabled(false);
        }else {
            String account = edtAccount.getText().toString();
            String password = edtPassword.getText().toString();
            Log.i(TAG, "login: "+account+password);
            SharedPreferences sp = getSharedPreferences("user", Activity.MODE_PRIVATE);
            String userAccount = sp.getString("account","");
            String userPassword = sp.getString("password","");

            if (account.equals(userAccount)&&password.equals(userPassword)) {
                onLoginSucceed();
                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("login",true);
                editor.apply();
            }else{
                Toast.makeText(getApplicationContext(),"请检查输入！",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean validate(){
        boolean valid = true;

        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();

        if (account.isEmpty()){
            valid = false;
        }else{
            edtAccount.setError(null);
        }

        if (password.isEmpty()){
            valid = false;
        }else {
            edtPassword.setError(null);
        }
        return valid;
    }

    public void onLoginFailed(){
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
        btnLogin.setEnabled(true);
    }

    public void onLoginSucceed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
