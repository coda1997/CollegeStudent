package com.example.a60440.collegestudent.requestServes;

/**
 * Created by 60440 on 2016/12/25.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoServes {
    @POST("servlet/GetVideoServlet")
    Call<String> getString(@Query("userId") String userId);

}