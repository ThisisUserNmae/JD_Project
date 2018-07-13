package com.bwei.jd_project.mvp.home.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.jd_project.R;
import com.bwei.jd_project.base.BaseFragment;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.home.model.MyGridView;
import com.bwei.jd_project.mvp.home.model.bean.AdBean;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;
import com.bwei.jd_project.mvp.home.presenter.HomePresenter;
import com.bwei.jd_project.mvp.home.view.activity.ProductDetailsActivity;
import com.bwei.jd_project.mvp.home.view.adapter.ChildrenProductBaseAdapter;
import com.bwei.jd_project.mvp.home.view.adapter.ProductClassifyShowRecyclerView;
import com.bwei.jd_project.mvp.home.view.adapter.ProductShowRecyclerView;
import com.bwei.jd_project.mvp.home.view.iview.IHomeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {

    private com.stx.xhb.xbanner.XBanner banner;

    private android.support.v7.widget.RecyclerView mainRecyclerViewShowProductClassify;

    private android.support.v7.widget.RecyclerView mainRecyclerViewPhone;

    private MyGridView mainGridView;

    private List<String> images = new ArrayList<>();

    private List<String> titles = new ArrayList<>();


    protected void initDatas() {



    }

    protected void initViews() {

        mainGridView = view.findViewById(R.id.mainGridView);
        mainRecyclerViewPhone = (RecyclerView) view.findViewById(R.id.mainRecyclerViewPhone);
        mainRecyclerViewShowProductClassify = (RecyclerView) view.findViewById(R.id.mainRecyclerViewShowProductClassify);
        banner = (XBanner) view.findViewById(R.id.banner);

        presenter = new HomePresenter(this);

    }

    @Override
    protected int ByLayout() {
        return R.layout.home_fragment_layout;
    }

    @Override
    public void getSceess(AdBean adBean) {

        String code = adBean.getCode();

        images.clear();
        titles.clear();

        if ("0".equals(code)) {

            List<AdBean.DataBean> data = adBean.getData();

            for (int i = 0; i < data.size(); i++) {

                String icon = data.get(i).getIcon();

                String title = data.get(i).getTitle();
                images.add(icon);
                titles.add(title);

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

            banner.setPageTransformer(Transformer.Cube);

            banner.setPageChangeDuration(3000);

            AdBean.MiaoshaBean miaosha = adBean.getMiaosha();

            final List<AdBean.MiaoshaBean.ListBeanX> list = miaosha.getList();

            ProductShowRecyclerView productShowRecyclerView = new ProductShowRecyclerView(getActivity(), list);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);

            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            mainRecyclerViewPhone.setLayoutManager(gridLayoutManager);

            mainRecyclerViewPhone.setAdapter(productShowRecyclerView);

            productShowRecyclerView.setSetOnClickListener(new ProductShowRecyclerView.setOnClickListener() {
                @Override
                public void onClickListener(View view, int position) {

/*                    int pid = list.get(position).getPid();

                    Intent it = new Intent(getActivity(), ProductDetailsActivity.class);

                    it.putExtra("pid",pid);

                    startActivity(it);*/

                    Toast.makeText(getActivity(),"您的监听成功了",Toast.LENGTH_LONG).show();

                }
            });

            AdBean.TuijianBean tuijian = adBean.getTuijian();

            List<AdBean.TuijianBean.ListBean> list1 = tuijian.getList();

            ChildrenProductBaseAdapter adapter = new ChildrenProductBaseAdapter(getActivity(), list1);

            mainGridView.setAdapter(adapter);

            presenter.catagoryPresenter(HttpConfig.CATAGORYBEAN_URL);

        } else {

            Toast.makeText(getActivity(), "您失败了", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getError(Throwable throwable) {

    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {

        String code = catagoryBean.getCode();

        if ("0".equals(code)) {

            final List<CatagoryBean.DataBean> data = catagoryBean.getData();

            ProductClassifyShowRecyclerView productClassifyShowRecyclerView = new ProductClassifyShowRecyclerView(getActivity(), data);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            mainRecyclerViewShowProductClassify.setLayoutManager(gridLayoutManager);

            mainRecyclerViewShowProductClassify.setAdapter(productClassifyShowRecyclerView);

            productClassifyShowRecyclerView.setSetOnClickListener(new ProductClassifyShowRecyclerView.setOnClickListener() {
                @Override
                public void OnClickListener(View view, int position) {

                    Toast.makeText(getActivity(),"您点击了"+data.get(position).getName(),Toast.LENGTH_LONG).show();

                }
            });

        }

    }

    @Override
    public void getCatagoryError(Throwable throwable) {

    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && presenter !=null){

            presenter.AdPresneter(HttpConfig.AD_URL);

        }else{

            presenter = new HomePresenter(this);

            presenter.AdPresneter(HttpConfig.AD_URL);

        }
    }
}
