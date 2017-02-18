package com.example.a60440.collegestudent.requestServes;

/**
 * Created by 60440 on 2016/12/25.
 */
import com.example.a60440.collegestudent.bean.VideoInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoServes {
    @POST("servletc/GetVideoServlet")
    Call<List<VideoInfo>> getString(@Query("userId") int userId);

    @POST("servletc/GetMyVideoServlet")
    Call<List<VideoInfo>> getMyVideo(@Query("userid") String id);

    @POST("servletc/RemoveVideoServlet")
    Call<String> deleteVideo(@Query("videoId") String id,
                             @Query("videoUrl") String videoUrl
                             );

}
