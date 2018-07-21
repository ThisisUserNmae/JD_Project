package com.bwei.jd_project.mvp.shoppingcar.model;

import com.bwei.jd_project.mvp.shoppingcar.model.addcommonaddressapi.AddCommonAddressApi;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddCommonAddressBean;
import com.bwei.jd_project.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Observable;

public class AddCommonAddressModel {

    public Observable<AddCommonAddressBean> addCommonAddressModel(Map<String,String> map){

        return RetrofitManager.getDefault().create(AddCommonAddressApi.class).addCommonAddress(map);

    }

}

