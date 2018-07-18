package com.bwei.jd_project.mvp.myinfo.view.iview;

import com.bwei.jd_project.mvp.myinfo.model.bean.UpLoadBean;

public interface IUpLoadView {

    void getSuccess(UpLoadBean upLoadBean);

    void getError(Throwable throwable);

}
