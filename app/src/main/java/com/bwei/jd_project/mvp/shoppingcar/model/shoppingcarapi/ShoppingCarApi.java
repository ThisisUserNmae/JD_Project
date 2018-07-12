package com.bwei.jd_project.mvp.shoppingcar.model.shoppingcarapi;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ShoppingCarApi {

    @GET()
    Observable<ShoppingCarBean> selectShoppingCar (@Url String url);

}
