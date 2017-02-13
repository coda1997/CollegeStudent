package com.example.a60440.collegestudent.requestServes;

import com.example.a60440.collegestudent.utils.QuestionInfo;

import java.util.Set;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface QuestionRequestServes {
    @POST("servlet/GetQuestionServlet")
    Call<String> getString(@Query("userId") String userId);
}
