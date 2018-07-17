package com.bwei.jd_project.mvp.myinfo.model.myinfoapi;

import com.bwei.jd_project.mvp.myinfo.model.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MyInfoApi {

    @GET()
    Observable<LoginBean> login (@Url String url);

}
