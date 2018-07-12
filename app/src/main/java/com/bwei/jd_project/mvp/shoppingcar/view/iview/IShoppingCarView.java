package com.bwei.jd_project.mvp.shoppingcar.view.iview;

import com.bwei.jd_project.base.ivew;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;

public interface IShoppingCarView extends ivew {

    void getSuccess(ShoppingCarBean shoppingCarBean);

    void getError(Throwable throwable);

}
