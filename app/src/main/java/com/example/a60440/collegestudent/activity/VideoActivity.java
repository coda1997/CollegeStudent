package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.a60440.collegestudent.R;

/**
 * Created by 60440 on 2016/11/29.
 */

public class VideoActivity extends Activity {
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_main);
        InitView();
    }

    private void InitView() {
        videoView = (VideoView)findViewById(R.id.id_videoView_main);

    }
}
