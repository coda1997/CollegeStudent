package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a60440.collegestudent.R;

/**
 * Created by 60440 on 2016/12/7.
 */

public class VedioManegerActivity extends Activity implements View.OnClickListener{
    private Button vedioAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_maneger);
        InitView();
    }

    private void InitView() {
        vedioAddButton=(Button)findViewById(R.id.id_vedio_add);
        vedioAddButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.id_vedio_add){
            Log.i("add vedio","succeed");
        }
    }
}
