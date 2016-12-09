package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.a60440.collegestudent.R;

import butterknife.BindView;

public class VideoActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_main);
        initView();
    }

    private void initView() {

    }
}