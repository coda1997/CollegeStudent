package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.a60440.collegestudent.R;
import com.github.mikephil.charting.charts.LineChart;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 60440 on 2017/2/23.
 */

public class StudentInfoActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    //initialize two line charts about number of the student's joining class
    @Bind(R.id.chart_overall)
    LineChart overallLineChart;
    @Bind(R.id.chart_detail)
    LineChart detailLineChart;
    private String uid=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        ButterKnife.bind(this);
        toolbar.setTitle("详细信息");
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        initData();
    }

    private void initData() {
        // TODO: 2017/2/25
        //here something about a student's information is needed for two charts.


    }
}
