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
import com.example.a60440.collegestudent.bean.Student;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.loader.NormalImageLoader;

import java.util.ArrayList;

/**
 * Created by 60440 on 2017/2/21.
 */

public class StudentInfoAdapter extends RecyclerView.Adapter{
    private Context context;
    private MyItemClickListener myItemClickListener;
    private ArrayList<Student> students;
    public StudentInfoAdapter(ArrayList<Student> students,Context context){
        this.students=students;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_studentinfo,parent,false);
        return new StudentHolder(view,myItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof StudentHolder){
            StudentHolder studentHolder = (StudentHolder)holder;
            Student student = students.get(position);
            studentHolder.studentname.setText(student.getNickname());
            studentHolder.studentInfo.setText(student.getSignature());
            new NormalImageLoader().getPicture(context.getResources().getString(R.string.baseURL)+student.getImageURL(),studentHolder.imageView);


        }
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView studentname;
        TextView studentInfo;

        public StudentHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            studentname=(TextView)itemView.findViewById(R.id.tv_student_name);
            studentInfo=(TextView)itemView.findViewById(R.id.tv_student_info);
            imageView=(ImageView)itemView.findViewById(R.id.iv_student_image);
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
