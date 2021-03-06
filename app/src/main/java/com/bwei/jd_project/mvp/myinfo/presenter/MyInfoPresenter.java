package com.bwei.jd_project.mvp.myinfo.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.myinfo.model.MyInfoModel;
import com.bwei.jd_project.mvp.myinfo.model.bean.LoginBean;
import com.bwei.jd_project.mvp.myinfo.view.iview.IMyInfoView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyInfoPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MyInfoModel myInfoModel;

    private IMyInfoView iMyInfoView;

    public MyInfoPresenter(IMyInfoView iMyInfoView) {
        myInfoModel = new MyInfoModel();
        this.iMyInfoView = iMyInfoView;
    }

    @SuppressLint("CheckResult")
    public void loginPresenter(String mobile, String password) {

        myInfoModel.loginModle(HttpConfig.LOGIN_URL + "?mobile=" + mobile + "&password=" + password)

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {

                        if (iMyInfoView != null) {

                            iMyInfoView.getSuccess(loginBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iMyInfoView != null) {

                            iMyInfoView.getError(throwable);

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

        if (iMyInfoView != null) {

            iMyInfoView = null;

        }

        compositeDisposable.clear();

    }
}
