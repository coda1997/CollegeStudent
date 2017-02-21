package com.example.a60440.collegestudent.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.VedioManegerActivity;
import com.example.a60440.collegestudent.bean.Answer;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.bean.VideoInfo;
import com.example.a60440.collegestudent.listener.OnDeleteClickListener;
import com.example.a60440.collegestudent.listener.OnRecyclerViewItemClickListener;
import com.example.a60440.collegestudent.requestServes.VideoServes;
import com.example.a60440.collegestudent.utils.UiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by 60440 on 2017/2/8.
 */

public class VideoManagementAdapter extends RecyclerView.Adapter {
    private ArrayList<VideoInfo> videos;
    private MyItemClickListener myItemClickListener;
    private Activity activity;
    private Context context;

    public VideoManagementAdapter(ArrayList<VideoInfo> videoInfos, Context context){
        videos=videoInfos;
        this.context=context;
    }

    public void getActivity(Activity activity){
        this.activity=activity;
    }

    @Override
    public VideoManagementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_video_management,parent,false);

        return new VideoManagementHolder(view,myItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof VideoManagementHolder){
            VideoManagementHolder videoManagementHolder = (VideoManagementHolder) holder;
            final VideoInfo videoInfo = videos.get(position);
            videoManagementHolder.videoName.setText(videoInfo.videoTitle);
            Log.i("video manage",videoInfo.toString());
            //new NormalImageLoader().getPicture(videos.get(position).videoImage,videoManagementHolder.videoImg);
            FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
            mmr.setDataSource(context.getResources().getString(R.string.baseURL)+ videoInfo.videoUrl);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
            Bitmap bitmap = mmr.getFrameAtTime(10000,FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
//            byte[] artwork = mmr.getEmbeddedPicture();
            videoManagementHolder.videoImg.setImageBitmap(bitmap);
            mmr.release();
            videoManagementHolder.deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(context.getResources().getString(R.string.baseURL))
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    VideoServes videoServes = retrofit.create(VideoServes.class);
                    Call<String> call = videoServes.deleteVideo(videoInfo.videoId+"",videoInfo.videoUrl);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                            remove(videoInfo.videoId);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context, "删除失败"+t, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }
    }
    public void remove(int id){
        for (VideoInfo videoInfo : videos) {
            if (videoInfo.videoId==id)
                videos.remove(videoInfo);
        }
        UiUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener=listener;
    }

    class VideoManagementHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView videoName;
        ImageView videoImg;
        TextView videoSize;
        ImageView deleteImageView;

        public VideoManagementHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            videoName=(TextView)itemView.findViewById(R.id.base_swipe_item_title4);
            videoImg=(ImageView)itemView.findViewById(R.id.base_swipe_item_imag_video_management);
            videoSize=(TextView)itemView.findViewById(R.id.id_vedio_management_size);
            deleteImageView=(ImageView) itemView.findViewById(R.id.iv_delete_video);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(myItemClickListener!=null){
                myItemClickListener.onItemClick(v,getPosition());
            }

        }


    }
}
