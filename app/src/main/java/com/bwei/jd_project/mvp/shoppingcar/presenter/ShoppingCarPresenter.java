package com.bwei.jd_project.mvp.shoppingcar.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.base.BasePresenter;
import com.bwei.jd_project.mvp.shoppingcar.model.ShoppingCarModel;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShoppingCarView;

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
    public void selectShoppingCar(String url){

        shoppingCarModel.selectShoppingCar(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShoppingCarBean>() {
                    @Override
                    public void accept(ShoppingCarBean shoppingCarBean) throws Exception {

                        if (view!=null){

                            view.getSuccess(shoppingCarBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (view!=null){

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

}
