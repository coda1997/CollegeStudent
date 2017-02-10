package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a60440.collegestudent.R;

/**
 * Created by 60440 on 2017/2/9.
 */

public class SettingPersonSchool extends Fragment {
    private final int currentFragmentId = 8;
    public int getCurrentFragmentId() {
        return currentFragmentId;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personalizesignature,container,false);
        return v;
    }
}
