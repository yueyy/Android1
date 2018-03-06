package com.example.yueuy.birthday.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.birthday.R;

/**
 * Created by yueuy on 17-12-26.
 */

public class SignupActivity extends AppCompatActivity{
    private static final String TAG = "SignupActivity";

    private EditText edtId;
    private EditText edtAccount;
    private EditText edtPassword;
    private Button btnSignUp;
    private TextView tvLogin;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    public void init(){
        edtId = (EditText)findViewById(R.id.input_name);
        edtAccount = (EditText)findViewById(R.id.input_account);
        edtPassword = (EditText)findViewById(R.id.input_password);
        btnSignUp = (Button)findViewById(R.id.btn_sign_up);
        tvLogin = (TextView)findViewById(R.id.tv_login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void signUp(){
        if (!validate()){
            onSignUpFailed();
            return;
        }
        btnSignUp.setEnabled(false);

        String name = edtId.getText().toString();
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        if (name.isEmpty() || account.isEmpty() || password.isEmpty()) {
            onSignUpFailed();
        }else {
            onSignUpSuccess();
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
