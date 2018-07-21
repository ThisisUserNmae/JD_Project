package com.bwei.jd_project.mvp.shoppingcar.model.showcommonaddressapi;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.CommonAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateDefaultAddressBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CommonAddressApi {

    @GET(HttpConfig.COMMONADDRESS_URL)
    Observable<CommonAddressBean> commonAddress(@Query("uid") int uid);

    @GET(HttpConfig.UPDATEDEFAULTADDRESS_UTL)
    Observable<UpdateDefaultAddressBean> updateDefaultAddress(@QueryMap Map<String,String> map);

}
