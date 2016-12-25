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
 * Created by 60440 on 2016/12/8.
 */

public class FriendListAdapter extends RecyclerView.Adapter {
    private ArrayList<String> items;
    private ArrayList<String> times;
    private ArrayList<String> userNames;
    private ArrayList<Integer> ImageItems;
    private Context context;
    private MyItemClickListener myItemClickListener;
    private CardView cardView;

    public FriendListAdapter(ArrayList<String> items,ArrayList<Integer> imageItems,ArrayList<String> times,ArrayList<String> userNames,Context context) {
        this.items=items;
        this.context=context;
        this.times=times;
        this.userNames=userNames;
        this.ImageItems=imageItems;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FriendListHodler){
            FriendListHodler friendListHodler = (FriendListHodler)holder;
            friendListHodler.time.setText(times.get(position));
            friendListHodler.userName.setText(userNames.get(position));
            friendListHodler.textView.setText(items.get(position));
            friendListHodler.imageView.setImageResource(ImageItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public FriendListHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview03,parent,false);
        return new FriendListHodler(view,myItemClickListener);
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener=listener;
    }

    class FriendListHodler extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        ImageView imageView;
        TextView userName;
        TextView time;


        public FriendListHodler(View itemView,MyItemClickListener listener) {
            super(itemView);
            userName = (TextView)itemView.findViewById(R.id.base_swipe_item_title3);
            imageView = (ImageView)itemView.findViewById(R.id.base_swipe_item_imag3);
            time = (TextView)itemView.findViewById(R.id.id_friend_time);
            textView=(TextView)itemView.findViewById(R.id.id_friend_content);
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
