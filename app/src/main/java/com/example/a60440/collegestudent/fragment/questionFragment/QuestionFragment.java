package com.example.a60440.collegestudent.fragment.questionFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.example.a60440.collegestudent.activity.MainActivity;
import com.example.a60440.collegestudent.configuration.BaseConfiguration;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.adapter.QuesListAdapter;
import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.utils.QuestionInfo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 60440 on 2016/11/20.
 */

public class QuestionFragment extends Fragment implements MyItemClickListener {
    private QuesListAdapter quesListAdapter;
    private final String imageUrl= BaseConfiguration.imagesUrl;

    @Bind(R.id.id_RecyclerView)
    RecyclerView recyclerView;

    private ArrayList<QuestionInfo> questions;
    private final String TAG_1 = "OnClick";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab01,container,false);
        ButterKnife.bind(this,v);
        initRecyclerView(v);
        return v;
    }

    private void initRecyclerView(View v){
        if(recyclerView==null){
            Log.i(TAG_1,"bad mess");
            return;
        }
        initData();
        quesListAdapter = new QuesListAdapter(questions,recyclerView.getContext());
        quesListAdapter.getActivity(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        quesListAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(quesListAdapter);
    }
    private void initData(){
        questions=new ArrayList<>();

        initData("大学生有怎么样的生活啊","10月10日","1111人","来自三年二班",imageUrl,"周铠");
        initData("有父母陪是怎么样的","11月13日","643人","来自五年一班",imageUrl,"宋惠");
        initData("大哥哥，这道题该怎么做？","12月01日","24人","来自四年一班",imageUrl,"王光曜");
        initData("有时候很想在外地的父母，大姐姐可以告诉我，应该怎么做吗？","12月09日","63人","来自五年一班",imageUrl,"谢柱坤");
        initData("这些题目好难啊，都不想做","11:02","382人","来自二年四班",imageUrl,"江茹");
        initData("大哥哥，大学生活美好吗？","11:06","39人","来自五年六班",imageUrl,"王学舟");

    }
        private void initData(String data,String time,String peopleNum, String className, String id,String name){
           QuestionInfo questionInfo = new QuestionInfo();
            questionInfo.itemName=data;
            questionInfo.itemTime=time;
            questionInfo.peopleNum=peopleNum;
            questionInfo.itemClass=className;
            questionInfo.userImage=id;
            questionInfo.userName=name;
            questions.add(questionInfo);
        }
    @Override
    public void onItemClick(View view, int postion) {
        Log.i(TAG_1," succeed "+postion);

        if(this.getActivity() instanceof MainActivity){
            MainActivity mainActivity = (MainActivity) this.getActivity();
            mainActivity.setSelect(4);
        }
    }

}
