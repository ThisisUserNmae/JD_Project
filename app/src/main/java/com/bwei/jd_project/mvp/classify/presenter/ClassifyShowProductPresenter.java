package com.bwei.jd_project.mvp.classify.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.classify.model.ClassifyShowProductModel;
import com.bwei.jd_project.mvp.classify.model.bean.ShowProductBean;
import com.bwei.jd_project.mvp.classify.view.iview.IClassifyShowProductView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClassifyShowProductPresenter {

    private IClassifyShowProductView iClassifyShowProductView;


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ClassifyShowProductModel classifyShowProductModel;

    public ClassifyShowProductPresenter(IClassifyShowProductView iClassifyShowProductView) {
        this.iClassifyShowProductView = iClassifyShowProductView;
        classifyShowProductModel = new ClassifyShowProductModel();
    }

    @SuppressLint("CheckResult")
    public void showProduct(int pscid, String sort) {

        classifyShowProductModel.showProductModel(HttpConfig.SHOWCHILDRENPRODUCT_URL + pscid + "&sort=" + sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowProductBean>() {
                    @Override
                    public void accept(ShowProductBean showProductBean) throws Exception {

                        if (iClassifyShowProductView != null) {

                            iClassifyShowProductView.getSuccess(showProductBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iClassifyShowProductView != null) {

                            iClassifyShowProductView.getError(throwable);

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

    public void oonDestory() {

        if (iClassifyShowProductView != null) {

            iClassifyShowProductView = null;

        }
        compositeDisposable.clear();

    }

}
