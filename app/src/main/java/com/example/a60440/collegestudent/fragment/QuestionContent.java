package com.example.a60440.collegestudent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.requestServes.QuestionRequestServes;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2016/11/27.
 */

public class QuestionContent extends Fragment {
    @Bind(R.id.id_question_content)
    TextView questionContent;
    private int questionId = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ques_content,container,false);
        ButterKnife.bind(this,v);
        getQuestion(questionId);
        return v;
    }
    private void getQuestion(int questionId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QuestionRequestServes requestServes = retrofit.create(QuestionRequestServes.class);
        Call<String> call = requestServes.getString(questionId+"");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("==","return: "+response.body().toString());//response is question content
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("===","fail");
            }
        });

    }
}
