package com.bwei.jd_project.mvp.shoppingcar.model;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.model.shoppingcarapi.ShoppingCarApi;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;


public class ShoppingCarModel {

    public Observable<ShoppingCarBean> selectShoppingCar(String url){

        return RetrofitManager.getDefault().create(ShoppingCarApi.class).selectShoppingCar(url);

    }

}