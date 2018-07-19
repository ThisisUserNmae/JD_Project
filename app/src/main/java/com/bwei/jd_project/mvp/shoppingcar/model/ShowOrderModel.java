package com.bwei.jd_project.mvp.shoppingcar.model;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.showorderapi.ShowOrderApi;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;

public class ShowOrderModel {

    public Observable<ShowOrderBean> showOrderModel(String url){

        return RetrofitManager.getDefault().create(ShowOrderApi.class).showOrder(url);
    }

}
