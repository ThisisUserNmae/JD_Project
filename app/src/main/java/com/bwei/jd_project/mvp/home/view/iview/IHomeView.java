package com.bwei.jd_project.mvp.home.view.iview;

import com.bwei.jd_project.base.ivew;
import com.bwei.jd_project.mvp.home.model.bean.AdBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;

public interface IHomeView extends ivew {

    void getSceess(AdBean adBean);

    void getError(Throwable throwable);

    void getCatagorySuccess(CatagoryBean catagoryBean);

    void getCatagoryError(Throwable throwable);

}
