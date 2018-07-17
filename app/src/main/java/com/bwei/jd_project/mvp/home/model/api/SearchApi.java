package com.bwei.jd_project.mvp.home.model.api;

import com.bwei.jd_project.mvp.home.model.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SearchApi {

    @GET()
    Observable<SearchBean> serachProduct(@Url String url);


}
