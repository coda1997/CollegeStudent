package com.example.a60440.collegestudent.bean;

import android.net.Uri;

import com.example.a60440.collegestudent.configuration.Constant;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by fate on 2016/11/21.
 */

public class UserInfo {
    public static int id;
    public static String uid;
    public static String username;
    public static String imageURL;
    public static String nickname;
    public static String signiture;
    public static String token;

    public static ArrayList<String> groupNames = new ArrayList<>();
    public static Uri getPrivateChatUri(String uid,String title){
        return Uri.parse(Constant.URI_CONVERSATION_PRIVATE+uid+"&title="+title);
    }
    public static String getIdentity(String uid){
        String identity = "";
        switch (uid.charAt(0)) {
            case 's':
                identity = "学生";
                break;
            case 't':
                identity = "老师";
                break;
            case 'c':
                identity = "大学生";
                break;
            case 'a':
                identity = "家长";
                break;
            default:
                break;
        }
        return identity;
    }

}
