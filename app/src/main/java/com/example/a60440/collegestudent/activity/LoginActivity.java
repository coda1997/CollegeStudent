package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.requestServes.RequestServes;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/27.
 */

public class LoginActivity extends Activity{
    private String userName = "";
    private String userpwd = "";

    @OnClick(R.id.button2)
    void loginOnClick(){
        userName = loginName.getText().toString();
        userpwd = loginpwd.getText().toString();
//        InitLogin(userName,userpwd);
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.button3)
    void registerOnClick(){
        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
    }

    @Bind(R.id.editText2)
    EditText loginName;
    @Bind(R.id.editText)
    EditText loginpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        ButterKnife.bind(this);
    }

    private void InitLogin(String username,String userpwd) {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
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
                if(response.body().toString().equals("true")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

}
