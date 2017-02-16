package com.example.a60440.collegestudent.requestServes;

import com.example.a60440.collegestudent.bean.AnswerInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2017/2/13.
 */

public interface GetAnswer {
    @POST("servlet/GetAnswerServlet")
    Call<ArrayList<AnswerInfo>> loadAnswers(@Query("iid") String iid);
}
