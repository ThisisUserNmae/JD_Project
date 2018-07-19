package com.bwei.jd_project.mvp.find.model.findapi;

import com.bwei.jd_project.mvp.find.model.bean.BoonBean;
import com.bwei.jd_project.mvp.find.model.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BoonAndNewsAndMenuApi {

    @GET()
    Observable<BoonBean> boon(@Url String url);


    @GET()
    Observable<NewsBean> news(@Url String url);

}
