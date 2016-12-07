package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.fragment.SettingMainFragment;
import com.example.a60440.collegestudent.fragment.SettingPeronFileFragment;
import com.example.a60440.collegestudent.fragment.SettingPersonalizeSignature;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by 60440 on 2016/11/29.
 */

public class SettingActivity extends AppCompatActivity{
    private Fragment mSettingMain;
    private Fragment mPersongfile;
    private Fragment mNotification;
    private Fragment mSideFunction;
    private Fragment mSettingPersionalizeSignature;
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
        currentFragment = intent.getIntExtra("currentFrament",-1);
    }

    public void setSelect(int i){
    FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        if(i==0){
            currentFragment=1;
            if(mSettingPersionalizeSignature==null){
                mSettingPersionalizeSignature=new SettingPersonalizeSignature();
                transaction.add(R.id.id__setting_container,mSettingPersionalizeSignature);
            }else{
                transaction.show(mSettingPersionalizeSignature);
            }
        }else if(i==1){
            currentFragmentId=1;
            if(mPersongfile==null){
                mPersongfile = new SettingPeronFileFragment();
                transaction.add(R.id.id__setting_container,mPersongfile);
            }else{
                transaction.show(mPersongfile);
            }
        }else if(i==2){
            currentFragmentId=1;
            if(mNotification==null){

            }else{

            }
        }else if(i==3){
            currentFragmentId=1;
            if(mSideFunction==null){

            }else{

            }
        }else if(i==-1){
            currentFragmentId=0;
            if(mSettingMain==null){
                mSettingMain = new SettingMainFragment();
                transaction.add(R.id.id__setting_container,mSettingMain);
            }else{
                transaction.show(mSettingMain);
            }
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mPersongfile!=null)
            transaction.hide(mPersongfile);
        if(mNotification!=null)
            transaction.hide(mNotification);
        if(mSideFunction!=null)
            transaction.hide(mSideFunction);
        if(mSettingMain!=null)
            transaction.hide(mSettingMain);
        if(mSettingPersionalizeSignature!=null)
            transaction.hide(mSettingPersionalizeSignature);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KEYCODE_BACK&&event.getRepeatCount()==0&&currentFragmentId!=0){
            setSelect(-1);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
