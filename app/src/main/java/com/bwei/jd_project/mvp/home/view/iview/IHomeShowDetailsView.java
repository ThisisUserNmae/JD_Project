package com.bwei.jd_project.mvp.home.view.iview;

import com.bwei.jd_project.mvp.home.model.bean.AddShoppingCarBean;
import com.bwei.jd_project.mvp.home.model.bean.ProductDetailsCreateOrderBean;
import com.bwei.jd_project.mvp.home.model.bean.ShowDetailsBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;

public interface IHomeShowDetailsView {

    void getSuccess(ShowDetailsBean showDetailsBean);
    void getError(Throwable throwable);

    void getAddShoppingCarSuccess(AddShoppingCarBean addShoppingCarBean);
    void getAddShoppingCarError(Throwable throwable);

    void getAddOrderSuccess(ProductDetailsCreateOrderBean productDetailsCreateOrderBean);
    void getAddOrderError(Throwable throwable);

}
