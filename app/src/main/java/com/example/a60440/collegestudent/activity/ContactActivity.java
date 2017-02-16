package com.example.a60440.collegestudent.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.adapter.ExpandableContactAdapter;
import com.example.a60440.collegestudent.bean.Roster;
import com.example.a60440.collegestudent.bean.RosterGroup;
import com.example.a60440.collegestudent.bean.UserInfo;
import com.example.a60440.collegestudent.configuration.Constant;
import com.example.a60440.collegestudent.view.AnimatedExpandableListView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactActivity extends AppCompatActivity {

    @Bind(R.id.aelv_contact)
    AnimatedExpandableListView aelvContact;
    @Bind(R.id.search_view)
    MaterialSearchView searchView;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    private ExpandableContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initData();
    }

    private void initView() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(ContactActivity.this, SearchActivity.class);
                intent.putExtra("query", query);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, Constant.REQURST_SEARCH);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mAdapter = new ExpandableContactAdapter(new ArrayList<RosterGroup>());
        aelvContact.setAdapter(mAdapter);
        aelvContact.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Roster roster = (Roster) mAdapter.getChild(groupPosition, childPosition);
                Intent intent = new Intent(Intent.ACTION_VIEW, UserInfo.getPrivateChatUri(roster.getUid(),roster.getRemark()));
                startActivity(intent);
                return false;
            }
        });
    }

    private void initData() {
//        RetrofitUtils.getService().getRequestCount(UserInfo.uid)
//                          .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        tvNumber.setText(result.getCount()+"");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
//        RetrofitUtils.getService().getContacts(UserInfo.uid)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ArrayList<RosterGroup>>() {
//                    @Override
//                    public void accept(ArrayList<RosterGroup> groups) throws Exception {
//                        mAdapter.setData(groups);
//                        UserInfo.groupNames.clear();
//                        for (RosterGroup group : groups) {
//                            UserInfo.groupNames.add(group.getName());
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        new AlertDialog.Builder(ContactActivity.this).setMessage("获取数据异常:" + throwable).setPositiveButton("重试", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                initData();
//                            }
//                        }).setNegativeButton("取消", null).show();
//                    }
//                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REQURST_SEARCH && resultCode == RESULT_OK) {
            initData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.rl_friend_request)
    public void onClick() {
        Intent intent = new Intent(this, FriendRequestActivity.class);
        startActivityForResult(intent, Constant.REQURST_SEARCH);
    }

}
