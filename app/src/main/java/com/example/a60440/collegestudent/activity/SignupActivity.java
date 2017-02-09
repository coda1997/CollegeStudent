package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.requestServes.RegisterServes;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2017/2/7.
 */

public class SignupActivity extends Activity {
    @Bind(R.id.editText3)
    EditText registerName;
    @Bind(R.id.editText4)
    EditText registerpwd;
    @Bind(R.id.editText5)
    EditText registerCheckpwd;
    @OnClick(R.id.button5)
    void registerResetOnClick(){
        registerName.setText("");
        registerpwd.setText("");
        registerCheckpwd.setText("");

    }
    @OnClick(R.id.button4)
    void registerSubmitOnclick(){
        InitSignup(registerName.toString(),registerpwd.toString());
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);
        ButterKnife.bind(this);
    }
    private void InitSignup(String username, String userpwd) {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
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
}
