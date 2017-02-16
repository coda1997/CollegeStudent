package com.example.a60440.collegestudent.requestServes;

import com.example.a60440.collegestudent.bean.User;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2017/2/13.
 */

public interface AddVideoServes {
    @Multipart
    @POST("servlet/AddVideoServlet")
    Call<String> upload(
            @Part("userId") String id,
            @Part MultipartBody.Part file);
    @POST("servlet/AddVideoPlayNumber")
    Call<String> addVideoPlayNumber(@Query("videoId") int id);
}
