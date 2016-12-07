package com.example.a60440.collegestudent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.SettingActivity;

/**
 * Created by 60440 on 2016/11/30.
 */

public class SettingMainFragment extends Fragment implements View.OnClickListener{
    private LinearLayout personFileLinerLayout;
    private LinearLayout personSignLinerLayout;
    private LinearLayout notificationLinerLayout;
    private LinearLayout sideFunctionLinerLayout;
    private SettingActivity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_main,container,false);
        InitView(v);
        return v;
    }

    private void InitView(View v) {
        personFileLinerLayout = (LinearLayout)v.findViewById(R.id.id_setting_personfile);
        personFileLinerLayout.setOnClickListener(this);
        personSignLinerLayout=(LinearLayout)v.findViewById(R.id.id_setting_personalizesignature);
        personSignLinerLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(getActivity() instanceof SettingActivity){
            activity = (SettingActivity) getActivity();
            if(id==R.id.id_setting_personfile){
                activity.setSelect(1);
            }else if(id==R.id.id_setting_personalizesignature){
                activity.setSelect(0);
            }

            //add other fragment select;
        }
    }

}
