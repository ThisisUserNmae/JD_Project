package com.bwei.jd_project.mvp.classify.model;

import com.bwei.jd_project.mvp.classify.model.api.ClassifyShowProductApi;
import com.bwei.jd_project.mvp.classify.model.bean.ShowProductBean;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;

public class ClassifyShowProductModel {

    public Observable<ShowProductBean> showProductModel(String url){

        return RetrofitManager.getDefault().create(ClassifyShowProductApi.class).showProduct(url);
    }

}
