package com.example.a60440.collegestudent.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.listener.MyItemClickListener;

import java.util.ArrayList;

/**
 * Created by 60440 on 2016/11/27.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.NormalHolder> {
    private ArrayList<String> items;
    private Context context;
    private MyItemClickListener myItemClickListener;
    private CardView cardView;

    public VideoListAdapter(ArrayList<String> items,Context context){
        this.items=items;
        this.context=context;
    }

    @Override
    public void onBindViewHolder(NormalHolder holder, int position) {
        if(holder instanceof NormalHolder){
            NormalHolder normalHolder = (NormalHolder)holder;
            normalHolder.textView.setText(items.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
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
