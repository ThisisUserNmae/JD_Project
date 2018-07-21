package com.bwei.jd_project.mvp.shoppingcar.model.addcommonaddressapi;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddCommonAddressBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface AddCommonAddressApi {

    @GET("user/addAddr")
    Observable<AddCommonAddressBean> addCommonAddress(@QueryMap Map<String,String> map);

}
