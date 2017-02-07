package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.a60440.collegestudent.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/12/8.
 */

public class QuestionContentActivity extends Activity {
    private ImageButton backButton;
    @OnClick(R.id.id_BackButton)
    void setBackButton(){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quesbar01);
        ButterKnife.bind(this);
    }

}
