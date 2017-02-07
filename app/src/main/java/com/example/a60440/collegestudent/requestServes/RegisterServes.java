package com.example.a60440.collegestudent.requestServes;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface RegisterServes {
    @POST("servlet/SignupServlet")
    Call<String> getString(@Query("username") String username, @Query("userpwd") String userpwd);
}
