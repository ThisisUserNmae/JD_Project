package com.bwei.jd_project.mvp.home.model;

import com.bwei.jd_project.mvp.home.model.api.SearchApi;
import com.bwei.jd_project.mvp.home.model.bean.SearchBean;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;

public class SearchModel {


    public Observable<SearchBean> searchModel(String url){


        return RetrofitManager.getDefault().create(SearchApi.class).serachProduct(url);
    }



}

