package com.example.a60440.collegestudent.requestServes;



import com.example.a60440.collegestudent.bean.CourseLearning;
import com.example.a60440.collegestudent.bean.MessageRecord;
import com.example.a60440.collegestudent.bean.TotalCourseLearning;
import com.example.a60440.collegestudent.bean.TotalMessageRecord;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mrwen on 2017/2/26.
 */

public interface InterfaceStudent {
    //获得课程学习情况
    @GET("servlet/GetCourseLearning")
    Call<ArrayList<CourseLearning>> getCourseLearning(@Query("id") int id);

    //获得课程学习总情况
    @GET("servlet/GetTotalCourseLearning")
    Call<ArrayList<TotalCourseLearning>> getTotalCourseLearning(@Query("id") int id);

    //获得消息发送情况
    @GET("servlet/GetMessageRecord")
    Call<ArrayList<MessageRecord>> getMessageRecord(@Query("uid") String uid);

    //获得消息发送总情况
    @GET("servlet/GetTotalMessageRecord")
    Call<ArrayList<TotalMessageRecord>> getTotalMessageRecord(@Query("uid") String uid);
}
