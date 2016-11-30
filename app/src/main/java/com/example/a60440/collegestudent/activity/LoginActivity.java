package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.requestServes.RegisterServes;
import com.example.a60440.collegestudent.requestServes.RequestServes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2016/11/27.
 */

public class LoginActivity extends Activity {
    private Button loginButton;
    private Button registerButton;
    private EditText loginName;
    private EditText loginpwd;
    private String userName = "";
    private String userpwd = "";
    private Button registerSubmit;
    private Button registerReset;
    private EditText registerName;
    private EditText registerpwd;
    private EditText registerCheckpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        InitView();
    }

    private void InitView() {
        loginButton = (Button)findViewById(R.id.button2);
        registerButton = (Button)findViewById(R.id.button3);
        loginName = (EditText)findViewById(R.id.editText);
        loginpwd = (EditText)findViewById(R.id.editText2);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.signup_main);
                initRegister();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = loginName.getText().toString();
                userpwd = loginpwd.getText().toString();
                InitLogin(userName,userpwd);
            }
        });

    }

    private void initRegister() {
        registerName = (EditText)findViewById(R.id.editText3);
        registerpwd = (EditText)findViewById(R.id.editText4);
        registerCheckpwd = (EditText)findViewById(R.id.editText5);
        registerSubmit = (Button)findViewById(R.id.button4);
        registerReset = (Button)findViewById(R.id.button5);
        registerReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerName.setText("");
                registerpwd.setText("");
                registerCheckpwd.setText("");
            }
        });
        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitSignup(registerName.toString(),registerpwd.toString());
            }
        });

    }

    private void InitSignup(String username, String userpwd) {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl("http://localhost:11112/Demo1/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterServes requestServes = retorfit.create(RegisterServes.class);
        Call<String> call = requestServes.getString(username.toString(),userpwd.toString());
        call.enqueue(new Callback<String>(){
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("===","fail");
            }

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("==","return:"+response.body().toString());
            }
        });


    }

    private void InitLogin(String username,String userpwd) {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl("http://localhost:11112/Demo1/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServes requestServes = retorfit.create(RequestServes.class);
        Call<String> call = requestServes.getString(username.toString(),userpwd.toString());
        call.enqueue(new Callback<String>(){
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("===","fail");
            }

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("==","return:"+response.body().toString());
            }
        });
    }
}
