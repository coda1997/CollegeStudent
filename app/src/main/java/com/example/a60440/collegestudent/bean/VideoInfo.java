package com.example.a60440.collegestudent.bean;

/**
 * Created by 60440 on 2017/2/8.
 */

public class VideoInfo {
    @Override
    public String toString() {
        return "VideoInfo{" +
                "playnumber=" + playnumber +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoId=" + videoId +
                '}';
    }

    public String videoTitle;
    public String videoUrl;
    public int videoId;
    public int playnumber;
}
