package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.utils.UserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 60440 on 2016/12/7.
 */

public class SettingPersonalizeSignature extends Fragment {
    private final int currentFragmentId = 2;
    public int getCurrentFragmentId() {
        return currentFragmentId;
    }
    @Bind(R.id.id_setting_textview)
    EditText editText;
    private String signature;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personalizesignature,container,false);
        ButterKnife.bind(this,v);
        User user = UserUtils.getParam(getContext());
        signature=user.getSignature();
        editText.setText(signature);
        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        User user = UserUtils.getParam(getContext());
        user.setSignature(signature);
        UserUtils.setParam(getContext(),user);
        super.onHiddenChanged(hidden);
    }
}
