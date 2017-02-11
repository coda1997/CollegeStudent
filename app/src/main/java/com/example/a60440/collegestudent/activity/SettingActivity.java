package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingMainFragment;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPeronFileFragment;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonGender;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonIntroduction;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonMajor;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonName;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonRegion;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonSchool;
import com.example.a60440.collegestudent.fragment.settingFragment.SettingPersonalizeSignature;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by 60440 on 2016/11/29.
 */

public class SettingActivity extends AppCompatActivity{
    private SettingMainFragment mSettingMain;
    private SettingPeronFileFragment mPersongfile;
    private SettingPersonalizeSignature mSettingPersionalizeSignature;
    private SettingPersonIntroduction mPersionalIntroduction;
    private SettingPersonName settingPersonName;
    private SettingPersonGender settingPersonGender;
    private SettingPersonRegion settingPersonRegion;
    private SettingPersonMajor settingPersonMajor;
    private SettingPersonSchool settingPersonSchool;

    private int currentFragmentId = 0;
    private int currentFragment = -1;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar);

        InitView();
        setSelect(currentFragment);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("==","menu is clicked");
        return true;
    }

    private void InitView() {
        Intent intent = getIntent();
        currentFragment = intent.getIntExtra("currentFrament",0);
    }

    public void setSelect(int i){
    FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
//            toolbar.getMenu().clear();
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
//            mPersongfile.initData(getBaseContext());
        }else if(i==2){
            if(mSettingPersionalizeSignature==null){
                mSettingPersionalizeSignature=new SettingPersonalizeSignature();
                transaction.add(R.id.id__setting_container,mSettingPersionalizeSignature);
            }else{
                transaction.show(mSettingPersionalizeSignature);
            }
//            toolbar.inflateMenu(R.menu.save_menu);

        }else if(i==3){
            if(mPersionalIntroduction==null){
                mPersionalIntroduction=new SettingPersonIntroduction();
                transaction.add(R.id.id__setting_container,mPersionalIntroduction);
            }else {
                transaction.show(mPersionalIntroduction);
            }
//            toolbar.inflateMenu(R.menu.save_menu);
        }else if(i==4){
            if(settingPersonName==null){
                settingPersonName=new SettingPersonName();
                transaction.add(R.id.id__setting_container,settingPersonName);
            }else {
                transaction.show(settingPersonName);
            }
//            toolbar.inflateMenu(R.menu.save_menu);
        }else if(i==5){
            if(settingPersonRegion==null){
                settingPersonRegion=new SettingPersonRegion();
                transaction.add(R.id.id__setting_container,settingPersonRegion);
            }else {
                transaction.show(settingPersonRegion);
            }
//            toolbar.inflateMenu(R.menu.save_menu);
        }else if(i==6){
//            String gender = mPersongfile.getUser().getGender();

            if(settingPersonGender==null){
                settingPersonGender = new SettingPersonGender();
//                settingPersonGender.setGender(gender);
                transaction.add(R.id.id__setting_container,settingPersonGender);
            }else{
//                settingPersonGender.setGender(gender);
                transaction.show(settingPersonGender);
            }

        }else if(i==7){
            if(settingPersonMajor==null){
                settingPersonMajor = new SettingPersonMajor();
                transaction.add(R.id.id__setting_container,settingPersonMajor);
            }else{
                transaction.show(settingPersonMajor);
            }
//            toolbar.inflateMenu(R.menu.save_menu);
        }else if(i==8){
            if(settingPersonSchool==null){
                settingPersonSchool = new SettingPersonSchool();
                transaction.add(R.id.id__setting_container,settingPersonSchool);
            }else{
                transaction.show(settingPersonSchool);
            }
//            toolbar.inflateMenu(R.menu.save_menu);
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mPersongfile!=null)
            transaction.hide(mPersongfile);
        if(mSettingMain!=null)
            transaction.hide(mSettingMain);
        if(mSettingPersionalizeSignature!=null)
            transaction.hide(mSettingPersionalizeSignature);
        if(mPersionalIntroduction!=null)
            transaction.hide(mPersionalIntroduction);
        if(settingPersonSchool!=null)
            transaction.hide(settingPersonSchool);
        if(settingPersonMajor!=null)
            transaction.hide(settingPersonMajor);
        if(settingPersonGender!=null){

            transaction.hide(settingPersonGender);

        }
        if(settingPersonName!=null)
            transaction.hide(settingPersonName);
        if(settingPersonRegion!=null)
            transaction.hide(settingPersonRegion);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KEYCODE_BACK&&currentFragmentId!=0){
            if(currentFragmentId==3||currentFragmentId==4||currentFragmentId==5||currentFragmentId==6||currentFragmentId==7||currentFragmentId==8){
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
