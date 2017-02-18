package com.example.a60440.collegestudent.requestServes;

import com.example.a60440.collegestudent.bean.Info;
import com.example.a60440.collegestudent.bean.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface RequestServes {
    @POST("servletc/LoginServlet")
    Call<String> getString(@Query("username") String loginname,@Query("password") String loginpwd);

    //获得联系人信息
    @GET("GetUserInfoServlet")
    Call<Info> getUserInfo(@Query("quid")String queryUid, @Query("muid")String myUid);

    @GET("RequestTokenServlet")
    Call<Result> requestToken(@Query("uid") String uid);
}
