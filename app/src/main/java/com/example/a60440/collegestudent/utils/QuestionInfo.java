package com.example.a60440.collegestudent.utils;

/**
 * Created by 60440 on 2017/2/8.
 */

public class QuestionInfo {
    public String id;
    public String title;
    public String date;
    public String content;
    public String foucusnumber;
    public String questionerName;
    public String questionerImage;

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", foucusnumber='" + foucusnumber + '\'' +
                ", questionerName='" + questionerName + '\'' +
                ", questionerImage='" + questionerImage + '\'' +
                '}';
    }
}
