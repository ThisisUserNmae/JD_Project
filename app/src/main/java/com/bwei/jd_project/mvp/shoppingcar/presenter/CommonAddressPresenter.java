package com.bwei.jd_project.mvp.shoppingcar.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.mvp.shoppingcar.model.CommonAddressModel;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.CommonAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateDefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.ICommonAddressView;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CommonAddressPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ICommonAddressView iCommonAddressView;

    private CommonAddressModel commonAddressModel;

    public CommonAddressPresenter(ICommonAddressView iCommonAddressView) {
        this.iCommonAddressView = iCommonAddressView;
        commonAddressModel = new CommonAddressModel();
    }

    @SuppressLint("CheckResult")
    public void commonAddressPresenter(int uid) {

        commonAddressModel.commonAddressModel(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonAddressBean>() {
                    @Override
                    public void accept(CommonAddressBean commonAddressBean) throws Exception {

                        if (iCommonAddressView != null) {

                            iCommonAddressView.getCommonAddressSuccess(commonAddressBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iCommonAddressView != null) {

                            iCommonAddressView.getCommonAddressError(throwable);

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
    public void updateDefaultAddress(Map<String,String> map){

        commonAddressModel.updateDefaultAddressModel(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateDefaultAddressBean>() {
                    @Override
                    public void accept(UpdateDefaultAddressBean updateDefaultAddressBean) throws Exception {

                        if (iCommonAddressView != null) {

                            iCommonAddressView.getUpdateDefaultAddressSuccess(updateDefaultAddressBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iCommonAddressView != null) {

                            iCommonAddressView.getUpdateDefaultAddressError(throwable);

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

        if (iCommonAddressView != null) {

            iCommonAddressView = null;

        }
        compositeDisposable.clear();

    }

}
