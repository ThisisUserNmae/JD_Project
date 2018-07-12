package com.bwei.jd_project.mvp.classify.model.api;

import com.bwei.jd_project.mvp.classify.model.bean.ProductCatagoryBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ClassifyApi {

    @GET()
    Observable<CatagoryBean> productClaassify (@Url String url);

    @GET()
    Observable<ProductCatagoryBean> childrenproductClassify (@Url String url);

}
