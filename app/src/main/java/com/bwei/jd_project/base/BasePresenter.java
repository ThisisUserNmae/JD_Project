package com.bwei.jd_project.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends ivew> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();

    public void onDestroy(){

        if (view!=null){

            view = null;

        }

        compositeDisposable.clear();

    }

}
