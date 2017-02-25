package com.example.a60440.collegestudent.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.Info;
import com.example.a60440.collegestudent.bean.Result;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.configuration.Constant;
import com.example.a60440.collegestudent.requestServes.RequestServes;
import com.example.a60440.collegestudent.utils.UserUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.fl_container)
    FrameLayout flContainer;
    AlphaAnimation showLogo;
    TranslateAnimation showTitleLayout, showTitleView;
    private boolean animationEnd;
    private boolean requestEnd;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent;
            switch (msg.what) {
                case 0:
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.splash_enter,R.anim.splash_exit);
                    break;
                case 1:
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.splash_enter,R.anim.splash_exit);
                    break;
            }
        }
    };
    private boolean needLogin;
    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mPreferences = getSharedPreferences(Constant.PREFERENCE_USERINFO, MODE_PRIVATE);
        needLogin = mPreferences.getBoolean(Constant.KEY_NEED_LOGIN, true);
        if (!needLogin)
            sendRequest();
        else requestEnd = true;
        startAnimation();
        initDB();
    }

    private void sendRequest() {
//        String username = mPreferences.getString("username", "");
//        String password = mPreferences.getString("password", "");
//        RetrofitUtils.getService().login(username, password).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        if (result.getCode() == 0) {
//                            UserInfo.imageURL = result.getImageURL();
//                            UserInfo.nickname = result.getNickname();
//                            UserInfo.id = result.getId();
//                            UserInfo.uid = "s" + UserInfo.id;
//                            UserInfo.token = result.getToken();
//                            UserInfo.signiture = result.getSigniture();
//                            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                                @Override
//                                public io.rong.imlib.model.UserInfo getUserInfo(String uid) {
//                                    return UserInfo.findUserByUid(uid);
//                                }
//                            }, true);
//                            RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
//                                @Override
//                                public boolean onUserPortraitClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
//                                    Intent intent = new Intent(context, PersonalInfoActivity.class);
//                                    intent.putExtra("uid", info.getUserId());
//                                    startActivity(intent);
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onMessageClick(Context context, View view, io.rong.imlib.model.Message message) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onMessageLinkClick(Context context, String s) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onMessageLongClick(Context context, View view, io.rong.imlib.model.Message message) {
//                                    return false;
//                                }
//                            });
//                            connect();
//                        } else {
//                            new AlertDialog.Builder(SplashActivity.this)
//                                    .setMessage("登录失败:" + result.getMessage())
//                                    .setNegativeButton("确定", null).create().show();
//                            requestEnd = true;
//                            handler.sendEmptyMessageDelayed(0,500);
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(SplashActivity.this, "登录失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                        requestEnd = true;
//                        handler.sendEmptyMessageDelayed(0,500);
//                    }
//                });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RequestServes requestServes = retrofit.create(RequestServes.class);
        User user = UserUtils.getParam(getApplicationContext());
        Call<String> call = requestServes.getString(user.getUsername(),user.getPassword());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                User user1 = new Gson().fromJson(response.body(),User.class);
                if (user1!=null) {

                    UserInfo.imageURL = user1.getImageURL();
                    UserInfo.nickname = user1.getNickname();
                    UserInfo.id = user1.getId();
                    UserInfo.uid = "c" + UserInfo.id;
                    UserInfo.token = user1.getToken();
                    if(UserInfo.token==null){
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                        return;
                    }
                    UserInfo.signiture = user1.getSignature();
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public io.rong.imlib.model.UserInfo getUserInfo(String uid) {
                            return findUserByUid(uid);
                        }
                    }, true);
                    RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
                        @Override
                        public boolean onUserPortraitClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
                            Intent intent = new Intent(context, PersonalInfoActivity.class);
                            intent.putExtra("uid", info.getUserId());
                            startActivity(intent);
                            return false;
                        }

                        @Override
                        public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
                            return false;
                        }

                        @Override
                        public boolean onMessageClick(Context context, View view, io.rong.imlib.model.Message message) {
                            return false;
                        }

                        @Override
                        public boolean onMessageLinkClick(Context context, String s) {
                            return false;
                        }

                        @Override
                        public boolean onMessageLongClick(Context context, View view, io.rong.imlib.model.Message message) {
                            return false;
                        }
                    });
                    connect();
                } else {
                    new AlertDialog.Builder(SplashActivity.this)
                            .setMessage("登录失败:" + "fail")
                            .setNegativeButton("确定", null).create().show();
                    requestEnd = true;
                    handler.sendEmptyMessageDelayed(0,500);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "登录失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
                requestEnd = true;
                handler.sendEmptyMessageDelayed(0,500);
            }
        });

    }

    public void connect() {

        RongIM.connect(UserInfo.token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                requestForToken();
            }

            @Override
            public void onSuccess(String s) {
                requestEnd = true;
                if (animationEnd)
                    sendMessage();
            }

            @Override
            public void onError(RongIMClient.ErrorCode code) {
                Toast.makeText(SplashActivity.this, "登录失败" + code.getMessage(), Toast.LENGTH_SHORT).show();
                requestEnd = true;
                handler.sendEmptyMessageDelayed(0,500);
            }
        });
    }


    private void requestForToken() {
//        RetrofitUtils.getService().requestToken(UserInfo.uid)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Consumer<Result>() {
//                               @Override
//                               public void accept(Result result) throws Exception {
//                                   if (result.getCode() == 0) {
//                                       UserInfo.token = result.getToken();
//                                       connect();
//                                   } else {
//                                       new AlertDialog.Builder(SplashActivity.this).setMessage("错误:" + result.getMessage()).show();
//                                   }
//
//                               }
//                           }, new Consumer<Throwable>() {
//                               @Override
//                               public void accept(Throwable throwable) throws Exception {
//                                    new AlertDialog.Builder(SplashActivity.this).setMessage("错误:" + throwable.toString()).show();
//                               }
//                           }
//                );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServes requestServes = retrofit.create(RequestServes.class);
        Call<Result> call = requestServes.requestToken(UserInfo.uid);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getCode() == 0) {
                    UserInfo.token = response.body().getToken();
                    connect();
                } else {
                    new AlertDialog.Builder(SplashActivity.this).setMessage("错误:" + response.body().getMessage()).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                new AlertDialog.Builder(SplashActivity.this).setMessage("错误:" + t.toString()).show();

            }
        });

    }

    private void startAnimation() {
        showLogo = new AlphaAnimation(0, 1);
        showLogo.setDuration(800);
        showTitleLayout = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        showTitleLayout.setDuration(800);
        showTitleView = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        showTitleView.setDuration(800);
        showTitleView.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationEnd = true;
                if (requestEnd)
                    sendMessage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        showLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                flContainer.setVisibility(View.VISIBLE);
                flContainer.startAnimation(showTitleLayout);
                tvTitle.startAnimation(showTitleView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivLogo.startAnimation(showLogo);
    }

    private void sendMessage() {
        handler.sendEmptyMessageDelayed(needLogin ? 0 : 1, 500);
    }


    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private io.rong.imlib.model.UserInfo findUserByUid(String uid) {
        try {

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.baseURL))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final RequestServes requestServes=retrofit.create(RequestServes.class);
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
    private void initDB() {
        InputStream stream = null;
        FileOutputStream outputStream = null;
        try {
            String fileName = "region.db";
            File file = new File(getFilesDir(), fileName);
            if (file.exists())
                return;
            stream = getAssets().open(fileName);
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = stream.read(buffer)) != -1)
                outputStream.write(buffer, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null)
                    stream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Toast.makeText(this, "拷贝完成", Toast.LENGTH_SHORT).show();
    }

}
