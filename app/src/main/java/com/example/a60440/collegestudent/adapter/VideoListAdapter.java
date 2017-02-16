package com.example.a60440.collegestudent.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.bean.VideoInfo;

import java.util.ArrayList;

import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by 60440 on 2016/11/27.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.NormalHolder> {
    private ArrayList<VideoInfo> videos;
    private Context context;
    private MyItemClickListener myItemClickListener;
    private CardView cardView;


    public VideoListAdapter(ArrayList<VideoInfo> videoInfo,Context context){
        this.context=context;
        videos=videoInfo;
    }

    @Override
    public void onBindViewHolder(NormalHolder holder, int position) {
        if(holder instanceof NormalHolder){

            NormalHolder normalHolder = (NormalHolder)holder;
            normalHolder.textView.setText(videos.get(position).videoTitle);
           // new NormalImageLoader().getPicture(videos.get(position).videoImage,normalHolder.imageView);
            Log.i("video url is",videos.get(position).videoUrl);
            FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
            mmr.setDataSource(videos.get(position).videoUrl);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
            Bitmap bitmap = mmr.getFrameAtTime(20000000,FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
            normalHolder.imageView.setImageBitmap(bitmap);
            mmr.release();
//            normalHolder.imageView


        }

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    @Override
    public NormalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview02,parent,false);
        return new NormalHolder(view,myItemClickListener);
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener = listener;
    }

    class NormalHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        ImageView imageView;

        public NormalHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.base_swipe_item_title2);
            imageView=(ImageView)itemView.findViewById(R.id.id_video_iamge);
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
