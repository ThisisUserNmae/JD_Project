package com.bwei.jd_project.mvp.find.model;

import com.bwei.jd_project.mvp.find.model.bean.BoonBean;
import com.bwei.jd_project.mvp.find.model.bean.NewsBean;
import com.bwei.jd_project.mvp.find.model.findapi.BoonAndNewsAndMenuApi;
import com.bwei.jd_project.utils.RetrofitManager;

import io.reactivex.Observable;

public class BoonAndNewsAndMenuModel {


    public Observable<BoonBean> boonModel(String url){

        return RetrofitManager.getDefault().create(BoonAndNewsAndMenuApi.class).boon(url);
    }

    public Observable<NewsBean> newsModel(String url){

        return RetrofitManager.getDefault().create(BoonAndNewsAndMenuApi.class).news(url);
    }


}
