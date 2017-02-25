package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.StudentInfoAdapter;
import com.example.a60440.collegestudent.bean.Student;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.requestServes.ContantServes;
import com.example.a60440.collegestudent.utils.UserUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2017/2/25.
 */

public class UnpairStudentActivity extends AppCompatActivity implements MyItemClickListener{
    private User user;
    private ArrayList<Student> students;
    @Bind(R.id.rv_student)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toorbar;
    private int start = 0;
    @Bind(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pair_student);
        ButterKnife.bind(this);
        toorbar.setTitle("配对学生");
        setSupportActionBar(toorbar);
        initView();
        ininTwkingView();
    }
    private void ininTwkingView(){
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                start+=10;
                initView();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

//            @Override
//            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                super.onLoadMore(refreshLayout);
//            }
        });


    }
    private void initView() {
        user = UserUtils.getParam(getApplicationContext());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<ArrayList<Student>> call = contantServes.getUnpairStudent(start);
        call.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                ArrayList<Student> unpairStudents = response.body();
                if(unpairStudents!=null){
                    students=unpairStudents;
                    StudentInfoAdapter studentAdapter = new StudentInfoAdapter(students,recyclerView.getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    studentAdapter.setOnItemClickListener(UnpairStudentActivity.this);
                    recyclerView.setAdapter(studentAdapter);
                }else {
                    Toast.makeText(UnpairStudentActivity.this, "已更新", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                Toast.makeText(UnpairStudentActivity.this, "网络异常，请重试"+t, Toast.LENGTH_SHORT).show();
                Log.i("unpair student",t.toString());
            }
        });


        //// TODO: 2017/2/21


    }
    /*
    **this method used to pass a uid for requesting more infomation of a student who
    **has not been paired
    */
    @Override
    public void onItemClick(View view, int postion) {
        //// TODO: 2017/2/21
        Intent intent = new Intent(this,StudentDetailActivity.class);
        intent.putExtra("uid","s"+students.get(postion).getId());
        startActivity(intent);

    }
}
