package com.example.a60440.collegestudent.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.MainActivity;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.requestServes.RequestServes;
import com.example.a60440.collegestudent.requestServes.UpdateInfoServes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2017/2/9.
 */

public class UserUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "usrInfo";


    public static void setParam(Context context , final User user){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("id", user.getId());
        editor.putString("username", (String)user.getUsername());
        editor.putString("password", (String)user.getPassword());
        editor.putString("identity", (String)user.getIdentity());
        editor.putString("nickname", (String)user.getNickname());
        editor.putString("email", (String)user.getEmail());
        editor.putString("number", (String)user.getNumber());
        editor.putString("realname", (String)user.getRealname());
        editor.putString("classname", (String)user.getClassname());
        editor.putString("region", (String)user.getRegion());
        editor.putString("school", (String)user.getSchool());
        editor.putString("phone", (String)user.getPhone());
        editor.putString("signature", (String)user.getSignature());
        editor.putString("imageURL", (String)user.getImageURL());
        editor.putString("major",(String)user.getMajor());
        editor.putString("gender",(String)user.getGender());
        editor.putString("introduction",(String)user.getIntroduction());
        editor.putString("token",(String)user.getToken());
        editor.commit();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UpdateInfoServes updateInfoServes = retrofit.create(UpdateInfoServes.class);
        Call<String> call = updateInfoServes.getString(user.getId(),user.getNickname(),
                user.getPassword(),user.getRegion(),user.getSchool(),user.getSignature(),
                user.getImageURL(),user.getGender(),user.getMajor(),user.getIntroduction()
                );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("setting update:==",response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("setting update:==","fail");
            }
        });

    }



    public static User getParam(Context context){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        User user = new User();
        user.setUsername(sp.getString("username","nonexist"));
        user.setPassword(sp.getString("password","nonexist"));
        user.setId(sp.getInt("id",0));
        user.setIdentity(sp.getString("identity","nonexist"));
        user.setNickname(sp.getString("nickname","nonexist"));
        user.setEmail(sp.getString("email","nonexist"));
        user.setNumber(sp.getString("number","nonexist"));
        user.setRealname(sp.getString("realname","nonexist"));
        user.setClassname(sp.getString("classname","nonexist"));
        user.setRegion(sp.getString("region","nonexist"));
        user.setSchool(sp.getString("school","nonexist"));
        user.setPhone(sp.getString("phone","nonexist"));
        user.setSignature(sp.getString("signature","nonexist"));
        user.setImageURL(sp.getString("imageURL","nonexist"));
        user.setMajor(sp.getString("major","nonexist"));
        user.setGender(sp.getString("gender","男"));
        user.setIntroduction(sp.getString("introduction","nonexist"));
        user.setToken(sp.getString("token","noexist"));
        return user;
    }
}