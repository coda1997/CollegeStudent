package com.example.a60440.collegestudent.requestServes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2017/2/12.
 */

public interface UpdateInfoServes {
    @POST("servlet/updateCollegeStudentServlet")
    @FormUrlEncoded
    Call<String> getString(@Field("id") int id,
                           @Field("nickname") String nickname,
                           @Field("password") String password,
                           @Field("region") String region,
                           @Field("school") String school,
                           @Field("signature") String signature,
                           @Field("imageURL") String imageURL,
                           @Field("gender") String gender,
                           @Field("major") String major,
                           @Field("introduction") String introduction
                           );

}
