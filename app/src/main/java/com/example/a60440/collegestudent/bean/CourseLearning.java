package com.example.a60440.collegestudent.bean;

import java.util.Date;

/**
 * Created by mrwen on 2017/2/26.
 */

public class CourseLearning {
    int id;
    long duration;
    Date time;

    @Override
    public String toString() {
        return "CourseLearning{" +
                "course=" + course +
                ", id=" + id +
                ", druation=" + duration +
                ", time=" + time +
                '}';
    }


    Course course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDruation() {
        return duration;
    }

    public void setDruation(long druation) {
        this.duration = druation;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
