package com.bwei.jd_project.mvp.home.view.iview;

import com.bwei.jd_project.mvp.home.model.bean.AddShoppingCarBean;
import com.bwei.jd_project.mvp.home.model.bean.ShowDetailsBean;

public interface IHomeShowDetailsView {

    void getSuccess(ShowDetailsBean showDetailsBean);

    void getError(Throwable throwable);

    void getAddShoppingCarSuccess(AddShoppingCarBean addShoppingCarBean);

    void getAddShoppingCarError(Throwable throwable);

}
