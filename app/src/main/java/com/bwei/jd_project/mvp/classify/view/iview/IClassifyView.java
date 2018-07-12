package com.bwei.jd_project.mvp.classify.view.iview;

import com.bwei.jd_project.base.ivew;
import com.bwei.jd_project.mvp.classify.model.bean.ProductCatagoryBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;

public interface IClassifyView extends ivew {

    void getSuccess(CatagoryBean catagoryBean);

    void getError(Throwable throwable);

    void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);

    void getProductCatagoryError(Throwable throwable);

}
