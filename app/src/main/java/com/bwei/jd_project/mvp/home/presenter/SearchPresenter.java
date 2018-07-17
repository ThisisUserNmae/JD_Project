package com.bwei.jd_project.mvp.home.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.home.model.SearchModel;
import com.bwei.jd_project.mvp.home.model.bean.SearchBean;
import com.bwei.jd_project.mvp.home.view.iview.ISearchView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ISearchView iSearchView;

    private SearchModel searchModel;

    public SearchPresenter(ISearchView iSearchView) {
        this.iSearchView = iSearchView;
        searchModel = new SearchModel();
    }

    @SuppressLint("CheckResult")
    public void searchPresenter(String keyword) {

        searchModel.searchModel(HttpConfig.SEARCHKEYWORD_URL + keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(SearchBean searchBean) throws Exception {

                        if (iSearchView != null) {

                            iSearchView.getSceess(searchBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iSearchView != null) {

                            iSearchView.getError(throwable);

                        }

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                });

    }

    public void onDestory() {

        if (iSearchView != null) {

            iSearchView = null;

        }

        compositeDisposable.clear();

    }

}
