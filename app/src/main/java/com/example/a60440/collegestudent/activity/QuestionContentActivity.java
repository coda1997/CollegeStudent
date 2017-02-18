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
import com.example.a60440.collegestudent.bean.Answer;
import com.example.a60440.collegestudent.bean.QuestionInfo;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.requestServes.GetAnswer;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

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
    ArrayList<Answer> answers;
    @OnClick(R.id.id_question_answer)
    void setAnswer(){
        Intent intent = new Intent(this,AddAnswerActivity.class);
        intent.putExtra("iid",Integer.parseInt(questionInfo.getId()));
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_content);
        ButterKnife.bind(this);
        recyclerView=(RecyclerView)findViewById(R.id.rc_answer);
        Intent intent = getIntent();
        questionInfo=(QuestionInfo)intent.getSerializableExtra("Question");
        Log.i("questioninfo",questionInfo.toString());
        initQues();
        questionTitle.setText(questionInfo.getTitle());
        questionContent.setText(questionInfo.getContent());
        answerNumber.setText(questionInfo.getAnswerNumber()+"人回答");

    }

    private void initQues() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAnswer getanswer = retrofit.create(GetAnswer.class);
        Call<ArrayList<Answer>> call = getanswer.loadAnswers(Integer.parseInt(questionInfo.getId()));
        call.enqueue(new Callback<ArrayList<Answer>>() {
            @Override
            public void onResponse(Call<ArrayList<Answer>> call, Response<ArrayList<Answer>> response) {
                answers = response.body();
                Log.i("answer load:",answers.toString());
                if(answers==null)
                    Log.i("answer","fail");
                answerAdapter = new AnswerAdapter(answers,recyclerView.getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                answerAdapter.setOnItemClickListener(QuestionContentActivity.this);
                recyclerView.setAdapter(answerAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Answer>> call, Throwable t) {
                t.printStackTrace();
            }
        });






    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
