package com.bwei.jd_project.mvp.shoppingcar.view.iview;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddCommonAddressBean;

public interface IAddCommonAddressView {

    void getAddCommonAddressSuccess(AddCommonAddressBean addCommonAddressBean);
    void getAddCommonAddressError(Throwable throwable);

}
