package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.SettingActivity;

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
        if(getActivity() instanceof SettingActivity){
            ((SettingActivity)getActivity()).setSelect(3);
        }
    }
    @OnClick(R.id.id_setting_personalfile_signature)
    void setPersonalSignatureOnClickListener(){
        if (getActivity() instanceof SettingActivity){
            ((SettingActivity)getActivity()).setSelect(2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personfile,container,false);
        ButterKnife.bind(this,v);
        return v;
    }
}
