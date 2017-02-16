package com.example.a60440.collegestudent.fragment.questionFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.requestServes.GetAnswer;
import com.example.a60440.collegestudent.bean.AnswerInfo;
import com.example.a60440.collegestudent.bean.QuestionInfo;

import java.util.ArrayList;

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

    private QuestionInfo questionInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ques_content,container,false);
//        ButterKnife.bind(this,v);
//        getQuestion(questionId);
        return v;
    }
    private void getAnswer(String questionId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAnswer getAnswer = retrofit.create(GetAnswer.class);
        Call<ArrayList<AnswerInfo>> call = getAnswer.loadAnswers(questionInfo.id);
        call.enqueue(new Callback<ArrayList<AnswerInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<AnswerInfo>> call, Response<ArrayList<AnswerInfo>> response) {
                Log.e("==","return: "+response.body().toString());//response is question content
            }

            @Override
            public void onFailure(Call<ArrayList<AnswerInfo>> call, Throwable t) {
                Log.e("===","fail");
            }
        });

    }
}
