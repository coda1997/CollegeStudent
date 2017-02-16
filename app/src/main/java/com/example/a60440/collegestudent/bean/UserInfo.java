package com.example.a60440.collegestudent.bean;

import android.net.Uri;

/**
 * Created by mrwen on 2017/2/16.
 */

public class UserInfo {
    public static Uri getPrivateChatUri(String uid, String title) {
        return Uri.parse("rong://hl.iss.whu.edu.laboratoryproject/conversation/private?targetId=" + uid + "&title=" + title);
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
