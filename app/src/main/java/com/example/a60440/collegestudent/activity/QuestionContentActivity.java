package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.AnswerAdapter;
import com.example.a60440.collegestudent.bean.AnswerInfo;
import com.example.a60440.collegestudent.bean.QuestionInfo;
import com.example.a60440.collegestudent.fragment.questionFragment.QuestionContent;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.requestServes.GetAnswer;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

/**
 * Created by 60440 on 2017/2/16.
 */

public class QuestionContentActivity extends Activity implements MyItemClickListener {

    @Bind(R.id.id_question_content)
    TextView questionTitle;
    @Bind(R.id.id_question_content_expamdanletextview)
    ExpandableTextView questionContent;
    @Bind(R.id.tv_discover_number)
    TextView answerNumber;
    @Bind(R.id.ib_answer)
    ImageView answer;
    QuestionInfo questionInfo;
    AnswerAdapter answerAdapter;
    RecyclerView recyclerView;
    ArrayList<AnswerInfo> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_content);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        questionInfo=(QuestionInfo)intent.getSerializableExtra("Question");
        initQues();

    }

    private void initQues() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAnswer getanswer = retrofit.create(GetAnswer.class);
        Call<ArrayList<AnswerInfo>> call = getanswer.loadAnswers(questionInfo.getId());
        call.enqueue(new Callback<ArrayList<AnswerInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<AnswerInfo>> call, Response<ArrayList<AnswerInfo>> response) {
                answers = response.body();
                if(answers==null)
                    Log.i("answer","fail");
                answerAdapter = new AnswerAdapter(answers,recyclerView.getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                answerAdapter.setOnItemClickListener(QuestionContentActivity.this);
                recyclerView.setAdapter(answerAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<AnswerInfo>> call, Throwable t) {

            }
        });






    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
