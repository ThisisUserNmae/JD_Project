package com.bwei.jd_project.mvp.myinfo.view.iview;

import com.bwei.jd_project.base.ivew;
import com.bwei.jd_project.mvp.myinfo.model.bean.LoginBean;

public interface IMyInfoView extends ivew {


    void getSuccess(LoginBean loginBean);

    void getError(Throwable throwable);
}
