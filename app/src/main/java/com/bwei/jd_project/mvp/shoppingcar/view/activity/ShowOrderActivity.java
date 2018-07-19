package com.bwei.jd_project.mvp.shoppingcar.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.presenter.ShowOrderPresenter;
import com.bwei.jd_project.mvp.shoppingcar.view.adapter.OrderRecyclerView;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShowOrderView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ShowOrderActivity extends AppCompatActivity implements IShowOrderView{

    private ShowOrderPresenter showOrderPresenter;

    private XRecyclerView showOrderRecyclerView;

    private int page =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);

        initViews();

        initDatas();

    }

    private void initDatas() {

        SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

        boolean isOn = sharedPreferences.getBoolean("isOn", false);

        if (isOn){

            int uid = sharedPreferences.getInt("uid", 1);

            showOrderPresenter.showOrderPresenter(uid,page);
        }

    }

    private void initViews() {

        showOrderRecyclerView = findViewById(R.id.showOrderRecyclerView);

        showOrderPresenter = new ShowOrderPresenter(this);

        showOrderRecyclerView.setPullRefreshEnabled(true);

        showOrderRecyclerView.setLoadingMoreEnabled(true);

        showOrderRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                page =1;

                initDatas();

                showOrderRecyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {

                page++;

                initDatas();

                showOrderRecyclerView.refreshComplete();

            }
        });


    }

    @Override
    public void getShowOrderSuccess(ShowOrderBean showOrderBean) {

        String code = showOrderBean.getCode();

        if ("0".equals(code)){

            List<ShowOrderBean.DataBean> data = showOrderBean.getData();

            OrderRecyclerView orderRecyclerView = new OrderRecyclerView(ShowOrderActivity.this,data);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowOrderActivity.this);

            showOrderRecyclerView.setLayoutManager(linearLayoutManager);

            showOrderRecyclerView.setAdapter(orderRecyclerView);

            Toast.makeText(ShowOrderActivity.this,"展示订单请求成功了",Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(ShowOrderActivity.this,"展示订单请求失败了",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getShowOrderError(Throwable throwable) {

        Toast.makeText(ShowOrderActivity.this,""+throwable.getMessage(),Toast.LENGTH_LONG).show();

    }
}
