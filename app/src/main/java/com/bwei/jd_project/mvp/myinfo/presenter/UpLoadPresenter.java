package com.bwei.jd_project.mvp.myinfo.presenter;

import com.bwei.jd_project.mvp.myinfo.model.UpLoadModel;
import com.bwei.jd_project.mvp.myinfo.model.bean.UpLoadBean;
import com.bwei.jd_project.mvp.myinfo.view.iview.IUpLoadView;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class UpLoadPresenter {

    private IUpLoadView iUpLoadView;

    private UpLoadModel upLoadModel;

    public UpLoadPresenter(IUpLoadView iUpLoadView) {
        this.iUpLoadView = iUpLoadView;
        upLoadModel = new UpLoadModel();
    }

    public void upLoadPresenter(int uid, @Part MultipartBody.Part part) {

        upLoadModel.upLoadModel(uid, part)
                .enqueue(new Callback<UpLoadBean>() {
                    @Override
                    public void onResponse(Call<UpLoadBean> call, Response<UpLoadBean> response) {

                        if (iUpLoadView != null) {

                            UpLoadBean upLoadBean = response.body();

                            iUpLoadView.getSuccess(upLoadBean);

                        }

                    }

                    @Override
                    public void onFailure(Call<UpLoadBean> call, Throwable t) {

                        if (iUpLoadView != null) {

                            iUpLoadView.getError(t);

                        }

                    }
                });

    }

}
