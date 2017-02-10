package com.example.a60440.collegestudent.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.a60440.collegestudent.bean.User;

/**
 * Created by 60440 on 2017/2/9.
 */

public class UserUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "usrInfo";


    public static void setParam(Context context ,User user){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("id",(String)user.getId());
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
        editor.commit();
    }



    public static User getParam(Context context){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        User user = new User();
        user.setUsername(sp.getString("username","nonexist"));
        user.setPassword(sp.getString("password","nonexist"));
        user.setId(sp.getString("id","nonexist"));
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
        return user;
    }
}