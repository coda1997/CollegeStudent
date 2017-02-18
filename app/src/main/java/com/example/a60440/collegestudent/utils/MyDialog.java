package com.example.a60440.collegestudent.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a60440.collegestudent.R;


/**
 * Created by mrwen on 2017/2/9.
 */

public class MyDialog {
    //显示提示框
    public void showAlertDialgo(Context context,String tip){
        new AlertDialog.Builder(context).setTitle("提示")
                .setMessage(tip)
                .setPositiveButton("确认",null)
                .show();
    }

    //弹出单行输入框
    public void showSingleLineInputDialog(Context context, String title, String defaultString,final TextView textView) {

        final EditText inputServer = new EditText(context);
        //inputServer.setSelection(defaultString.length());
        inputServer.setText(defaultString);
        inputServer.setMaxLines(1);
        inputServer.setMinLines(1);
        inputServer.setFocusable(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setIcon(
                R.drawable.ic_arrow_check).setView(inputServer).setNegativeButton("取消", null);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(inputServer.getText());
                    }
                });
        builder.show();
    }


}
