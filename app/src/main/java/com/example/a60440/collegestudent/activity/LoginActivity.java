package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.requestServes.RequestServes;
import com.example.a60440.collegestudent.utils.UserUtils;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by 60440 on 2016/11/27.
 */

public class LoginActivity extends Activity {
    private String userName = "";
    private String userpwd = "";

    @OnClick(R.id.button2)
    void loginOnClick() {
        userName = loginName.getText().toString();
        userpwd = loginpwd.getText().toString();
        InitLogin(userName,userpwd);
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
  //      startActivity(intent);
        //here is a test for initialize a user account
//        User user = new User();
//        user.setUsername("zhang3");
//        user.setPassword("123456");
//        user.setNickname("张三");
//        user.setPhone("13026133110");
//        user.setEmail("haha");
//        user.setGender("男");
//        UserUtils.setParam(getBaseContext(), user);
    }

    @OnClick(R.id.button3)
    void registerOnClick() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
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

    private void InitLogin(final String username, final String userpwd) {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServes requestServes = retorfit.create(RequestServes.class);
        Call<String> call = requestServes.getString(username.toString(), userpwd.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("===", "fail");
            }

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("==", "return:" + username+" "+ userpwd);
                Gson gson = new Gson();
                User user = gson.fromJson(response.body(), User.class);
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("User", user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    //something to do
                }
            }
        });
    }

}