package com.bwei.jd_project.mvp.shoppingcar.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.shoppingcar.model.ShowOrderModel;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShowOrderView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowOrderPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private IShowOrderView iShowOrderView;

    private ShowOrderModel showOrderModel;

    public ShowOrderPresenter(IShowOrderView iShowOrderView) {
        this.iShowOrderView = iShowOrderView;
        showOrderModel = new ShowOrderModel();
    }

    @SuppressLint("CheckResult")
    public void showOrderPresenter(int uid,int page) {

        showOrderModel.showOrderModel(HttpConfig.SHOWORDER_URL + "uid=" + uid+"&page="+page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowOrderBean>() {
                    @Override
                    public void accept(ShowOrderBean showOrderBean) throws Exception {

                        if (iShowOrderView != null) {

                            iShowOrderView.getShowOrderSuccess(showOrderBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iShowOrderView != null) {

                            iShowOrderView.getShowOrderError(throwable);

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

        if (iShowOrderView != null) {

            iShowOrderView = null;

        }

        compositeDisposable.clear();

    }


}
