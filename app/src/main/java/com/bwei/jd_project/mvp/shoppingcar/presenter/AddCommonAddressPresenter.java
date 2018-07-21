package com.bwei.jd_project.mvp.shoppingcar.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.mvp.shoppingcar.model.AddCommonAddressModel;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddCommonAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateDefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IAddCommonAddressView;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddCommonAddressPresenter {

    private IAddCommonAddressView iAddCommonAddressView;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private AddCommonAddressModel addCommonAddressModel;

    public AddCommonAddressPresenter(IAddCommonAddressView iAddCommonAddressView) {
        this.iAddCommonAddressView = iAddCommonAddressView;
        addCommonAddressModel = new AddCommonAddressModel();
    }

    @SuppressLint("CheckResult")
    public void addCommonAddress(Map<String,String> map){

        addCommonAddressModel.addCommonAddressModel(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCommonAddressBean>() {
                    @Override
                    public void accept(AddCommonAddressBean addCommonAddressBean) throws Exception {

                        if (iAddCommonAddressView != null) {

                            iAddCommonAddressView.getAddCommonAddressSuccess(addCommonAddressBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iAddCommonAddressView != null) {

                            iAddCommonAddressView.getAddCommonAddressError(throwable);

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

    public void onDestory(){

        if (iAddCommonAddressView!=null){

            iAddCommonAddressView =null;

        }
        compositeDisposable.clear();

    }

}
