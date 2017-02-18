package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.InfoDetail;
import com.example.a60440.collegestudent.bean.Result;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.configuration.Constant;
import com.example.a60440.collegestudent.requestServes.ContantServes;
import com.example.a60440.collegestudent.transform.GlideCircleTransform;
import com.example.a60440.collegestudent.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class PersonalInfoActivity extends AppCompatActivity {

    @Bind(R.id.iv_person_info_photo)
    ImageView mIvPersonInfoPhoto;
    @Bind(R.id.tv_person_info_name)
    TextView mTvPersonInfoName;
    @Bind(R.id.tv_person_info_gender)
    TextView mTvPersonInfoGender;
    @Bind(R.id.tv_person_info_rank)
    TextView mTvPersonInfoRank;
    @Bind(R.id.tv_person_info_region)
    TextView mTvPersonInfoRegion;
    @Bind(R.id.tv_person_info_signature)
    TextView mTvPersonInfoSignature;
    @Bind(R.id.tv_person_info_phone)
    TextView mTvPersonInfoPhone;
    @Bind(R.id.tv_person_info_email)
    TextView mTvPersonInfoEmail;
    @Bind(R.id.tv_person_info_identity)
    TextView mTvPersonInfoIdentity;
    @Bind(R.id.tv_person_info_school)
    TextView mTvPersonInfoSchool;

    private String uid;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        uid = getIntent().getStringExtra("uid");
        initData();
    }

    private void initData() {
//        RetrofitUtils.getService().getDetailedInfo(uid).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<InfoDetail>() {
//                    @Override
//                    public void accept(InfoDetail detail) throws Exception {
//                        populateInfo(detail);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(PersonalInfoActivity.this, "链接失败" + throwable, Toast.LENGTH_SHORT).show();
//                    }
//                });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<InfoDetail> call = contantServes.getDetailedInfo(uid);
        call.enqueue(new Callback<InfoDetail>() {
            @Override
            public void onResponse(Call<InfoDetail> call, Response<InfoDetail> response) {
                populateInfo(response.body());

            }

            @Override
            public void onFailure(Call<InfoDetail> call, Throwable t) {
                Toast.makeText(PersonalInfoActivity.this, "链接失败" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateInfo(InfoDetail detail) {
        nickname = detail.getNickname();
        setText(mTvPersonInfoName, nickname);
        setText(mTvPersonInfoEmail, detail.getEmail());
        setText(mTvPersonInfoGender, detail.getGender());
        setText(mTvPersonInfoPhone, detail.getPhone());
        setText(mTvPersonInfoRegion, detail.getRegion());
        setText(mTvPersonInfoSignature, detail.getSignature());
        setText(mTvPersonInfoIdentity, UserInfo.getIdentity(uid));
        setText(mTvPersonInfoSchool, detail.getSchool());
        Glide.with(UiUtils.getContext()).load(Constant.SERVER_URL + detail.getImageURL())
                .placeholder(R.drawable.default_photo)
                .transform(new GlideCircleTransform(this))
                .into(mIvPersonInfoPhoto);
    }

    private void setText(TextView textView, String text) {
        if (TextUtils.isEmpty(text))
            textView.setText("未填写");
        else
            textView.setText(text);
    }

    @OnClick(R.id.bt_chat)
    public void onClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW, UserInfo.getPrivateChatUri(uid,nickname));
        startActivity(intent);
    }

}
