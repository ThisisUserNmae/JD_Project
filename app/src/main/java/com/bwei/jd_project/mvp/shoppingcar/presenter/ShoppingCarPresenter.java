package com.bwei.jd_project.mvp.shoppingcar.presenter;

import android.annotation.SuppressLint;
import android.net.wifi.aware.PublishConfig;

import com.bwei.jd_project.base.BasePresenter;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.shoppingcar.model.ShoppingCarModel;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.DeleteCartBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShoppingCarView;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCarPresenter extends BasePresenter<IShoppingCarView> {

    private ShoppingCarModel shoppingCarModel;

    public ShoppingCarPresenter(IShoppingCarView view) {
        super(view);

    }

    @Override
    protected void initModel() {

        shoppingCarModel = new ShoppingCarModel();

    }

    @SuppressLint("CheckResult")
    public void selectShoppingCar(String url) {

        shoppingCarModel.selectShoppingCar(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShoppingCarBean>() {
                    @Override
                    public void accept(ShoppingCarBean shoppingCarBean) throws Exception {

                        if (view != null) {

                            view.getSuccess(shoppingCarBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (view != null) {

                            view.getError(throwable);

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
    public void deleteCart(int uid, int pid) {

        shoppingCarModel.deleteCart(HttpConfig.DELETESHHOPPINGCAR_URL + "?uid=" + uid + "&pid=" + pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DeleteCartBean>() {
                    @Override
                    public void accept(DeleteCartBean deleteCartBean) throws Exception {

                        if (view != null) {

                            view.getDeleteCartSuccess(deleteCartBean);

                        }


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (view != null) {

                            view.getDeleteCarError(throwable);

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
    public void updateCaartPresenter(Map<String, String> map) {

        shoppingCarModel.updateCartModel(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateShoppingCarBean>() {
                    @Override
                    public void accept(UpdateShoppingCarBean updateShoppingCarBean) throws Exception {

                        if (view != null) {

                            view.getUpdateCartSuccess(updateShoppingCarBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (view != null) {

                            view.getUpdateCartError(throwable);

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
    public void addOrderPresenter(int uid,double price){

        shoppingCarModel.addOrderModel(HttpConfig.ADDRODER_URL+"?uid="+uid+"&price="+price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddOrderBean>() {
                    @Override
                    public void accept(AddOrderBean addOrderBean) throws Exception {

                        if (view != null) {

                            view.getAddOrderSuccess(addOrderBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (view != null) {

                            view.getAddOrderError(throwable);

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

}
