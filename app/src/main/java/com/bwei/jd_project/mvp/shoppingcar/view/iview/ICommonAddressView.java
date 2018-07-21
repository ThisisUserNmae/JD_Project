package com.bwei.jd_project.mvp.shoppingcar.view.iview;

import com.bwei.jd_project.mvp.shoppingcar.model.bean.CommonAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateDefaultAddressBean;

public interface ICommonAddressView {

    void getCommonAddressSuccess(CommonAddressBean commonAddressBean);

    void getCommonAddressError(Throwable throwable);

    void getUpdateDefaultAddressSuccess(UpdateDefaultAddressBean updateDefaultAddressBean);

    void getUpdateDefaultAddressError(Throwable throwable);


}
