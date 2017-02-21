package com.example.a60440.collegestudent.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 60440 on 2017/2/19.
 */

public class TimeUtils {
    public static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        return simpleDateFormat.format(date);

    }
}
