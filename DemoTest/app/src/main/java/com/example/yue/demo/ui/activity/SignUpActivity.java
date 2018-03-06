package com.example.yue.demo.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yue.demo.R;

/**
 * Created by yue on 2018/2/24.
 */

public class SignUpActivity extends AppCompatActivity{

    private EditText edtAccount;
    private EditText edtPassword;
    private Button btnSignUp;
    private TextView tvLogin;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView(){
        edtAccount = findViewById(R.id.input_account);
        edtPassword = findViewById(R.id.input_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        tvLogin = findViewById(R.id.to_login);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void signUp(){
        if (!validate()){
            onSignUpFailed();
            return;
        }
        btnSignUp.setEnabled(false);

        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        if (account.isEmpty() || password.isEmpty()) {
            onSignUpFailed();
        }else {
            SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("account",account);
            editor.putString("password",password);
            editor.apply();
            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
            onSignUpSuccess();
            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void onSignUpFailed(){
        Toast.makeText(getBaseContext(), "you must complete your data!", Toast.LENGTH_SHORT).show();
        btnSignUp.setEnabled(true);
    }

    public void onSignUpSuccess(){
        btnSignUp.setEnabled(true);
        Toast.makeText(getBaseContext(),"you have create an account!",Toast.LENGTH_SHORT).show();
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
}
