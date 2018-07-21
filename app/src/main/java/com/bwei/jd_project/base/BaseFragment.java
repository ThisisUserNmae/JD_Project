package com.bwei.jd_project.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P presenter;

    protected View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(ByLayout(), container, false);

        initViews();

        return view;
    }

    protected abstract void initDatas();

    protected abstract void initViews();

    protected abstract int ByLayout();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && presenter != null) {

            initDatas();

        } else {
            return;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();

    }
}
