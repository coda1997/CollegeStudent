package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingMainFragment;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPeronFileFragment;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonIntroduction;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonalizeSignature;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by 60440 on 2016/11/29.
 */

public class SettingActivity extends AppCompatActivity{
    private SettingMainFragment mSettingMain;
    private SettingPeronFileFragment mPersongfile;
//    private Fragment mNotification;
//    private Fragment mSideFunction;
    private SettingPersonalizeSignature mSettingPersionalizeSignature;
    private SettingPersonIntroduction mPersionalIntroduction;
    private int currentFragmentId = 0;
    private int currentFragment = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.setting_container);
    InitView();
    setSelect(currentFragment);
    }

    private void InitView() {
        Intent intent = getIntent();
        currentFragment = intent.getIntExtra("currentFrament",0);
    }

    public void setSelect(int i){
    FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        currentFragmentId=i;
        if(i==0){
            if(mSettingMain==null){
                mSettingMain = new SettingMainFragment();
                transaction.add(R.id.id__setting_container,mSettingMain);

            }else{
                transaction.show(mSettingMain);
            }
        }else if(i==1){
            if(mPersongfile==null){
                mPersongfile = new SettingPeronFileFragment();
                transaction.add(R.id.id__setting_container,mPersongfile);
            }else{
                transaction.show(mPersongfile);
            }
        }else if(i==2){
            if(mSettingPersionalizeSignature==null){
                mSettingPersionalizeSignature=new SettingPersonalizeSignature();
                transaction.add(R.id.id__setting_container,mSettingPersionalizeSignature);
            }else{
                transaction.show(mSettingPersionalizeSignature);
            }

        }else if(i==3){
            if(mPersionalIntroduction==null){
                mPersionalIntroduction=new SettingPersonIntroduction();
                transaction.add(R.id.id__setting_container,mPersionalIntroduction);
            }else {
                transaction.show(mPersionalIntroduction);
            }
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mPersongfile!=null)
            transaction.hide(mPersongfile);
//        if(mNotification!=null)
//            transaction.hide(mNotification);
//        if(mSideFunction!=null)
//            transaction.hide(mSideFunction);
        if(mSettingMain!=null)
            transaction.hide(mSettingMain);
        if(mSettingPersionalizeSignature!=null)
            transaction.hide(mSettingPersionalizeSignature);
        if(mPersionalIntroduction!=null)
            transaction.hide(mPersionalIntroduction);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KEYCODE_BACK&&currentFragmentId!=0){
            if(currentFragmentId==3){
                setSelect(1);
                return true;
            }
            if(currentFragmentId==1||currentFragmentId==2){
                setSelect(0);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
