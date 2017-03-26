package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.AnswerAdapter;
import com.example.a60440.collegestudent.bean.Answer;
import com.example.a60440.collegestudent.bean.QuestionInfo;
import com.example.a60440.collegestudent.listener.MyItemClickListener;
import com.example.a60440.collegestudent.requestServes.GetAnswer;
import com.ms.square.android.expandabletextview.ExpandableTextView;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Created by 60440 on 2017/2/16.
 */

public class QuestionContentActivity extends Activity implements MyItemClickListener {

    @Bind(R.id.id_question_content)
    TextView questionTitle;
    @Bind(R.id.id_question_content_expamdanletextview)
    ExpandableTextView questionContent;
    @Bind(R.id.tv_discover_number)
    TextView answerNumber;
    @Bind(R.id.ib_answer)
    ImageView answer;
    @Bind(R.id.ib_share)
    ImageView share;
    QuestionInfo questionInfo;
    AnswerAdapter answerAdapter;
    RecyclerView recyclerView;
    ArrayList<Answer> answers;
    @OnClick(R.id.id_question_answer)
    void setAnswer(){
        Intent intent = new Intent(this,AddAnswerActivity.class);
        intent.putExtra("iid",Integer.parseInt(questionInfo.getId()));
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_content);
        ButterKnife.bind(this);
        recyclerView=(RecyclerView)findViewById(R.id.rc_answer);
        Intent intent = getIntent();
        questionInfo=(QuestionInfo)intent.getSerializableExtra("Question");
        Log.i("questioninfo",questionInfo.toString());
        initQues();
        questionTitle.setText(questionInfo.getTitle());
        questionContent.setText(questionInfo.getContent());
        answerNumber.setText(questionInfo.getAnswerNumber()+"人回答");
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShare();
            }
        });
    }

    private void initShare() {
        // intent.setType("text/plain"); //纯文本
            /*
             * 图片分享 it.setType("image/png"); 　//添加图片 File f = new
             * File(Environment.getExternalStorageDirectory()+"/name.png");
             *
             * Uri uri = Uri.fromFile(f); intent.putExtra(Intent.EXTRA_STREAM,
             * uri); 　
             */
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
    //    oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
     //   oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    //    oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
    private void initQues() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAnswer getanswer = retrofit.create(GetAnswer.class);
        Call<ArrayList<Answer>> call = getanswer.loadAnswers(Integer.parseInt(questionInfo.getId()));
        call.enqueue(new Callback<ArrayList<Answer>>() {
            @Override
            public void onResponse(Call<ArrayList<Answer>> call, Response<ArrayList<Answer>> response) {
                answers = response.body();
                Log.i("answer load:",answers.toString());
                if(answers==null){
                    Toast.makeText(QuestionContentActivity.this, "网络异常请重试", Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                    answerAdapter = new AnswerAdapter(answers,recyclerView.getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    answerAdapter.setOnItemClickListener(QuestionContentActivity.this);
                    answerAdapter.getActivity(QuestionContentActivity.this);
                    recyclerView.setAdapter(answerAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Answer>> call, Throwable t) {
                t.printStackTrace();
            }
        });




    }





    @Override
    public void onItemClick(View view, int postion) {

    }
}
