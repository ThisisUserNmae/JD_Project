package com.bwei.jd_project.mvp.home.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.home.model.HomeShowDetailsModel;
import com.bwei.jd_project.mvp.home.model.bean.AddShoppingCarBean;
import com.bwei.jd_project.mvp.home.model.bean.ProductDetailsCreateOrderBean;
import com.bwei.jd_project.mvp.home.model.bean.ShowDetailsBean;
import com.bwei.jd_project.mvp.home.view.iview.IHomeShowDetailsView;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeDetailsPresenter {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private IHomeShowDetailsView iHomeShowDetailsView;

    private HomeShowDetailsModel homeShowDetailsModel;

    public HomeDetailsPresenter(IHomeShowDetailsView iHomeShowDetailsView) {
        this.iHomeShowDetailsView = iHomeShowDetailsView;
        homeShowDetailsModel = new HomeShowDetailsModel();
    }

    @SuppressLint("CheckResult")
    public void showDetails(int pid) {

        homeShowDetailsModel.showDetailsModel(HttpConfig.SHOWPRODUCTDETAILS_URL + pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowDetailsBean>() {
                    @Override
                    public void accept(ShowDetailsBean showDetailsBean) throws Exception {

                        if (iHomeShowDetailsView != null) {

                            iHomeShowDetailsView.getSuccess(showDetailsBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iHomeShowDetailsView != null) {

                            iHomeShowDetailsView.getError(throwable);

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
    public void addShoppingCarPresenter(int uid, int pid) {

        homeShowDetailsModel.addShoppingCar(HttpConfig.ADDSHOPPINGCAR_URL + uid + "&pid=" + pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddShoppingCarBean>() {
                    @Override
                    public void accept(AddShoppingCarBean addShoppingCarBean) throws Exception {

                        if (iHomeShowDetailsView != null) {

                            iHomeShowDetailsView.getAddShoppingCarSuccess(addShoppingCarBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iHomeShowDetailsView != null) {

                            iHomeShowDetailsView.getAddShoppingCarError(throwable);

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
    public void addOrder(int uid, double price) {

        homeShowDetailsModel.addOrder(HttpConfig.PRODUCTDETAILSADDRODER_URL + "?uid=" + uid + "&price=" + price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductDetailsCreateOrderBean>() {
                    @Override
                    public void accept(ProductDetailsCreateOrderBean productDetailsCreateOrderBean) throws Exception {

                        if (iHomeShowDetailsView != null) {

                            iHomeShowDetailsView.getAddOrderSuccess(productDetailsCreateOrderBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iHomeShowDetailsView != null) {

                            iHomeShowDetailsView.getAddOrderError(throwable);

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

        if (iHomeShowDetailsView != null) {

            iHomeShowDetailsView = null;

        }

        compositeDisposable.clear();

    }

}
