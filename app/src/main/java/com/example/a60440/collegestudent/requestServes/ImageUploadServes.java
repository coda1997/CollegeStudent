package com.example.a60440.collegestudent.requestServes;

import android.database.Observable;

import com.example.a60440.collegestudent.bean.Result;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 60440 on 2017/2/12.
 */

public interface ImageUploadServes {
    @POST("servlet/ImageServlet")
    @Multipart
    Call<String> uploadImage(@Part("fileName") String uid , @Part("file\";filename=\"1.jpg") RequestBody file);
}
