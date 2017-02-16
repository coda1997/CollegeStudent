package com.example.a60440.collegestudent.bean;

import java.io.Serializable;

/**
 * Created by 60440 on 2017/2/8.
 */

public class QuestionInfo implements Serializable{
    public String id;
    public String title;
    public String date;
    public String content;
    public String foucusnumber;
    public String questionerName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFoucusnumber() {
        return foucusnumber;
    }

    public void setFoucusnumber(String foucusnumber) {
        this.foucusnumber = foucusnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionerImage() {
        return questionerImage;
    }

    public void setQuestionerImage(String questionerImage) {
        this.questionerImage = questionerImage;
    }

    public String getQuestionerName() {
        return questionerName;
    }

    public void setQuestionerName(String questionerName) {
        this.questionerName = questionerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String questionerImage;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
