package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a60440.collegestudent.Manifest;
import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
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
    @Bind(R.id.editText5)
    EditText registerpwd;
    @Bind(R.id.editText4)
    EditText registerCheckpwd;
    @OnClick(R.id.button5)
    void registerResetOnClick(){
        registerName.setText("");
        registerpwd.setText("");
        registerCheckpwd.setText("");

    }
    @OnClick(R.id.button4)
    void registerSubmitOnclick(){
        String pwd = registerpwd.getText().toString();
        String confirm = registerCheckpwd.getText().toString();
        int nameLength = registerName.getText().toString().length();
        int pwdLength = pwd.length();
        if(nameLength<7){
            Toast.makeText(this, "用户名过短，应有8-11位用户名", Toast.LENGTH_SHORT).show();
            return;
        }else if(nameLength>12){
            Toast.makeText(this, "用户名过长，应有8-11位用户名", Toast.LENGTH_SHORT).show();
            return;
        }else if(pwdLength<5&&pwdLength>19){
            Toast.makeText(this, "密码格式不正确，应为6-18位字母或数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pwd.equals(confirm)){
            InitSignup(registerName.getText().toString(),registerpwd.getText().toString());
            Log.i("signup == ",registerName.getText().toString()+" "+registerpwd.getText().toString());

        }else {
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            registerpwd.setText("");
            registerCheckpwd.setText("");
        }

    }
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);
        ButterKnife.bind(this);
        toolbar.setTitle("账号注册");
    }
    private void InitSignup(final String username, final String userpwd) {
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
                Toast.makeText(SignupActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                if(response.body().toString().equals("ture")){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(userpwd);
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}
