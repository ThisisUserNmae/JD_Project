package com.bwei.jd_project.mvp.classify.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.base.BaseFragment;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.classify.model.bean.ProductCatagoryBean;
import com.bwei.jd_project.mvp.classify.presenter.ClassifyPresneter;
import com.bwei.jd_project.mvp.classify.view.activity.ShowProductActivity;
import com.bwei.jd_project.mvp.classify.view.adapter.ChildrenProductClassifyNameAndProductContent;
import com.bwei.jd_project.mvp.classify.view.adapter.ProductClassifyBaseAdapter;
import com.bwei.jd_project.mvp.classify.view.iview.IClassifyView;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends BaseFragment<ClassifyPresneter> implements IClassifyView {

    private android.widget.ListView classifyListView;

    private com.stx.xhb.xbanner.XBanner banner;

    private android.support.v7.widget.RecyclerView classifyShopsRecyclerView;

    private int cid;

    @Override
    protected void initDatas() {


    }

    @Override
    protected void initViews() {

        classifyListView = view.findViewById(R.id.classifyListView);

        banner = view.findViewById(R.id.banner);

        classifyShopsRecyclerView = view.findViewById(R.id.classifyShopsRecyclerView);

        presenter = new ClassifyPresneter(this);

    }

    @Override
    protected int ByLayout() {
        return R.layout.classify_fragment_layout;
    }

    @Override
    public void getSuccess(CatagoryBean catagoryBean) {

        final List<CatagoryBean.DataBean> data = catagoryBean.getData();

        ProductClassifyBaseAdapter adapter = new ProductClassifyBaseAdapter(getActivity(), data);

        classifyListView.setAdapter(adapter);

        presenter.childrenClassifySelect(1);

        classifyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cid = data.get(position).getCid();

                presenter.childrenClassifySelect(cid);

            }
        });

    }

    @Override
    public void getError(Throwable throwable) {

    }

    @Override
    public void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean) {

        String code = productCatagoryBean.getCode();

        if ("0".equals(code)){

            final List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();

            ChildrenProductClassifyNameAndProductContent childrenProductClassifyNameAndProductContent = new ChildrenProductClassifyNameAndProductContent(getActivity(),data);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

            classifyShopsRecyclerView.setLayoutManager(linearLayoutManager);

            classifyShopsRecyclerView.setAdapter(childrenProductClassifyNameAndProductContent);

            childrenProductClassifyNameAndProductContent.setOnClickListener(new ChildrenProductClassifyNameAndProductContent.OnClickListener() {
                @Override
                public void onClickListener(View view, int position) {

                    List<ProductCatagoryBean.DataBean.ListBean> list = data.get(position).getList();

                    int pscid = list.get(position).getPscid();

                    Intent it = new Intent(getActivity(), ShowProductActivity.class);

                    it.putExtra("pscid",pscid);

                    startActivity(it);

                }
            });


        }else{

            Toast.makeText(getActivity(),"您的请求失败了",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getProductCatagoryError(Throwable throwable) {

        Toast.makeText(getActivity(),throwable.getMessage(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && presenter != null) {

            presenter.classifySelect(HttpConfig.CATAGORYBEAN_URL);

        }
    }
}
