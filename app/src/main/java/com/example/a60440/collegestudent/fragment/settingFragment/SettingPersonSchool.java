package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.utils.UserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 60440 on 2017/2/9.
 */

public class SettingPersonSchool extends Fragment {
    private final int currentFragmentId = 8;
    public int getCurrentFragmentId() {
        return currentFragmentId;
    }
    @Bind(R.id.id_setting_textview)
    EditText editText;
    private String school;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personalizesignature,container,false);
        ButterKnife.bind(this,v);
        User user = UserUtils.getParam(getContext());
        school = user.getSchool();
        editText.setText(school);
        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        User user = UserUtils.getParam(getContext());
        user.setSchool(editText.getText().toString());
        UserUtils.setParam(getContext(),user);
        super.onHiddenChanged(hidden);
    }
}
