package com.example.a60440.collegestudent.fragment.settingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.LoginActivity;
import com.example.a60440.collegestudent.activity.SettingActivity;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/11/30.
 */

public class SettingMainFragment extends Fragment{
    private final int currentFragmentId = 0;

    public final int getCurrentFragmentId() {
        return currentFragmentId;
    }

    @OnClick(R.id.id_setting_personfile)
    void setPersonfileOnClickListener(){
        if(getActivity() instanceof SettingActivity){
            ((SettingActivity) getActivity()).setSelect(1);
            Log.i("person file","succeed");
        }
    }

    @OnClick(R.id.id_setting_personalizesignature)
    void setPersonalizsinatureOnClickListener(){
        if(getActivity() instanceof SettingActivity){
            ((SettingActivity) getActivity()).setSelect(2);
            Log.i("personlizesignature","succeed");
        }
    }
    @OnClick(R.id.id_setting_log_out)
    void logout(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_main,container,false);
        ButterKnife.bind(this,v);
        return v;
    }


}
