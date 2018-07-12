package com.bwei.jd_project.mvp.classify.model;

import com.bwei.jd_project.mvp.classify.model.api.ClassifyApi;
import com.bwei.jd_project.mvp.classify.model.bean.ProductCatagoryBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;


public class ClassifyModel {


    public Observable<CatagoryBean> classifySelect(String url){


        return RetrofitManager.getDefault().create(ClassifyApi.class).productClaassify(url);

    }


    public Observable<ProductCatagoryBean> childClassifySelect(String url){

        return RetrofitManager.getDefault().create(ClassifyApi.class).childrenproductClassify(url);

    }

}
