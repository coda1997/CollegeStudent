package com.example.a60440.collegestudent.requestServes;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by 60440 on 2017/2/13.
 */

public interface AddVideoServes {
    @Multipart
    @POST("servlet/AddVideoServlet")
    Call<String> upload(@Part("fileName") String des,
                        @Part("file\";filename=\"1.txt") RequestBody file
                        );
}
