package com.example.a60440.collegestudent.requestServes;

import com.example.a60440.collegestudent.bean.Answer;
import com.example.a60440.collegestudent.bean.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2017/2/13.
 */

public interface GetAnswer {
    @GET("LoadAnswersServlet")
    Call<ArrayList<Answer>> loadAnswers(@Query("iid") int iid);

    @GET("LoadMyAnswersServlet")
    Call<ArrayList<Answer>> loadMyAnswer(@Query("uid")String uid);

    @GET("DeleteAnswerServlet")
    Call<Result> deleteAnswer(@Query("aid")int aid);
}

