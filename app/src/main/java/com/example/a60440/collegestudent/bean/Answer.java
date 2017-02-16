package com.example.a60440.collegestudent.bean;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fate on 2016/12/9.
 */

public class Answer {

    private int id;
    private String content;
    private boolean anonymous;
    private Date time;
    private int agree;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
