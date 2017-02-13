package com.example.a60440.collegestudent.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.Result;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.fragment.FriendFragment;
import com.example.a60440.collegestudent.fragment.questionFragment.QuestionContent;
import com.example.a60440.collegestudent.fragment.questionFragment.QuestionFragment;
import com.example.a60440.collegestudent.fragment.VedioFragment;
import com.example.a60440.collegestudent.requestServes.ImageUploadServes;
import com.example.a60440.collegestudent.utils.ImageUtils;
import com.example.a60440.collegestudent.utils.UserUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.KeyEvent.KEYCODE_BACK;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private LinearLayout mTabQuestion;
    private LinearLayout mTabFriend;
    private LinearLayout mTabVideo;
    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTabAdd;
    private Fragment mTabContent;
    private int currentFragmentId = 0;
    private Toolbar toolbar;
    private MaterialSearchView searchView;
    private User user;
    private ImageView userImageView;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private static final int TAKE_VIDEO = 3;
    protected static Uri tempUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Intent intent = this.getIntent();
        user = (User) intent.getSerializableExtra("User");
        if(user!=null){
            //...
            UserUtils.setParam(getBaseContext(),user);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initEvent();
        setSelect(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        userImageView=(ImageView)view.findViewById(R.id.imageView);
        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });

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
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_adding){
            setSelect(3);
            return true;
        }else if(id==R.id.id_sharing){
            return true;
        }else if(id==R.id.id_video_adding){
            Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
            innerIntent.setType("video/*");
            Intent wrapperIntent = Intent.createChooser(innerIntent,null);
            startActivityForResult(wrapperIntent,TAKE_VIDEO);

            return true;
        }else if(id==R.id.id_video_management){
            Intent intent = new Intent(this,VedioManegerActivity.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.chatting_adding_friends){

        }else if(id==R.id.chatting_adding_class){

        }

        return super.onOptionsItemSelected(item);
    }

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
            intent.putExtra("currentFrament",0);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startSettingActivity() {
        Intent intent = new Intent(MainActivity.this,SettingActivity.class);
        intent.putExtra("currentFrament",1);
        startActivity(intent);

    }

    public void initView() {
        mTabQuestion = (LinearLayout)findViewById(R.id.id_tab_question);
        mTabVideo = (LinearLayout)findViewById(R.id.id_tab_vedio);
        mTabFriend = (LinearLayout)findViewById(R.id.id_tab_friend);

        searchView=(MaterialSearchView)findViewById(R.id.id_search_view);searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
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
//                if(mTabAdd==null){
//                    currentFragmentId = 3;
//                    mTabAdd= new AddQuestionFragment();
//                    transaction.add(R.id.id_content,mTabAdd);
//                }else{
//                    currentFragmentId = 3;
//                    transaction.show(mTabAdd);
//                }
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
        if(toolbar.getMenu()!=null)
            searchView.setMenuItem(toolbar.getMenu().findItem(R.id.action_search));

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
    private void saveUserInfo(User user){
        if(user==null){
            return;
        }else {
            UserUtils.setParam(getApplicationContext(),user);
        }

    }
    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
//                        Intent openAlbumIntent = new Intent(
//                                Intent.ACTION_GET_CONTENT);
                        Intent openAlbumIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        openAlbumIntent.setType("image/*");
//                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);

                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
                case TAKE_VIDEO:
                    Uri uri = data.getData();
                    Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                    cursor.moveToFirst();
                    String video_path = cursor.getString(1);
                    String video_size = cursor.getString(2);
                    String video_name = cursor.getString(3);
                    Log.i("video_path",video_path);
                    Log.i("video_size",video_size);
                    Log.i("video_name",video_name);

                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = ImageUtils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            userImageView.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.baseURL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ImageUploadServes imageUploadServes = retrofit.create(ImageUploadServes.class);

            File imageFile = new File(imagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart"),imageFile);
            Call<Result> call = imageUploadServes.uploadImage(user.getId(),requestBody);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Log.i("uploader iamge:=","succeed");
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.i("uploader iamge:=","fail");

                }
            });


        }
    }
}
