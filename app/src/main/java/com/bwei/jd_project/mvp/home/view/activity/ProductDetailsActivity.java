package com.bwei.jd_project.mvp.home.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.model.bean.AddShoppingCarBean;
import com.bwei.jd_project.mvp.home.model.bean.ProductDetailsCreateOrderBean;
import com.bwei.jd_project.mvp.home.model.bean.ShowDetailsBean;
import com.bwei.jd_project.mvp.home.presenter.HomeDetailsPresenter;
import com.bwei.jd_project.mvp.home.view.adapter.ShowProductDetailsRecyclerView;
import com.bwei.jd_project.mvp.home.view.iview.IHomeShowDetailsView;
import com.bwei.jd_project.mvp.myinfo.view.activity.LoginActivity;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.view.activity.ShowOrderActivity;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements IHomeShowDetailsView {

    private static final String TAG = "ProductDetailsActivity";

    private com.stx.xhb.xbanner.XBanner banner;

    private int pid;

    private HomeDetailsPresenter homeDetailsPresenter;

    private android.support.v7.widget.RecyclerView showDetailsRecyclerView;

    List<String> titles = new ArrayList<>();

    private ShowDetailsBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initViews();

        initDatas();

    }

    private void initDatas() {

        Intent it = getIntent();

        pid = it.getIntExtra("pid", 1);

        //Log.d(TAG, "initViews: " + pid);

        homeDetailsPresenter.showDetails(pid);

    }

    private void initViews() {

        banner = (XBanner) findViewById(R.id.banner);

        showDetailsRecyclerView = (RecyclerView) findViewById(R.id.showDetailsRecyclerView);

        homeDetailsPresenter = new HomeDetailsPresenter(this);

    }

    @Override
    public void getSuccess(final ShowDetailsBean showDetailsBean) {

        String code = showDetailsBean.getCode();

        if ("0".equals(code)) {

            data = showDetailsBean.getData();

            final ShowDetailsBean.SellerBean seller = showDetailsBean.getSeller();

            ShowProductDetailsRecyclerView showProductDetailsRecyclerView = new ShowProductDetailsRecyclerView(ProductDetailsActivity.this, data, seller);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailsActivity.this);

            showDetailsRecyclerView.setLayoutManager(linearLayoutManager);

            showDetailsRecyclerView.setAdapter(showProductDetailsRecyclerView);

            showProductDetailsRecyclerView.setCreateOrderClickListener(new ShowProductDetailsRecyclerView.CreateOrderClickListener() {
                @Override
                public void onClick(View view, int position,double price) {

                    //Toast.makeText(ProductDetailsActivity.this,"您点击了购买按钮",Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

                    boolean isOn = sharedPreferences.getBoolean("isOn", false);

                    if (isOn){

                        int uid = sharedPreferences.getInt("uid", 1);

                        Log.d(TAG, "onClick: uid"+uid+"   price"+price);

                        homeDetailsPresenter.addOrder(uid,price);

                    }else{

                        Toast.makeText(ProductDetailsActivity.this,"您还没有登录",Toast.LENGTH_SHORT).show();

                    }

                }
            });

            showProductDetailsRecyclerView.setOnAddShoppingCarClickLiener(new ShowProductDetailsRecyclerView.OnAddShoppingCarClickLiener() {
                @Override
                public void OnClickListener(View view, int position) {

                    SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

                    int uid = sharedPreferences.getInt("uid", 1);

                    if (sharedPreferences.getBoolean("isOn", false)) {

                        int pid = data.getPid();

                        homeDetailsPresenter.addShoppingCarPresenter(uid, pid);

                    } else {

                        AlertDialog.Builder alert = new AlertDialog.Builder(ProductDetailsActivity.this);
                        alert.setTitle("登陆提示框");

                        alert.setMessage("您确认要去登陆吗?");
                        alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent it = new Intent(ProductDetailsActivity.this, LoginActivity.class);

                                startActivity(it);

                            }
                        });

                        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alert.show();
                        alert.create();

                    }

                }
            });

            String[] split1 = showDetailsBean.getData().getImages().split("\\|");

            final List<String> strings = Arrays.asList(split1);

            for (int i = 0; i < strings.size(); i++) {

                titles.add("dddddd" + i);

            }

            // 为XBanner绑定数据
            banner.setData(strings, titles);

            // XBanner适配数据
            banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {

                    Glide.with(ProductDetailsActivity.this).load(strings.get(position)).into((ImageView) view);

                    //Log.d(TAG, "loadBanner: 您进来了");

                }
            });

            banner.setPageTransformer(Transformer.Cube);

            // 设置XBanner页面切换的时间，即动画时长
            banner.setPageChangeDuration(6000);

            //为商品赋值

        } else {

            //Toast.makeText(ProductDetailsActivity.this, "您的请求失败了", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getError(Throwable throwable) {

        //Toast.makeText(ProductDetailsActivity.this, "" + throwable.getMessage(), Toast.LENGTH_LONG).show();

        //Log.d(TAG, "getError: " + throwable.getMessage());

    }

    @Override
    public void getAddShoppingCarSuccess(AddShoppingCarBean addShoppingCarBean) {

        String code = addShoppingCarBean.getCode();
        if ("0".equals(code)) {

            Toast.makeText(ProductDetailsActivity.this, data.getPid() + "号商品已经添加到购物车", Toast.LENGTH_SHORT).show();

        } else {

            //Toast.makeText(ProductDetailsActivity.this, "您的请求失败了", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void getAddShoppingCarError(Throwable throwable) {

        //Toast.makeText(ProductDetailsActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getAddOrderSuccess(ProductDetailsCreateOrderBean productDetailsCreateOrderBean) {


        String code = productDetailsCreateOrderBean.getCode();

        if ("0".equals(code)){

            Intent it = new Intent(ProductDetailsActivity.this, ShowOrderActivity.class);

            startActivity(it);

        }else{

            String msg = productDetailsCreateOrderBean.getMsg();

            Log.d(TAG, "getAddOrderSuccess: msg"+msg);

            Toast.makeText(ProductDetailsActivity.this,"你创建订单出错啦",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void getAddOrderError(Throwable throwable) {

        Toast.makeText(ProductDetailsActivity.this,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        homeDetailsPresenter.onDestory();

    }

    public void CallBck(View view) {

        finish();

    }
}
