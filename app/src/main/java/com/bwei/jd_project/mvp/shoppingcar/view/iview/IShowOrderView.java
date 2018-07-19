package com.bwei.jd_project.mvp.shoppingcar.view.iview;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;

public interface IShowOrderView {

    void getShowOrderSuccess(ShowOrderBean showOrderBean);

    void getShowOrderError(Throwable throwable);
}
