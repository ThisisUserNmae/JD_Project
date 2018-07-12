package com.bwei.jd_project.mvp.home.model;

import com.bwei.jd_project.mvp.home.model.api.HomeApi;
import com.bwei.jd_project.mvp.home.model.bean.AdBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;


public class HomeModel {

    public Observable<AdBean> adModel(String url){

        return RetrofitManager.getDefault().create(HomeApi.class).AdAndProductClassifyShowAndproductShow(url);
    }


    public Observable<CatagoryBean> catagoryBeanModel(String url){

        return RetrofitManager.getDefault().create(HomeApi.class).catagoryBean(url);
    }

}
