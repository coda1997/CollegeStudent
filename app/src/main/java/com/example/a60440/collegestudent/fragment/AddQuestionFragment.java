package com.example.a60440.collegestudent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.requestServes.AddQuestionRequestServes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2016/11/27.
 */

public class AddQuestionFragment extends Fragment {
    private EditText questionEdit;
    private Button submitButton;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addquestionfragment,container,false);
        initView(v);

        return v;
    }

    private void initView(View v) {
        questionEdit = (EditText)v.findViewById(R.id.id_EditText_AddQuestion);
        submitButton = (Button)v.findViewById(R.id.id_Button_submit_question);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });

    }

    private void Submit() {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl("http://lacalhost:11112/Demo1/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AddQuestionRequestServes requestServes = retorfit.create(AddQuestionRequestServes.class);
        Call<String> call = requestServes.getString(questionEdit.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("==","return: "+response.body().toString());//response
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("===","fail");
            }
        });
    }


}
