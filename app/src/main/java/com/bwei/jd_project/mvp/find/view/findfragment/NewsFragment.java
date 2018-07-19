package com.bwei.jd_project.mvp.find.view.findfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.find.model.bean.BoonBean;
import com.bwei.jd_project.mvp.find.model.bean.NewsBean;
import com.bwei.jd_project.mvp.find.presenter.BoonAndNewsAndMenuPresenter;
import com.bwei.jd_project.mvp.find.view.adapter.NewsRecyclerView;
import com.bwei.jd_project.mvp.find.view.iview.IBoonAndNewsAndMenuView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class NewsFragment extends Fragment implements IBoonAndNewsAndMenuView {


    private View view;

    private BoonAndNewsAndMenuPresenter boonAndNewsAndMenuPresenter;

    private RecyclerView newsRecyclerView;

    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.find_newsfragment_layout, container, false);

        initViews();

        initDatas();

        return view;

    }

    private void initDatas() {


    }

    private void initViews() {

        newsRecyclerView = view.findViewById(R.id.newsRecyclerView);

        boonAndNewsAndMenuPresenter = new BoonAndNewsAndMenuPresenter(this);

    }


    public static NewsFragment create(String title) {

        Bundle bundle = new Bundle();

        bundle.putString("title", title);

        NewsFragment newsFragment = new NewsFragment();

        newsFragment.setArguments(bundle);

        return newsFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void getBoonSuccess(BoonBean boonBean) {

    }

    @Override
    public void getBoonError(Throwable throwable) {

    }

    @Override
    public void getNewsSuccess(NewsBean newsBean) {


        int error_code = newsBean.getError_code();

        if (error_code == 0) {

            Toast.makeText(getActivity(), "新闻请求成功", Toast.LENGTH_SHORT).show();

            NewsBean.ResultBean result = newsBean.getResult();

            List<NewsBean.ResultBean.DataBean> data = result.getData();

            NewsRecyclerView newsRecyclerView0 = new NewsRecyclerView(getActivity(), data);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

            newsRecyclerView.setLayoutManager(linearLayoutManager);

            newsRecyclerView.setAdapter(newsRecyclerView0);

        } else {

            Toast.makeText(getActivity(), "新闻请求失败", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void getNewsError(Throwable throwable) {

        Toast.makeText(getActivity(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && boonAndNewsAndMenuPresenter != null) {

            boonAndNewsAndMenuPresenter.newsPresenter(HttpConfig.NEWS_URL);

        }
    }
}
