package com.bwei.jd_project.mvp.home.view.iview;

import com.bwei.jd_project.mvp.home.model.bean.SearchBean;

public interface ISearchView {

    void getSceess(SearchBean searchBean);

    void getError(Throwable throwable);


}
