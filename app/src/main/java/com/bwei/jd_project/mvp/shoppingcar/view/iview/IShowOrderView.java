package com.bwei.jd_project.mvp.shoppingcar.view.iview;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.DefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateOrderBean;

public interface IShowOrderView {

    void getShowOrderSuccess(ShowOrderBean showOrderBean);
    void getShowOrderError(Throwable throwable);

    void getUpdateOrderSuccess(UpdateOrderBean updateOrderBean);
    void getUpdateOrderError(Throwable throwable);

    void getDefaultAddressSuccess(DefaultAddressBean defaultAddressBean);
    void getDefaultAddressError(Throwable throwable);

}
