package com.example.a60440.collegestudent.fragment.settingFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.activity.LoginActivity;
import com.example.a60440.collegestudent.activity.SettingActivity;
import com.example.a60440.collegestudent.utils.RegionDAO;
import com.example.a60440.collegestudent.bean.User;
import com.example.a60440.collegestudent.loader.NormalImageLoader;
import com.example.a60440.collegestudent.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 60440 on 2016/11/30.
 */

public class SettingPeronFileFragment extends Fragment {
    private final int currentFragmentId = 1;
    private List<String> provinceList;
    private List<String> cityList;
    private List<String> areaList;

    public int getCurrentFragmentId() {
        return currentFragmentId;
    }

    @OnClick(R.id.id_setting_personalfile_introduction)
    void setPersonalIntroductionOnClickListener(){
        selectFragment(3);
    }
    @OnClick(R.id.id_setting_personalfile_signature)
    void setPersonalSignatureOnClickListener(){
        selectFragment(2);
    }
    @OnClick(R.id.setting_nickname)
    void setNickName(){
        selectFragment(4);
    }
//    @OnClick(R.id.setting_region)
//    void setRegion(){
//        selectFragment(5);
//    }
    @OnClick(R.id.setting_gender)
    void setGender(){
        selectFragment(6);
    }
    @OnClick(R.id.setting_major)
    void setMajor(){
        selectFragment(7);
    }
    @OnClick(R.id.setting_school)
    void setSchool(){
        selectFragment(8);
    }
    private ArrayAdapter<String> mCityAdapter;
    private ArrayAdapter<String> mAreaAdapter;
    private List<Map<String, String>> mProvinces;
    private List<Map<String, String>> mCities;
    @Bind(R.id.id_setting_personfile_image)
    ImageView imageView;
    @Bind(R.id.id_setting_textview_nickname)
    TextView nicknameTextView;
    @Bind(R.id.id_setting_textview_gender)
    TextView genderTextView;
//    @Bind(R.id.id_setting_textview_region)
//    TextView regionTextView;
    @Bind(R.id.id_setting_textview_major)
    TextView majorTextView;
    @Bind(R.id.id_setting_textview_school)
    TextView schoolTextView;
    @Bind(R.id.sp_province)
    Spinner spinnerProvince;
    @Bind(R.id.sp_city)
    Spinner spinnerCity;
    @Bind(R.id.sp_area)
    Spinner spinnerArea;
    @OnClick(R.id.ll_quit)
    void setLogin(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_personfile,container,false);
        ButterKnife.bind(this,v);
        initView();
        initData(getContext());
        return v;
    }

    public void initData(Context context) {
        new NormalImageLoader().getPicture(getResources().getString(R.string.baseURL)+UserUtils.getParam(getContext()).getImageURL(),imageView);
        user = UserUtils.getParam(context);
        Log.i("userregion",user.getRegion());
        StringTokenizer stringTokenizer = new StringTokenizer(user.getRegion(),"_");
        String province=null,city=null,area=null;
        if(stringTokenizer.hasMoreTokens()) {
            province=stringTokenizer.nextToken();
            int position = provinceList.indexOf(province);
            spinnerProvince.setSelection(position);
            refreshCityByProvince(position);
            Log.i("index ",position+"");

        }
        if(stringTokenizer.hasMoreTokens()) {
            city=stringTokenizer.nextToken();
            int position = cityList.indexOf(city);
            spinnerCity.setSelection(position);
            refreshAreaByCity(position);
        }
        if(stringTokenizer.hasMoreTokens()) {
            area=stringTokenizer.nextToken();
            spinnerArea.setSelection(areaList.indexOf(area));
        }
        Log.i("User information",province+city+area);
        if(user.getNickname()!=null)
            nicknameTextView.setText(user.getNickname());
        if(user.getGender()!=null)
            genderTextView.setText(user.getGender());
        if(user.getRegion()!=null){
            province = spinnerProvince.getSelectedItem().toString();
            city = spinnerCity.getSelectedItem().toString();
            area = spinnerArea.getSelectedItem().toString();
            user.setRegion(province+"_"+city+"_"+area);
            UserUtils.setParam(getContext(),user);

        }
        if(user.getSchool()!=null)
            schoolTextView.setText(user.getSchool());
        if(user.getMajor()!=null)
            majorTextView.setText(user.getMajor());


    }
    private void selectFragment(int id){
        if (getActivity() instanceof SettingActivity){
            ((SettingActivity)getActivity()).setSelect(id);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        initData(getContext());
        super.onHiddenChanged(hidden);
    }
    private void initView() {
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>());
        spinnerProvince.setAdapter(provinceAdapter);
        mCityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>());
        spinnerCity.setAdapter(mCityAdapter);
        mAreaAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>());
        spinnerArea.setAdapter(mAreaAdapter);
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int number = refreshCityByProvince(position);
                if (number != 0) {
                    int selectedItemPosition = spinnerCity.getSelectedItemPosition();
                    spinnerCity.setSelection(0);
                    spinnerArea.setSelection(0);
                    if (selectedItemPosition == 0)
                        refreshAreaByCity(0);
                } else {
                    mAreaAdapter.clear();
                    mAreaAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refreshAreaByCity(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mProvinces = RegionDAO.getProvinces();
        provinceAdapter.clear();
        provinceList = flatList(mProvinces);
        provinceAdapter.addAll(provinceList);
        provinceAdapter.notifyDataSetChanged();

    }
    private void refreshAreaByCity(int position) {

        Map<String, String> map = mCities.get(position);
        String cityID = map.get("id");
        areaList = RegionDAO.getAreaByCity(cityID);
        mAreaAdapter.clear();
        if (areaList.size() != 0)
            mAreaAdapter.addAll(areaList);
        mAreaAdapter.notifyDataSetChanged();

    }

    private int refreshCityByProvince(int position) {

        Map<String, String> map = mProvinces.get(position);
        mCities = RegionDAO.getCityByProvince(map.get("id"));
        mCityAdapter.clear();
        if (mCities.size() != 0) {
            cityList = flatList(mCities);
            mCityAdapter.addAll(cityList);
        }
        mCityAdapter.notifyDataSetChanged();
        return mCities.size();
    }

    private List<String> flatList(List<Map<String, String>> list) {
        List<String> names = new ArrayList<>();
        for (Map<String, String> item : list) {
            names.add(item.get("name"));
        }
        return names;
    }
}
