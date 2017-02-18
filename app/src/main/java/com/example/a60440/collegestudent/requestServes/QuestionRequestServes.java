package com.example.a60440.collegestudent.requestServes;

import com.example.a60440.collegestudent.bean.QuestionInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface QuestionRequestServes {
    @POST("servletc/GetQuestionServlet")
    Call<String> getString(@Query("userId") int userId);

    @GET("LoadDiscoverServlet")
    Call<ArrayList<QuestionInfo>> loadDiscover(@Query("start") int start);
}
