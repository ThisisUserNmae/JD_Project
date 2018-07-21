package com.bwei.jd_project.mvp.home.model;

import com.bwei.jd_project.mvp.home.model.api.HomeShowDetailsApi;
import com.bwei.jd_project.mvp.home.model.bean.AddShoppingCarBean;
import com.bwei.jd_project.mvp.home.model.bean.ProductDetailsCreateOrderBean;
import com.bwei.jd_project.mvp.home.model.bean.ShowDetailsBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.shoppingcarapi.ShoppingCarApi;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;

public class HomeShowDetailsModel {

    public Observable<ShowDetailsBean> showDetailsModel(String url){

        return RetrofitManager.getDefault().create(HomeShowDetailsApi.class).showDetails(url);

    }

    public Observable<AddShoppingCarBean> addShoppingCar(String url){

        return RetrofitManager.getDefault().create(HomeShowDetailsApi.class).addShoppingCar(url);

    }

    public Observable<ProductDetailsCreateOrderBean> addOrder(String url){

        return RetrofitManager.getDefault().create(HomeShowDetailsApi.class).addOrder(url);

    }


}
