package com.example.a60440.collegestudent.requestServes;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface QuestionRequestServes {
    @POST("/servlet/Question")
    Call<String> getString(@Query("questionId") String quertionId);
}
