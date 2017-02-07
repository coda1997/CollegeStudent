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

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/11/30.
 */

public class SettingMainFragment extends Fragment{

    @OnClick(R.id.id_setting_personfile)
    void setPersonfileOnClickListener(){
        if(getActivity() instanceof SettingActivity){
            ((SettingActivity) getActivity()).setSelect(1);
        }
    }

    @OnClick(R.id.id_setting_personalizesignature)
    void setPersonalizsinatureOnClickListener(){
        if(getActivity() instanceof SettingActivity){
            ((SettingActivity) getActivity()).setSelect(0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_main,container,false);
        ButterKnife.bind(this,v);
        return v;
    }


}
