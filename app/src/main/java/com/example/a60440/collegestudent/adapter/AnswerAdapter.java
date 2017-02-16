package com.example.a60440.collegestudent.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.bean.AnswerInfo;
import com.example.a60440.collegestudent.bean.QuestionInfo;

import java.util.ArrayList;

/**
 * Created by 60440 on 2017/2/13.
 */

public class AnswerAdapter extends RecyclerView.Adapter {
    private Context context;
    private MyItemClickListener myItemClickListener;
    private Activity activity;
    private ArrayList<AnswerInfo> answers;

    public AnswerAdapter(ArrayList<AnswerInfo> answers,Context context){
        this.answers=answers;
        this.context=context;

    }
    public void getActivity(Activity activity){
        this.activity=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_answer,parent,false);

        return new AnswerHolder(view,myItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AnswerHolder){
            AnswerHolder answerHolder = (AnswerHolder)holder;
//// TODO: 2017/2/16
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener = listener;
    }


    class AnswerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView contentTextView;
        TextView likeNumTextView;
        TextView timeTextView;
        ImageView answerImageView;
        TextView answerNameTextView;
//        public String answerContent;
//        public String peopleNum;
//        public String userImage;
//        public String userName;
//        public String likeNum;
        public AnswerHolder(View itemView, MyItemClickListener listener){
            super(itemView);
            contentTextView=(TextView)itemView.findViewById(R.id.tv_answer_content);
            likeNumTextView=(TextView)itemView.findViewById(R.id.tv_agree);
            timeTextView=(TextView)itemView.findViewById(R.id.tv_time);
            answerNameTextView = (TextView)itemView.findViewById(R.id.tv_answer_name);
            answerImageView=(ImageView)itemView.findViewById(R.id.iv_answer_image);



        }

        @Override
        public void onClick(View v) {

        }
    }

}
