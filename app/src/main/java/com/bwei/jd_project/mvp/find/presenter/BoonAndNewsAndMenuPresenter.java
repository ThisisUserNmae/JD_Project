package com.bwei.jd_project.mvp.find.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.mvp.find.model.BoonAndNewsAndMenuModel;
import com.bwei.jd_project.mvp.find.model.bean.BoonBean;
import com.bwei.jd_project.mvp.find.model.bean.NewsBean;
import com.bwei.jd_project.mvp.find.view.iview.IBoonAndNewsAndMenuView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BoonAndNewsAndMenuPresenter {

    private BoonAndNewsAndMenuModel findModel;

    private IBoonAndNewsAndMenuView iFindView;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BoonAndNewsAndMenuPresenter(IBoonAndNewsAndMenuView iFindView) {
        findModel = new BoonAndNewsAndMenuModel();
        this.iFindView = iFindView;
    }

    @SuppressLint("CheckResult")
    public void boonPresenter(String url) {

        findModel.boonModel(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BoonBean>() {
                    @Override
                    public void accept(BoonBean boonBean) throws Exception {

                        if (iFindView != null) {

                            iFindView.getBoonSuccess(boonBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iFindView != null) {

                            iFindView.getBoonError(throwable);

                        }

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                        compositeDisposable.add(disposable);

                    }
                });

    }


    @SuppressLint("CheckResult")
    public void newsPresenter(String url) {

        findModel.newsModel(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(NewsBean newsBean) throws Exception {

                        if (iFindView != null) {

                            iFindView.getNewsSuccess(newsBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iFindView != null) {

                            iFindView.getNewsError(throwable);

                        }

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {


                        compositeDisposable.add(disposable);

                    }
                });


    }


    public void onDestory() {

        if (iFindView != null) {

            iFindView = null;

        }

        compositeDisposable.clear();

    }

}
