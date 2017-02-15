package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.VideoManagementAdapter;
import com.example.a60440.collegestudent.configuration.BaseConfiguration;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.utils.FriendsInfo;
import com.example.a60440.collegestudent.utils.VideoInfo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/12/7.
 */

public class VedioManegerActivity extends Activity implements MyItemClickListener{
    private VideoManagementAdapter videoManagementAdapter;
    private ArrayList<VideoInfo> videos;
    private final String imageUrl = BaseConfiguration.imagesUrl;

    @Nullable
    @OnClick(R.id.id_vedio_add)
    void setOnClickListener(){
        Intent intent = new Intent(VedioManegerActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Bind(R.id.id_RecyclerView_video_manegement)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_maneger_full);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if(recyclerView==null)
            Log.i("recyclerView","is null");
        else
            Log.i("recyclerVIew","is not null");
        initDatas();
        videoManagementAdapter = new VideoManagementAdapter(videos,recyclerView.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        videoManagementAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(videoManagementAdapter);
    }

    private void initDatas(){
        videos = new ArrayList<>();
        videos.add(initDatas("haa",imageUrl));

    }
    private VideoInfo initDatas(String name,String url){
        VideoInfo video = new VideoInfo();
        video.videoTitle=name;
        video.videoUrl=url;
        return video;
    }
    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(this,VideoActivity.class);
        intent.putExtra("videoUrl","www.baidu.com");
        startActivity(intent);
    }

}
