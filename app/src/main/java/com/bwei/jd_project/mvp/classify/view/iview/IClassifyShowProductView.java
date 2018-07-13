package com.bwei.jd_project.mvp.classify.view.iview;

import com.bwei.jd_project.mvp.classify.model.bean.ShowProductBean;

public interface IClassifyShowProductView {

    void getSuccess(ShowProductBean showProductBean);

    void getError(Throwable throwable);

}
