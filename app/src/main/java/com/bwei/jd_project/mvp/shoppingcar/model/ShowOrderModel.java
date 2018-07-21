package com.bwei.jd_project.mvp.shoppingcar.model;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.DefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.showorderapi.ShowOrderApi;
import com.bwei.jd_project.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Observable;

public class ShowOrderModel {

    public Observable<ShowOrderBean> showOrderModel(String url){

        return RetrofitManager.getDefault().create(ShowOrderApi.class).showOrder(url);
    }

    public Observable<UpdateOrderBean> updateOrder(Map<String,String> map){

        return RetrofitManager.getDefault().create(ShowOrderApi.class).updateOrder(map);
    }

    public Observable<DefaultAddressBean> defaultAddressModel(int uid){

        return RetrofitManager.getDefault().create(ShowOrderApi.class).defaultAddress(uid);
    }

}
