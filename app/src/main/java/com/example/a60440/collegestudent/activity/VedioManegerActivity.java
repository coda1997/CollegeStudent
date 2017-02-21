package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.VideoManagementAdapter;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.configuration.BaseConfiguration;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.bean.VideoInfo;
import com.example.a60440.collegestudent.requestServes.VideoServes;
import com.example.a60440.collegestudent.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    }

    private void initDatas(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VideoServes videoServes = retrofit.create(VideoServes.class);
        Call<List<VideoInfo>> call = videoServes.getString(UserUtils.getParam(getApplicationContext()).getId());
        call.enqueue(new Callback<List<VideoInfo>>() {
            @Override
            public void onResponse(Call<List<VideoInfo>> call, Response<List<VideoInfo>> response) {
                Log.i("getmyvideo","success");
                if(response==null)
                    return;
                for(VideoInfo videoInfo:response.body()){
                    videos.add(videoInfo);
                }
//                videoManagementAdapter = new VideoManagementAdapter(videos,recyclerView.getContext());
//                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//                videoManagementAdapter.setOnItemClickListener(VedioManegerActivity.this);
//                recyclerView.setAdapter(videoManagementAdapter);
            }

            @Override
            public void onFailure(Call<List<VideoInfo>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(this,VideoActivity.class);
        intent.putExtra("videoUrl",videos.get(postion).videoUrl);
        startActivity(intent);
    }

}
