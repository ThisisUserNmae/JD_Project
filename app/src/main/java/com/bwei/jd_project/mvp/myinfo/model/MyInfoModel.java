package com.bwei.jd_project.mvp.myinfo.model;

import com.bwei.jd_project.mvp.myinfo.model.bean.LoginBean;
import com.bwei.jd_project.mvp.myinfo.model.myinfoapi.MyInfoApi;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;

public class MyInfoModel {

    public Observable<LoginBean> loginModle(String url){


        return RetrofitManager.getDefault().create(MyInfoApi.class).login(url);
    }


}
