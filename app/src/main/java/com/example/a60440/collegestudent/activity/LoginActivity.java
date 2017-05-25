package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.Info;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.requestServes.RequestServes;
import com.example.a60440.collegestudent.utils.MD5Utils;
import com.example.a60440.collegestudent.utils.UserUtils;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2016/11/27.
 */

public class LoginActivity extends Activity {
    private String userName = "";
    private String userpwd = "";

    @OnClick(R.id.button2)
    void loginOnClick() {
        userName = loginName.getText().toString();
        userpwd = loginpwd.getText().toString();
        userpwd=MD5Utils.Encode(userpwd);
        InitLogin(userName,userpwd);
    }

    @OnClick(R.id.tv_register)
    void registerOnClick() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @Bind(R.id.editText2)
    EditText loginName;
    @Bind(R.id.editText)
    EditText loginpwd;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        ButterKnife.bind(this);

        toolbar.setTitle("账号登录");
    }

    private void InitLogin(final String username, final String userpwd) {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServes requestServes = retorfit.create(RequestServes.class);
        Call<String> call = requestServes.getString(username.toString(), userpwd);
        call.enqueue(new Callback<String>() {
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("===", "fail");
            }

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("==", "return:" + username+" "+ userpwd);
                Gson gson = new Gson();
                User user = gson.fromJson(response.body(), User.class);
                if (user != null) {
                    UserInfo.id=user.getId();
                    UserInfo.imageURL=user.getImageURL();
                    UserInfo.nickname=user.getNickname();
                    UserInfo.token = user.getToken();
                    UserInfo.uid = "c"+user.getId();
                    UserInfo.signature=user.getSignature();
                    storeInfo(user.getUsername(),user.getPassword());
                    UserUtils.setParam(getApplicationContext(),user);
                    connect(user.getToken());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);
                } else {
                    //something to do
                    Toast.makeText(LoginActivity.this, "网络异常，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void storeInfo(String username, String password) {
        SharedPreferences.Editor info = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();
        info.putString("username",username);
        info.putString("password",password);
        info.putBoolean("needlogin",false);
        info.commit();
    }




    private void connect(String token) {
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public io.rong.imlib.model.UserInfo getUserInfo(String uid) {
                return findUserByUid(uid);
            }
        },true);
        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
                Intent intent = new Intent(context, PersonalInfoActivity.class);
                intent.putExtra("uid",info.getUserId());
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });

        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {

            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                Log.d("LoginActivity", "--onSuccess" + userid);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

    }
    private io.rong.imlib.model.UserInfo findUserByUid(String uid) {
        try {

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.baseURL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final RequestServes requestServes = retrofit.create(RequestServes.class);
            final Call<Info> call=requestServes.getUserInfo(uid,UserInfo.uid);
            Response<Info> response = call.execute();
            Info info = response.body();
            io.rong.imlib.model.UserInfo userInfo=null;
            Log.i("userinfo", "findUserByUid: "+info);
            if (info!=null)
                userInfo = new io.rong.imlib.model.UserInfo(info.getUid(), info.getNickname(), Uri.parse(getResources().getString(R.string.baseURL) + info.getImageURL()));
            return userInfo;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}