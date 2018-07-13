package com.bwei.jd_project.mvp.classify.model.api;

import com.bwei.jd_project.mvp.classify.model.bean.ShowProductBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ClassifyShowProductApi {

    @GET()
    Observable<ShowProductBean> showProduct(@Url String url);

}
