package com.bwei.jd_project.mvp.myinfo.model.myinfoapi;

import com.bwei.jd_project.mvp.myinfo.model.bean.UpLoadBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UpLoadApi {

    @Multipart
    @POST("file/upload")
    Call<UpLoadBean> upLoad(@Query("uid") int uid, @Part MultipartBody.Part part);

}
