package com.bwei.jd_project.mvp.classify.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.base.BasePresenter;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.classify.model.ClassifyModel;
import com.bwei.jd_project.mvp.classify.model.bean.ProductCatagoryBean;
import com.bwei.jd_project.mvp.classify.view.iview.IClassifyView;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClassifyPresneter extends BasePresenter<IClassifyView> {

    private ClassifyModel classifyModel;

    public ClassifyPresneter(IClassifyView view) {
        super(view);
        classifyModel = new ClassifyModel();
    }

    @Override
    protected void initModel() {

    }

    @SuppressLint("CheckResult")
    public void classifySelect(String url) {

        classifyModel.classifySelect(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CatagoryBean>() {
                    @Override
                    public void accept(CatagoryBean catagoryBean) throws Exception {

                        if (view != null) {

                            view.getSuccess(catagoryBean);

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
    public void childrenClassifySelect(int cid){


        classifyModel.childClassifySelect(HttpConfig.CHILDRENCLASSIFY_URL+"?cid="+cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductCatagoryBean>() {
                    @Override
                    public void accept(ProductCatagoryBean productCatagoryBean) throws Exception {

                        if (view!=null){

                            view.getProductCatagorySuccess(productCatagoryBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (view!=null){

                            view.getProductCatagoryError(throwable);

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
