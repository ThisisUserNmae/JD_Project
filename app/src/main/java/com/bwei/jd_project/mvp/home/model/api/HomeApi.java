package com.bwei.jd_project.mvp.home.model.api;

import com.bwei.jd_project.mvp.home.model.bean.AdBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface HomeApi {

    @GET()
    Observable<AdBean> AdAndProductClassifyShowAndproductShow(@Url String url);


    @GET()
    Observable<CatagoryBean> catagoryBean(@Url String url);

}
