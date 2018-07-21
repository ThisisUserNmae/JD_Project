package com.bwei.jd_project.mvp.shoppingcar.model;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.CommonAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateDefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.showcommonaddressapi.CommonAddressApi;
import com.bwei.jd_project.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Observable;

public class CommonAddressModel {

    public Observable<CommonAddressBean> commonAddressModel(int uid){

        return RetrofitManager.getDefault().create(CommonAddressApi.class).commonAddress(uid);
    }

    public Observable<UpdateDefaultAddressBean> updateDefaultAddressModel(Map<String,String> map){

        return RetrofitManager.getDefault().create(CommonAddressApi.class).updateDefaultAddress(map);
    }


}
