package com.bwei.jd_project.mvp.home.presenter;

import android.annotation.SuppressLint;

import com.bwei.jd_project.base.BasePresenter;
import com.bwei.jd_project.mvp.home.model.HomeModel;
import com.bwei.jd_project.mvp.home.model.bean.AdBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;
import com.bwei.jd_project.mvp.home.view.iview.IHomeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {

    private HomeModel homeModel;

    public HomePresenter(IHomeView view) {
        super(view);
    }

    @Override
    protected void initModel() {

        homeModel = new HomeModel();

    }

    @SuppressLint("CheckResult")
    public void AdPresneter(String url){

         homeModel.adModel(url)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<AdBean>() {
                     @Override
                     public void accept(AdBean adBean) throws Exception {

                         if (view !=null){

                             view.getSceess(adBean);

                         }

                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {


                         if (view !=null){

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
    public void catagoryPresenter(String url){

        homeModel.catagoryBeanModel(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CatagoryBean>() {
                    @Override
                    public void accept(CatagoryBean catagoryBean) throws Exception {

                        if (view!=null){

                            view.getCatagorySuccess(catagoryBean);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (view!=null){

                            view.getCatagoryError(throwable);

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
