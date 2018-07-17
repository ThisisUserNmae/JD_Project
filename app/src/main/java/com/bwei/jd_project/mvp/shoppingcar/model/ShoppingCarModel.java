package com.bwei.jd_project.mvp.shoppingcar.model;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.DeleteCartBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.model.shoppingcarapi.ShoppingCarApi;
import com.bwei.jd_project.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Observable;


public class ShoppingCarModel {

    public Observable<ShoppingCarBean> selectShoppingCar(String url){

        return RetrofitManager.getDefault().create(ShoppingCarApi.class).selectShoppingCar(url);

    }

    public Observable<DeleteCartBean> deleteCart(String url){

        return RetrofitManager.getDefault().create(ShoppingCarApi.class).deleteCart(url);

    }

    public Observable<UpdateShoppingCarBean> updateCartModel(Map<String,String> map){

        return RetrofitManager.getDefault().create(ShoppingCarApi.class).updateCart(map);

    }

}
