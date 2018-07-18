package com.bwei.jd_project.mvp.myinfo.model;

import com.bwei.jd_project.mvp.myinfo.model.bean.UpLoadBean;
import com.bwei.jd_project.mvp.myinfo.model.myinfoapi.UpLoadApi;
import com.bwei.jd_project.utils.RetrofitManager;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Part;

public class UpLoadModel {


    public Call<UpLoadBean> upLoadModel(int uid, @Part MultipartBody.Part part){


        return RetrofitManager.getDefault().create(UpLoadApi.class).upLoad(uid,part);

    }

}
