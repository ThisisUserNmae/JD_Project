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
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.bwei.jd_project.mvp.home.view.activity.SelectActivity;
import com.bwei.jd_project.mvp.home.view.adapter.ChildrenProductBaseAdapter;
import com.bwei.jd_project.mvp.home.view.adapter.ProductClassifyShowRecyclerView;
import com.bwei.jd_project.mvp.home.view.adapter.ProductShowRecyclerView;
import com.bwei.jd_project.mvp.home.view.iview.IHomeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, View.OnClickListener {

    private com.stx.xhb.xbanner.XBanner banner;

    private android.support.v7.widget.RecyclerView mainRecyclerViewShowProductClassify;

    private android.support.v7.widget.RecyclerView mainRecyclerViewPhone;

    private MyGridView mainGridView;

    private MarqueeView marqueeView;

    private EditText ed_click;

    private List<String> images = new ArrayList<>();

    private List<String> titles = new ArrayList<>();

    protected void initDatas() {

        presenter.AdPresneter(HttpConfig.AD_URL);

    }

    protected void initViews() {

        mainGridView = view.findViewById(R.id.mainGridView);

        mainRecyclerViewPhone = view.findViewById(R.id.mainRecyclerViewPhone);

        mainRecyclerViewShowProductClassify = view.findViewById(R.id.mainRecyclerViewShowProductClassify);

        banner = view.findViewById(R.id.banner);

        ed_click = view.findViewById(R.id.ed_click);

        marqueeView = view.findViewById(R.id.marqueeView);

        ed_click.setOnClickListener(this);

        presenter = new HomePresenter(this);

        List<String> info = new ArrayList<>();
        info.add("夏季大甩卖,清凉特价！");
        info.add("全场惊爆价大特卖,全部25元,全球最低的价格");
        info.add("25元不算多,去不了香港到不了新加坡");
        info.add("25元不算贵,不用回家去开家长会");
        info.add("25元不算多,买不了房子.买不了车");
        info.add("25元不咋的盖不到房子买不到地");
        info.add("全部25元,全球最低的价格,机会难的数量不多");
        info.add("走过路过,千万不要错过");
        info.add("请想买的顾客抓紧时间抢购吧");
        info.add("夏季大甩卖,清凉特价");
        info.add("惊爆!!! 全场满1999元 获得商品一折优惠");
        info.add("惊爆!!! 全场满5000元 获得一件大牌商品免费优惠");
        info.add("你还在等什么???");
        info.add("洗洗睡吧!!!");
        // 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);

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

            banner.setPageChangeDuration(5000);

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

                    int pid = list.get(position).getPid();

                    Intent it = new Intent(getActivity(), ProductDetailsActivity.class);

                    it.putExtra("pid", pid);

                    startActivity(it);

                    //Toast.makeText(getActivity(),"您的监听成功了"+pid,Toast.LENGTH_LONG).show();

                }
            });

            AdBean.TuijianBean tuijian = adBean.getTuijian();

            final List<AdBean.TuijianBean.ListBean> list1 = tuijian.getList();

            ChildrenProductBaseAdapter adapter = new ChildrenProductBaseAdapter(getActivity(), list1);

            mainGridView.setAdapter(adapter);

            presenter.catagoryPresenter(HttpConfig.CATAGORYBEAN_URL);

            mainGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int pid = list1.get(position).getPid();

                    Intent it = new Intent(getActivity(), ProductDetailsActivity.class);

                    it.putExtra("pid", pid);

                    startActivity(it);

                }
            });

        } else {

            //Toast.makeText(getActivity(), "您失败了", Toast.LENGTH_LONG).show();

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

                    //Toast.makeText(getActivity(),"您点击了"+data.get(position).getName(),Toast.LENGTH_LONG).show();

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

        if (isVisibleToUser && presenter != null) {


        } else {

            presenter = new HomePresenter(this);

            presenter.AdPresneter(HttpConfig.AD_URL);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ed_click:

                Intent it = new Intent(getActivity(), SelectActivity.class);

                startActivity(it);

                break;

        }

    }
}
