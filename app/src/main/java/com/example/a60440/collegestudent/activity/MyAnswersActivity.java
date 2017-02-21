package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.RecyclerMyAnswersAdapter;
import com.example.a60440.collegestudent.bean.Answer;
import com.example.a60440.collegestudent.bean.Result;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.listener.OnDeleteClickListener;
import com.example.a60440.collegestudent.listener.OnRecyclerViewItemClickListener;
import com.example.a60440.collegestudent.requestServes.AddAnswer;
import com.example.a60440.collegestudent.requestServes.AddVideoServes;
import com.example.a60440.collegestudent.requestServes.GetAnswer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MyAnswersActivity extends AppCompatActivity {

    @Bind(R.id.recycler_general)
    RecyclerView recyclerAnswers;
    private RecyclerMyAnswersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_answers);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initData();
    }


    private void initView() {
        mAdapter = new RecyclerMyAnswersAdapter(new ArrayList<Answer>());
        recyclerAnswers.setLayoutManager(new LinearLayoutManager(this));
        recyclerAnswers.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener<Answer>() {
            @Override
            public void onItemClick(View v, Answer data) {
                Intent intent = new Intent(MyAnswersActivity.this,QuestionContentActivity.class);
                intent.putExtra("issue",data.getIssue());
                startActivity(intent);
            }
        });
        mAdapter.setOnDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int id) {
                deleteAnswer(id);
            }
        });
//        mAdapter.setOnUserInfoClickListener(new OnUserInfoClickListener() {
//            @Override
//            public void onUserInfoClickListener(String uid) {
//                Intent intent = new Intent(MyAnswersActivity.this,PersonalInfoActivity.class);
//                intent.putExtra("uid",uid);
//                startActivity(intent);
//            }
//        });
    }

    private void deleteAnswer(final int id) {
//        RetrofitUtils.getService().deleteAnswer(id).subscribeOn(Schedulers.newThread())
//        .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        Toast.makeText(MyAnswersActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
//                        mAdapter.remove(id);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(MyAnswersActivity.this, "删除失败"+throwable, Toast.LENGTH_SHORT).show();
//                    }
//                });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAnswer getAnswer = retrofit.create(GetAnswer.class);
        Call<Result> call = getAnswer.deleteAnswer(id);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Toast.makeText(MyAnswersActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                mAdapter.remove(id);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(MyAnswersActivity.this, "删除失败"+t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initData() {
//        RetrofitUtils.getService().loadMyAnswer(UserInfo.uid).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ArrayList<Answer>>() {
//                    @Override
//                    public void accept(ArrayList<Answer> answers) throws Exception {
//                        mAdapter.setData(answers);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(MyAnswersActivity.this, "错误"+throwable, Toast.LENGTH_SHORT).show();
//                    }
//                });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAnswer getAnswer = retrofit.create(GetAnswer.class);
        Call<ArrayList<Answer>> call = getAnswer.loadMyAnswer(UserInfo.uid);
        call.enqueue(new Callback<ArrayList<Answer>>() {
            @Override
            public void onResponse(Call<ArrayList<Answer>> call, Response<ArrayList<Answer>> response) {
                mAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Answer>> call, Throwable t) {
                Toast.makeText(MyAnswersActivity.this, "错误"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
