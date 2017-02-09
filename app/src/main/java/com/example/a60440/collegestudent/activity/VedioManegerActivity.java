package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a60440.collegestudent.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/12/7.
 */

public class VedioManegerActivity extends Activity{
    @OnClick(R.id.id_vedio_add)
    void setOnClickListener(View view){
        int id = view.getId();
        if(id==R.id.id_vedio_add){
            Log.i("add vedio","succeed");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_maneger);
        ButterKnife.bind(this);
    }

}
