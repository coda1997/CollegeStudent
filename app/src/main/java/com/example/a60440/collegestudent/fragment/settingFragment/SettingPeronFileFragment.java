package com.example.a60440.collegestudent.fragment.settingFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.SettingActivity;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.configuration.BaseConfiguration;
import com.example.a60440.collegestudent.loader.NormalImageLoader;
import com.example.a60440.collegestudent.utils.UserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/11/30.
 */

public class SettingPeronFileFragment extends Fragment {
    private final int currentFragmentId = 1;
    public int getCurrentFragmentId() {
        return currentFragmentId;
    }

    @OnClick(R.id.id_setting_personalfile_introduction)
    void setPersonalIntroductionOnClickListener(){
        selectFragment(3);
    }
    @OnClick(R.id.id_setting_personalfile_signature)
    void setPersonalSignatureOnClickListener(){
        selectFragment(2);
    }
    @OnClick(R.id.setting_nickname)
    void setNickName(){
        selectFragment(4);
    }
    @OnClick(R.id.setting_region)
    void setRegion(){
        selectFragment(5);
    }
    @OnClick(R.id.setting_gender)
    void setGender(){
        selectFragment(6);
    }
    @OnClick(R.id.setting_major)
    void setMajor(){
        selectFragment(7);
    }
    @OnClick(R.id.setting_school)
    void setSchool(){
        selectFragment(8);
    }

    @Bind(R.id.id_setting_personfile_image)
    ImageView imageView;
    @Bind(R.id.id_setting_textview_nickname)
    TextView nicknameTextView;
    @Bind(R.id.id_setting_textview_gender)
    TextView genderTextView;
    @Bind(R.id.id_setting_textview_region)
    TextView regionTextView;
    @Bind(R.id.id_setting_textview_major)
    TextView majorTextView;
    @Bind(R.id.id_setting_textview_school)
    TextView schoolTextView;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personfile,container,false);
        ButterKnife.bind(this,v);
        initData(getContext());
        return v;
    }

    public void initData(Context context) {
        new NormalImageLoader().getPicture(BaseConfiguration.imagesUrl,imageView);
        user = UserUtils.getParam(context);
        Log.i("User information","=="+user.toString());
        if(user.getNickname()!=null)
            nicknameTextView.setText(user.getNickname());
        if(user.getGender()!=null)
            genderTextView.setText(user.getGender());
        if(user.getRegion()!=null)
            regionTextView.setText(user.getRegion());
        if(user.getSchool()!=null)
            schoolTextView.setText(user.getSchool());
        if(user.getMajor()!=null)
            majorTextView.setText(user.getMajor());


    }
    private void selectFragment(int id){
        if (getActivity() instanceof SettingActivity){
            ((SettingActivity)getActivity()).setSelect(id);
        }
    }

    @Override
    public void onResume() {
        initData(getContext());
        Log.i("gender:=",user.getGender());
        super.onResume();
    }
}
