package com.example.a60440.collegestudent.requestServes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 60440 on 2017/2/13.
 */

public interface AddAnswer {
    @POST("servlet/AddAnswerServlet")
    @FormUrlEncoded
    Call<String> getString(@Field("id") String id,
                           @Field("userId") String userId,
                           @Field("content") String content
                           );
}
