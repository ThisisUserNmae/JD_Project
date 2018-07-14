package com.bwei.jd_project.mvp.home.model.api;

import com.bwei.jd_project.mvp.home.model.bean.AddShoppingCarBean;
import com.bwei.jd_project.mvp.home.model.bean.ShowDetailsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface HomeShowDetailsApi {

    @GET()
    Observable<ShowDetailsBean> showDetails(@Url String url);

    @GET()
    Observable<AddShoppingCarBean> addShoppingCar(@Url String url);

}
