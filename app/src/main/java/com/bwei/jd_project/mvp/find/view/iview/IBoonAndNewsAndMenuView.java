package com.bwei.jd_project.mvp.find.view.iview;

import com.bwei.jd_project.mvp.find.model.bean.BoonBean;
import com.bwei.jd_project.mvp.find.model.bean.NewsBean;

public interface IBoonAndNewsAndMenuView {

    void getBoonSuccess(BoonBean boonBean);

    void getBoonError(Throwable throwable);

    void getNewsSuccess(NewsBean newsBean);

    void getNewsError(Throwable throwable);

}
