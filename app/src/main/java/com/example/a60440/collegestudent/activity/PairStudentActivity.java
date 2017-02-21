package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.StudentInfoAdapter;
import com.example.a60440.collegestudent.bean.Student;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.utils.UserUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2017/2/21.
 */

public class PairStudentActivity extends AppCompatActivity implements MyItemClickListener{

    private User user;
    private ArrayList<Student> students;
    @Bind(R.id.rv_student)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toorbar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pair_student);
        ButterKnife.bind(this);
        toorbar.setTitle("配对界面");
        initView();
    }

    private void initView() {
        user = UserUtils.getParam(getApplicationContext());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //// TODO: 2017/2/21
//        StudentInfoAdapter studentAdapter = new StudentInfoAdapter(students,recyclerView.getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        studentAdapter.setOnItemClickListener(PairStudentActivity.this);
//        recyclerView.setAdapter(studentAdapter);

    }
    /*
    **this method used to pass a uid for requesting more infomation of a student who
    **has not been paired
    */
    @Override
    public void onItemClick(View view, int postion) {
        //// TODO: 2017/2/21
        Intent intent = new Intent(this,PersonalInfoActivity.class);
        intent.putExtra("uid","s"+students.get(postion).getId());
        startActivity(intent);

    }
}
