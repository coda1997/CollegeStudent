package com.example.a60440.collegestudent.fragment.questionFragment;

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
import com.example.a60440.collegestudent.activity.MainActivity;
import com.example.a60440.collegestudent.requestServes.AddQuestionRequestServes;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
//    private EditText questionEdit;
    private Button submitButton;
    @Bind(R.id.id_EditText_AddQuestion)
    EditText questionEdit;
    @OnClick(R.id.id_Button_submit_question)
    void setSubmitButton(){
        submit();
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).setSelect(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addquestionfragment,container,false);
        ButterKnife.bind(this,v);
        return v;
    }


    private void submit() {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
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
