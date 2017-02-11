package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.utils.UserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 60440 on 2017/2/9.
 */

public class SettingPersonName extends Fragment {
    private final int currentFragmentId = 4;
    public int getCurrentFragmentId() {
        return currentFragmentId;
    }
    @Bind(R.id.id_setting_textview)
    TextView textView;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personalizesignature,container,false);
        ButterKnife.bind(this,v);
        User user = UserUtils.getParam(getContext());
        nickname=user.getNickname();
        textView.setText(nickname);
        return v;

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        User user = UserUtils.getParam(getContext());
        user.setNickname(textView.getText().toString());
        UserUtils.setParam(getContext(),user);
        super.onHiddenChanged(hidden);
    }
}
