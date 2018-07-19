package com.bwei.jd_project.mvp.find.view.findfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwei.jd_project.R;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.find.model.bean.BoonBean;
import com.bwei.jd_project.mvp.find.model.bean.NewsBean;
import com.bwei.jd_project.mvp.find.presenter.BoonAndNewsAndMenuPresenter;
import com.bwei.jd_project.mvp.find.view.iview.IBoonAndNewsAndMenuView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class BoonFragment extends Fragment implements IBoonAndNewsAndMenuView {

    private View view;

    private BoonAndNewsAndMenuPresenter findPresenter;

    private XBanner banner;

    private int page = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.find_boonfragment_layout, container, false);

        initViews();

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initDatas();

    }

    private void initDatas() {


    }

    private void initViews() {

        banner = view.findViewById(R.id.banner);
        findPresenter = new BoonAndNewsAndMenuPresenter(this);

    }

    public static BoonFragment create(String title) {

        Bundle bundle = new Bundle();

        bundle.putString("title", title);

        BoonFragment boonFragment = new BoonFragment();

        boonFragment.setArguments(bundle);

        return boonFragment;
    }

    @Override
    public void getBoonSuccess(BoonBean boonBean) {

        final List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        List<BoonBean.ResultsBean> results = boonBean.getResults();

        for (int i = 0; i < results.size(); i++) {

            String url = results.get(i).getUrl();

            String createdAt = results.get(i).getCreatedAt();

            images.add(url);

            titles.add(createdAt);

        }

        // 为XBanner绑定数据
        banner.setData(images, titles);

        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(images.get(position)).into((ImageView) view);
            }
        });


/*        if (images.size() == titles.size()-1){

            images.clear();

            titles.clear();

            page++;

            findPresenter.boonPresenter(HttpConfig.BOON_URL+page);

        }*/


        banner.setPageTransformer(Transformer.Accordion);

        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(4000);

    }

    @Override
    public void getBoonError(Throwable throwable) {


    }

    @Override
    public void getNewsSuccess(NewsBean newsBean) {

    }

    @Override
    public void getNewsError(Throwable throwable) {

    }

    @Override
    public void onResume() {

        super.onResume();

        banner.startAutoPlay();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && findPresenter != null) {

            findPresenter.boonPresenter(HttpConfig.BOON_URL+page);

        } else {

            findPresenter = new BoonAndNewsAndMenuPresenter(this);

            findPresenter.boonPresenter(HttpConfig.BOON_URL+page);

        }


    }
}
