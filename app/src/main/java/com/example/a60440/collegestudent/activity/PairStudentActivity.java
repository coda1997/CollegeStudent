package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.StudentInfoAdapter;
import com.example.a60440.collegestudent.bean.Student;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.requestServes.ContantServes;
import com.example.a60440.collegestudent.utils.UserUtils;

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
        toorbar.setTitle("关心界面");
        setSupportActionBar(toorbar);
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_add_pair_student){
            Intent intent = new Intent(this,UnpairStudentActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_pair_student,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        user = UserUtils.getParam(getApplicationContext());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<ArrayList<Student>> call = contantServes.getStudentInfo(UserInfo.id+"");
        call.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                ArrayList<Student> unpairStudents = response.body();
                if(unpairStudents!=null){
                    students=unpairStudents;
                    StudentInfoAdapter studentAdapter = new StudentInfoAdapter(students,recyclerView.getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    studentAdapter.setOnItemClickListener(PairStudentActivity.this);
                    recyclerView.setAdapter(studentAdapter);
                }else {
                    Toast.makeText(PairStudentActivity.this, "学生获取失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                Toast.makeText(PairStudentActivity.this, "网络异常，请重试"+t, Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this,StudentInfoActivity.class);
        intent.putExtra("uid","s"+students.get(postion).getId());
        startActivity(intent);

    }
}
