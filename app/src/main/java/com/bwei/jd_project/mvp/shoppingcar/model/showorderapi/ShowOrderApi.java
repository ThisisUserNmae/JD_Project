package com.bwei.jd_project.mvp.shoppingcar.model.showorderapi;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ShowOrderApi {

    @GET()
    Observable<ShowOrderBean> showOrder (@Url String url);

}
