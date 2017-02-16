package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.requestServes.AddQuestionRequestServes;
import com.example.a60440.collegestudent.bean.QuestionInfo;
import com.example.a60440.collegestudent.utils.UserUtils;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2016/12/8.
 */

public class AddAnswerActivity extends Activity {
//    @OnClick(R.id.id_BackButton)
//    void setBackButton(){
//        finish();
//    }

    @OnClick(R.id.id_question_submit)
    void setSubmit(){
        QuestionInfo question = new QuestionInfo();
        User user = UserUtils.getParam(getBaseContext());
        question.questionerImage=user.getImageURL();
        question.title=editText.getText().toString();
        question.questionerName=user.getNickname();


        // TODO: 2017/2/13
        String answer = editText.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AddQuestionRequestServes addQuestionServes = retrofit.create(AddQuestionRequestServes.class);
        Call<String> call = addQuestionServes.getString(user.getId(),(new Gson()).toJson(question));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("add question"," succeed");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    @Bind(R.id.editText_introduction)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quesbar01);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {


    }


}
