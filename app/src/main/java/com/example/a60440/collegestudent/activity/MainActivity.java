package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.a60440.collegestudent.Manifest;
import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.fragment.AddQuestionFragment;
import com.example.a60440.collegestudent.fragment.FriendFragment;
import com.example.a60440.collegestudent.fragment.QuestionContent;
import com.example.a60440.collegestudent.fragment.QuestionFragment;
import com.example.a60440.collegestudent.fragment.VedioFragment;

import static android.view.KeyEvent.KEYCODE_BACK;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private LinearLayout mTabQuestion;
    private LinearLayout mTabFriend;
    private LinearLayout mTabVideo;
    ImageButton AddButton;
    private final String TAG_2 = "OnAddClick";

    //    private ImageButton mImagQuestion;
//    private ImageButton mImagFriend;
//    private ImageButton mImagVideo;
//
    private Fragment mTab01;

    private Fragment mTab02;

    private Fragment mTab03;
    private Fragment mTabAdd;
    private Fragment mTabContent;
    private int currentFragmentId = 0;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initEvent();
        setSelect(0);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
//        MenuItem item = menu.getItem(0);
//        if(item==null)
//            Log.i(TAG_2,"NULL");
//        else {
//            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    setSelect(3);
//                    return true;
//                }
//            });
//        }
//        MenuItem item_setting = menu.getItem(1);
//        if(item_setting!=null) {
//            item_setting.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    Intent intent = new Intent(MainActivity.this,SettingActivity.class);
//                    intent.putExtra("currentFrament",-1);
//                    startActivity(intent);
//                    return true;
//                }
//            });
//        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings||id==R.id.id_video_setting||id==R.id.chatting_settings) {
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            intent.putExtra("currentFrament",-1);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_adding){
            setSelect(3);
            return true;
        }else if(id==R.id.id_sharing){
            return true;
        }else if(id==R.id.id_video_adding){
            return true;
        }else if(id==R.id.id_video_sharing){
            return true;
        }else if(id==R.id.chatting_adding_friends){

        }else if(id==R.id.chatting_adding_class){

        }

        return super.onOptionsItemSelected(item);
    }

//    public void onClickAddQuestion(){

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startSettingActivity();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this,VedioManegerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            intent.putExtra("currentFrament",-1);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            StartLoginActivity();

        }
//        else if (id == R.id.nav_send) {
//
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startSettingActivity() {
        Intent intent = new Intent(MainActivity.this,SettingActivity.class);
        intent.putExtra("currentFrament",1);
        startActivity(intent);

    }


    private void StartLoginActivity() {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);

    }

    public void initView() {
        mTabQuestion = (LinearLayout)findViewById(R.id.id_tab_question);
        mTabVideo = (LinearLayout)findViewById(R.id.id_tab_vedio);
        mTabFriend = (LinearLayout)findViewById(R.id.id_tab_friend);

//        AddButton = (ImageButton)findViewById(R.id.id_addbutton);


//        mImagQuestion = (ImageButton)findViewById(R.id.id_tab_quesion_img);
//        mImagVideo = (ImageButton)findViewById(R.id.id_tab_vedio_img);
//        mImagFriend = (ImageButton) findViewById(R.id.id_tab_friend_img);

//        mTab01 = new QuestionFragment();
//        mTab02 = new VedioFragment();
//        mTab03 = new FriendFragment();
    }

    public void initEvent() {
        mTabQuestion.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);
        mTabVideo.setOnClickListener(this);

    }

    public void setSelect(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 0:
                if(mTab01==null){
                    mTab01 = new QuestionFragment();

                    currentFragmentId = 0;
                    transaction.add(R.id.id_content,mTab01);

                }else{
                    currentFragmentId = 0;
                    transaction.show(mTab01);
                }
                //this can put a image pressed
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.main);
                break;
            case 1:
                if(mTab02==null){
                    currentFragmentId = 1;
                    mTab02 = new VedioFragment();
                    transaction.add(R.id.id_content,mTab02);
                }else{
                    currentFragmentId = 1;
                    transaction.show(mTab02);
                }
                //this can put a image pressed
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.main_video);
                break;
            case 2:
                if(mTab03==null){
                    currentFragmentId = 2;
                    mTab03 = new FriendFragment();
                    transaction.add(R.id.id_content,mTab03);
                }else{
                    currentFragmentId = 2;
                    transaction.show(mTab03);
                }
                //this can put a image pressed
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.main_friend);
                break;
            case 3:
                if(mTabAdd==null){
                    currentFragmentId = 3;
                    mTabAdd= new AddQuestionFragment();
                    transaction.add(R.id.id_content,mTabAdd);
                }else{
                    currentFragmentId = 3;
                    transaction.show(mTabAdd);
                }
                break;
            case 4:
                if(mTabContent==null){
                    currentFragmentId = 4;
                    mTabContent= new QuestionContent();

                    transaction.add(R.id.id_content,mTabContent);
                }else{
                    currentFragmentId = 4;
                    transaction.show(mTabContent);
                }
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.main_question);
                break;
            default:break;
        }
        transaction.commit();

    }
    private void hideFragment(FragmentTransaction transaction){
        if(mTab01 != null)
            transaction.hide(mTab01);
        if(mTab02 != null)
            transaction.hide(mTab02);
        if(mTab03 != null)
            transaction.hide(mTab03);
        if(mTabAdd!=null)
            transaction.hide(mTabAdd);
        if(mTabContent!=null)
            transaction.hide(mTabContent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_tab_question:
                setSelect(0);
                break;
            case R.id.id_tab_vedio:
                setSelect(1);
                break;
            case R.id.id_tab_friend:
                setSelect(2);
                break;
            default:break;
        }
    }

    //    }
//
//        addQues.seton
//        MenuItem addQues = (MenuItem)findViewById(R.id.action_adding);
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KEYCODE_BACK&&event.getRepeatCount()==0){
            if(currentFragmentId==3||currentFragmentId==4){
                setSelect(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
