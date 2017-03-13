package com.example.a60440.collegestudent.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a60440.collegestudent.R;
import com.example.a60440.collegestudent.bean.CourseLearning;
import com.example.a60440.collegestudent.bean.LineChartDatas;
import com.example.a60440.collegestudent.bean.MessageRecord;
import com.example.a60440.collegestudent.configuration.Constant;
import com.example.a60440.collegestudent.requestServes.InterfaceStudent;
import com.github.lzyzsd.randomcolor.RandomColor;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.graphics.Color.BLUE;

/**
 * Created by 60440 on 2017/2/23.
 */

public class StudentInfoActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    //initialize two line charts about number of the student's joining class
    @Bind(R.id.chart_overall)
    LineChartView mLineChartView;
    @Bind(R.id.chart_detail)
    PieChartView detailLineChart;
    private String uid=null;
    private int id;
    private ArrayList<CourseLearning> courseLearnings;
    private ArrayList<MessageRecord> messageRecords;
    private ArrayList<CourseLearning> todayCourse;
    private Calendar calendar;
    private List<PointValue> mPointValues;
    private List<AxisValue> mAxisValues;
    private PieChartData pieChartData;
    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = true;
    private boolean hasCenterText1 = true;//环形中间的文字1
    private boolean hasCenterText2 = false;
    private boolean isExploded = true;
    private boolean hasLabelForSelected = true;

    //    private LineChartView mmLineChartView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        ButterKnife.bind(this);
        toolbar.setTitle("学生上课数据分析");
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);

        if(uid!=null){
            id=Integer.parseInt(uid.substring(1,uid.length()));
            Log.i("studentid=",id+"");
        }else{
            Toast.makeText(this, Constant.INTERNET_FAIL, Toast.LENGTH_SHORT).show();
        }
        initData();
    }

    private void initData() {
        // TODO: 2017/2/25
        //here something about a student's information is needed for two charts.
        getCourse();
//        getMessage();

    }
    private void getCourse(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceStudent interfaceStudent = retrofit.create(InterfaceStudent.class);
        Call<ArrayList<CourseLearning>> call = interfaceStudent.getCourseLearning(id);
        call.enqueue(new Callback<ArrayList<CourseLearning>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseLearning>> call, Response<ArrayList<CourseLearning>> response) {
                if(response.body()!=null){
                    courseLearnings=response.body();
                    Log.i("courselearning size:",courseLearnings.size()+"");
                    initCourse(courseLearnings);
                    initTotalCourse(courseLearnings);
                }else {
                    Toast.makeText(StudentInfoActivity.this, Constant.INTERNET_FAIL, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CourseLearning>> call, Throwable t) {
                Toast.makeText(StudentInfoActivity.this, Constant.INTERNET_FAIL+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getMessage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceStudent interfaceStudent = retrofit.create(InterfaceStudent.class);
        Call<ArrayList<MessageRecord>> call = interfaceStudent.getMessageRecord(uid);
        call.enqueue(new Callback<ArrayList<MessageRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<MessageRecord>> call, Response<ArrayList<MessageRecord>> response) {
                if(response.body()!=null){
                    messageRecords=response.body();
                    Log.i("get","success");

                }else{
                    Toast.makeText(StudentInfoActivity.this, Constant.INTERNET_FAIL, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MessageRecord>> call, Throwable t) {
                Toast.makeText(StudentInfoActivity.this, Constant.INTERNET_FAIL, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //inputs are course information in a whole week
    private void initCourse(final ArrayList<CourseLearning> courseLearnings){
//        Log.i("courseinfo", courseLearnings.get(0).toString());
//        Log.i("courseinfo", courseLearnings.get(1).toString());
//        Log.i("courseinfo", courseLearnings.get(2).toString());

        LineChartDatas chartData = new LineChartDatas(new Date());
        String[] posX = chartData.getPosX();
//        Log.i("date", posX[0]+posX[1]+posX[2]+posX[3]+posX[4]+posX[5]+posX[6]);
        int[] posY = {0,0,0,0,0,0,0};
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        ArrayList<String> week=new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i =0;i<7;i++){
            calendar.add(Calendar.DATE,-1);
            date=calendar.getTime();
            String format = simpleDateFormat.format(date);
            Log.i("date:",format);
            week.add(format);
            for(Iterator<CourseLearning> iterator=courseLearnings.iterator();iterator.hasNext();){
                CourseLearning courseLearning = iterator.next();
                if(simpleDateFormat.format(courseLearning.getTime()).equals(format)){
                    posY[(6-i)%7] += courseLearning.getDruation()/60000;
                }
            }
        }
        for(int i =0;i<7;i++){
            Log.i("posY=",posY[i]+"");
        }
        mPointValues=new ArrayList<>();
        mAxisValues = new ArrayList<>();

        for (int i = 0; i < 7 ; i++) {
            mPointValues.add(new PointValue(i, posY[i]));
            mAxisValues.add(new AxisValue(i).setLabel(posX[i])); //为每个对应的i设置相应的label(显示在X轴)
        }

        Line line = new Line(mPointValues).setColor(BLUE).setCubic(false);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

//坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(BLUE);
        axisX.setName("当天课程学习");
        axisX.setMaxLabelChars(10);
        axisX.setValues(mAxisValues);
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        data.setAxisYLeft(axisY);

//设置行为属性，支持缩放、滑动以及平移
        mLineChartView.setInteractive(true);
        mLineChartView.setZoomType(ZoomType.HORIZONTAL);
        mLineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        mLineChartView.setLineChartData(data);
        mLineChartView.setVisibility(View.VISIBLE);

    }

    private void initTotalCourse(final ArrayList<CourseLearning> courseLearnings){
        detailLineChart.setOnValueTouchListener(new ValueTouchListener());
        detailLineChart.setCircleFillRatio(0.9f);
        ArrayList<CourseLearning> courseLearnings1 = new ArrayList<>();

        for(CourseLearning courseLearning:courseLearnings){
            boolean isAdd = false;
            if(courseLearnings1.size()!=0){
                for(int i =0;i<courseLearnings1.size();i++){
                    CourseLearning courseLearning1 = courseLearnings1.get(i);
                    if(courseLearning1.getCourse().getId()==courseLearning.getCourse().getId()){
                        courseLearning1.setDruation(courseLearning1.getDruation()+courseLearning.getDruation());
                        isAdd=true;
                        break;
                    }

                }
                if(isAdd==false){
                    courseLearnings1.add(courseLearning);
                }else{
                    isAdd=false;
                }
            }else {
                courseLearnings1.add(courseLearning);
            }
        }

        generateData(courseLearnings1);

    }



    /**
     * 生成数据
     */
    private void generateData(ArrayList<CourseLearning> courseLearnings) {
        int numValues = courseLearnings.size();//分成的块数

        long sum=0L;
        for(CourseLearning courseLearning:courseLearnings){
            sum+=courseLearning.getDruation();
        }
        List<SliceValue> values = new ArrayList<SliceValue>();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        //添加与设置pie的data比例
        for (int i = 0; i < numValues; ++i) {
            RandomColor randomColor = new RandomColor();
            float value = courseLearnings.get(i).getDruation()*100.0f;
            SliceValue sliceValue = new SliceValue(value/sum, randomColor.randomColor());//每一块的值和颜色，图标根据值自动进行比例分配
            sliceValue.setLabel(courseLearnings.get(i).getCourse().getName()+"\n"+decimalFormat.format(value/sum)+"%");
            values.add(sliceValue);
        }
        pieChartData = new PieChartData(values);
        pieChartData.setHasLabels(true);//显示数据
        pieChartData.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChartData.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChartData.setHasCenterCircle(true);;//是否是环形显示
        pieChartData.setCenterCircleScale(0.5f);////设置环形的大小级别
        pieChartData.setValueLabelBackgroundColor(Color.TRANSPARENT);////设置值得背景透明
        pieChartData.setValueLabelBackgroundEnabled(false);//数据背景不显示
        pieChartData.setValueLabelsTextColor(Color.BLACK);

        pieChartData.setValues(values);//填充数据
        if (isExploded) {
            pieChartData.setSlicesSpacing(1);//设置间隔为0
        }

        if (hasCenterText1) {
            pieChartData.setCenterText1("一周课程数据分析");

            // Get roboto-italic font.
            //      Typeface tf = Typeface.createFromAsset(MainActivity.this.getAssets(), "Roboto-Italic.ttf");//设置字体
            //   pieChartData.setCenterText1Typeface(tf);

            // Get font size from dimens.xml and convert it to sp(library uses sp values).
            pieChartData.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    45));
            pieChartData.setCenterText1Color(Color.BLACK);////设置值得颜色*/
        }

        if (hasCenterText2) {
            pieChartData.setCenterText2("Charts (Roboto Italic)");

            Typeface tf = Typeface.createFromAsset(StudentInfoActivity.this.getAssets(), "Roboto-Italic.ttf");

            pieChartData.setCenterText2Typeface(tf);
            pieChartData.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    45));
        }

        detailLineChart.setPieChartData(pieChartData);
    }


    /**
     * To animate values you have to change targets values and then call}
     * method(don't confuse with View.animate()).
     */
    private void prepareDataAnimation() {
        for (SliceValue value : pieChartData.getValues()) {
            value.setTarget((float) Math.random() * 30 + 15);//更新数据
        }
        detailLineChart.startDataAnimation();
    }



    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
//            Toast.makeText(StudentInfoActivity.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }


    }
}
