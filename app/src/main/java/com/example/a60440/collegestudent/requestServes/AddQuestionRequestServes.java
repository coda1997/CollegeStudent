package com.example.a60440.collegestudent.requestServes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface AddQuestionRequestServes {
    @POST("servlet/AddQuestionServlet")
    @FormUrlEncoded
    Call<String> getString(@Field("userId") String id,
            @Field("question") String question
                           );
}
