package com.bwei.jd_project.mvp.shoppingcar.model.shoppingcarapi;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.DeleteCartBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateShoppingCarBean;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ShoppingCarApi {

    @GET()
    Observable<ShoppingCarBean> selectShoppingCar(@Url String url);

    @GET()
    Observable<DeleteCartBean> deleteCart(@Url String url);

    @GET(HttpConfig.UPDATESHOPPINGCAR_URL)
    Observable<UpdateShoppingCarBean> updateCart(@QueryMap Map<String, String> map);

}
