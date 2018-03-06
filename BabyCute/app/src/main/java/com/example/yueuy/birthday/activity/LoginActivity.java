package com.example.yueuy.birthday.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.birthday.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtAccount;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    public void init(){
        edtAccount = (EditText)findViewById(R.id.input_account);
        edtPassword = (EditText)findViewById(R.id.input_password);
        btnLogin = (Button)findViewById(R.id.btn_login);
        tvCreateAccount = (TextView)findViewById(R.id.create_account);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void login(){
        Log.d(TAG,"login");
        if (!validate()){
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();




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
        Intent intent = new Intent(getApplicationContext(),BirthdayActivity.class);
        startActivity(intent);
        finish();
    }

//    class ImageTask extends AsyncTask<Void,Void,Images>{
//
//        @Override
//        protected Images doInBackground(Void... params){
//            Images images = new LoadImage().get(bingBackground);
//            return images;
//        }
//
//        @Override
//        protected void onPostExecute(Images images){
//            initView(images);
//        }
//    }
}
