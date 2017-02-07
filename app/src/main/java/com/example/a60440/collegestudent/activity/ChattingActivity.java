package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.ChatMsgEntity;
import com.example.a60440.collegestudent.adapter.ChatMsgViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/12/8.
 */

public class ChattingActivity extends Activity{
    private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
    @Bind(R.id.listview)
    ListView mListView;
    @OnClick(R.id.btn_send)
    void setOnClickListener(){
        send();
    }
    @OnClick(R.id.id_chatting_imag_back)
    void setmBtnBackOnclick(){
        finish();
    }
    @Bind(R.id.et_sendmessage)
    EditText mEditTextContent;

    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_main);
        ButterKnife.bind(this);
        initData();// 初始化数据
        mListView.setSelection(mAdapter.getCount() - 1);
    }



    private String[] msgArray = new String[] {  "OK,搞起！！" ,"差点没钱请我吃饭","我什么时候说要请你吃饭","现在","哦哦"};

    private String[] dataArray = new String[] {
            "2017-01-07 18:57:33", "2017-01-07 18:56:32","2017-01-07 18:56:32","2017-01-07 18:56:32","2017-01-07 18:56:32"};
    private final static int COUNT = 5;// 初始化数组总数

    /**
     * 模拟加载消息历史，实际开发可以从数据库中读出
     */
    public void initData() {
        for (int i = 0; i < COUNT; i++) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(dataArray[i]);
            if (i % 2 == 0) {
                entity.setName("小绿");
                entity.setMsgType(true);// 收到的消息
            } else {
                entity.setName("小王");
                entity.setMsgType(false);// 自己发送的消息
            }
            entity.setMessage(msgArray[i]);
            mDataArrays.add(entity);
        }

        mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 发送消息
     */
    private void send() {
        String contString = mEditTextContent.getText().toString();
        if (contString.length() > 0) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setName("小王");
            entity.setDate(getDate());
            entity.setMessage(contString);
            entity.setMsgType(false);

            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变

            mEditTextContent.setText("");// 清空编辑框数据

            mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
        }
    }

    /**
     * 发送消息时，获取当前事件
     *
     * @return 当前时间
     */
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }
}
