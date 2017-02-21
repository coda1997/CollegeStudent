package com.example.a60440.collegestudent.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.requestServes.UpdateInfoServes;
import com.example.a60440.collegestudent.utils.UserUtils;

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
 * Created by 60440 on 2017/2/19.
 */

public class ChangePwdActivity extends AppCompatActivity {
    User user;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_old_password)
    EditText oldPassword;
    @Bind(R.id.et_new_password)
    EditText newPassword;
    @OnClick(R.id.bt_confirm_password)
    void confirm(){
        if(oldPassword.equals(user.getPassword())){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.baseURL))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UpdateInfoServes update = retrofit.create(UpdateInfoServes.class);
            Call<String> call = update.getString(user.getId(),user.getNickname(),user.getPassword(),user.getRegion(),user.getSchool()
            ,user.getSignature(),user.getImageURL(),user.getGender(),user.getMajor(),user.getIntroduction()
            );
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(ChangePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

                    finish();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(ChangePwdActivity.this, "修改失败"+t, Toast.LENGTH_SHORT).show();
                }
            });


        }else {
            Toast.makeText(this, "原密码输入错误", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        user = UserUtils.getParam(getApplicationContext());
        ButterKnife.bind(this);
    }
}
