package com.example.a60440.collegestudent.bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 60440 on 2017/2/26.
 */

public class LineChartDatas {
    private String posX[];
    private int posY[];
    private int data[];

    public int[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "LineChartDatas{" +
                "data=" + Arrays.toString(data) +
                ", posX=" + Arrays.toString(posX) +
                ", posY=" + Arrays.toString(posY) +
                '}';
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public LineChartDatas(Date date) {
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
//        String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        posX=new String[7];
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        for(int i =0;i<7;i++){
            posX[i]=weekDaysName[(week+i)%7];
        }
    }
    public String[] getPosX() {
        return posX;
    }

    public int[] getPosY() {
        return posY;
    }
    public void setPosY(int[] posY) {
        this.posY = posY;
    }

}
