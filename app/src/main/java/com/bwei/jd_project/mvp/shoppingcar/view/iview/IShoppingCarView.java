package com.bwei.jd_project.mvp.shoppingcar.view.iview;

import com.bwei.jd_project.base.ivew;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.DeleteCartBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateShoppingCarBean;

public interface IShoppingCarView extends ivew {

    void getSuccess(ShoppingCarBean shoppingCarBean);
    void getError(Throwable throwable);

    void getDeleteCartSuccess(DeleteCartBean deleteCartBean);
    void getDeleteCarError(Throwable throwable);

    void getUpdateCartSuccess(UpdateShoppingCarBean updateShoppingCarBean);
    void getUpdateCartError(Throwable throwable);

    void getAddOrderSuccess(AddOrderBean addOrderBean);
    void getAddOrderError(Throwable throwable);

}
