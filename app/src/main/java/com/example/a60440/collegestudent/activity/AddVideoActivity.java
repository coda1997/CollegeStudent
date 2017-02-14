package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.requestServes.AddQuestionRequestServes;
import com.example.a60440.collegestudent.requestServes.AddVideoServes;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 60440 on 2017/2/13.
 */

public class AddVideoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_maneger_full);

    }

//    private void updateVideo(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(getResources().getString(R.string.baseURL))
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AddVideoServes addVideoServes = retrofit.create(AddVideoServes.class);
//        File file = new File(Environment.getExternalStorageDirectory()+"/"+"1.txt");
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
//        Call<String> call = addVideoServes.upload("this is a text",requestBody);
//
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//
//
//    }
}
