package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a60440.collegestudent.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2017/2/9.
 */

public class SettingPersonIntroduction extends Fragment {
    private final int currentFragmentId = 3;
    public int getCurrentFragmentId() {
        return currentFragmentId;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personal_introduction,container,false);
        ButterKnife.bind(this,v);
        return v;
    }
}
