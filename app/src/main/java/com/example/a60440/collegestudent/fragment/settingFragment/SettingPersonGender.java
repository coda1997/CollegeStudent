package com.example.a60440.collegestudent.fragment.settingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.SettingActivity;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.utils.UserUtils;

import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2017/2/9.
 */

public class SettingPersonGender extends Fragment {
    private final int currentFragmentId = 6;
    private String gender = "male";
    @Bind(R.id.id_setting_gender_check_female)
    ImageView femaleImageView;
    @Bind(R.id.id_setting_gender_check_male)
    ImageView maleImageView;
    @OnClick(R.id.id_setting_gender_female)
    void setGenderFemale(){
        femaleImageView.setVisibility(ImageView.VISIBLE);
        maleImageView.setVisibility(ImageView.INVISIBLE);
        gender = "male";
        backToPersonFile();
    }
    @OnClick(R.id.setting_gender_male)
    void setGenderMale(){
        maleImageView.setVisibility(ImageView.VISIBLE);
        femaleImageView.setVisibility(ImageView.INVISIBLE);
        gender="female";
        backToPersonFile();
    }


    public int getCurrentFragmentId() {
        return currentFragmentId;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_persona_gender,container,false);
        ButterKnife.bind(this,v);
        return v;
    }

    private void backToPersonFile(){
        if(getActivity() instanceof SettingActivity){
            User user = UserUtils.getParam(getContext());


            ((SettingActivity) getActivity()).setSelect(1);
        }
    }
}
