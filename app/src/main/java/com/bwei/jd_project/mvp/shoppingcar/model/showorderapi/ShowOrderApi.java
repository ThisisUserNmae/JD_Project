package com.bwei.jd_project.mvp.shoppingcar.model.showorderapi;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.DefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateOrderBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ShowOrderApi {

    @GET()
    Observable<ShowOrderBean> showOrder (@Url String url);

    @GET(HttpConfig.UPDATEORDER_URL)
    Observable<UpdateOrderBean> updateOrder (@QueryMap Map<String,String> map);

    @GET(HttpConfig.DEFAULTADDRESS_URL)
    Observable<DefaultAddressBean> defaultAddress (@Query("uid") int uid);

}
