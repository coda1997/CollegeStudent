package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.SettingActivity;
import com.example.a60440.collegestudent.configuration.BaseConfiguration;
import com.example.a60440.collegestudent.loader.NormalImageLoader;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personfile,container,false);
        ButterKnife.bind(this,v);
        initData();
        return v;
    }

    private void initData() {
        new NormalImageLoader().getPicture(BaseConfiguration.imagesUrl,imageView);
    }
    private void selectFragment(int id){
        if (getActivity() instanceof SettingActivity){
            ((SettingActivity)getActivity()).setSelect(id);
        }
    }

}
