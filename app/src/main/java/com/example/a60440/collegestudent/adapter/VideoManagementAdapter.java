package com.example.a60440.collegestudent.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.os.CancellationSignal;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.loader.NormalImageLoader;
import com.example.a60440.collegestudent.utils.VideoInfo;

import java.util.ArrayList;

/**
 * Created by 60440 on 2017/2/8.
 */

public class VideoManagementAdapter extends RecyclerView.Adapter {
    private ArrayList<VideoInfo> videos;
    private Context context;
    private MyItemClickListener myItemClickListener;
    private Activity activity;

    public VideoManagementAdapter(ArrayList<VideoInfo> videoInfos){
        videos=videoInfos;

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
            videoManagementHolder.videoName.setText(videos.get(position).videoName);
            new NormalImageLoader().getPicture(videos.get(position).videoImage,videoManagementHolder.videoImg);

        }
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

        public VideoManagementHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            videoName=(TextView)itemView.findViewById(R.id.base_swipe_item_title4);
            videoImg=(ImageView)itemView.findViewById(R.id.base_swipe_item_imag_video_management);
            videoSize=(TextView)itemView.findViewById(R.id.id_vedio_management_size);
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
