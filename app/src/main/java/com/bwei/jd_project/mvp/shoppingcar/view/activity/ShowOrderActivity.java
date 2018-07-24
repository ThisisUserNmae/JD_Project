package com.bwei.jd_project.mvp.shoppingcar.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.DefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.presenter.ShowOrderPresenter;
import com.bwei.jd_project.mvp.shoppingcar.view.adapter.OrderRecyclerView;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShowOrderView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowOrderActivity extends AppCompatActivity implements IShowOrderView ,View.OnClickListener {

    private ShowOrderPresenter showOrderPresenter;

    private XRecyclerView showOrderRecyclerView;

    private TextView defaultAddress;

    private LinearLayout showOrderLinearLayout;

    private String[] names = {"待支付", "已支付", "已取消"};

    private int page = 1;
    private int uid;
    private OrderRecyclerView orderRecyclerView;
    private TextView waitPay;
    private TextView yetPay;
    private TextView cancelPay;

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

        if (isOn) {

            uid = sharedPreferences.getInt("uid", 1);

            showOrderPresenter.showOrderPresenter(uid, page,0);
            waitPay.setTextColor(Color.RED);

            showOrderPresenter.defaultAddressPresenter(uid);

        }else{

            Toast.makeText(ShowOrderActivity.this,"亲,你要先去登陆哦!",Toast.LENGTH_SHORT).show();

        }

    }

    private void initViews() {

        showOrderRecyclerView = findViewById(R.id.showOrderRecyclerView);

        showOrderLinearLayout = findViewById(R.id.showOrderLinearLayout);

        defaultAddress = findViewById(R.id.orderAddr);

        waitPay = findViewById(R.id.waitPay);
        yetPay = findViewById(R.id.yetPay);
        cancelPay = findViewById(R.id.cancelPay);

        waitPay.setOnClickListener(this);
        yetPay.setOnClickListener(this);
        cancelPay.setOnClickListener(this);


        showOrderLinearLayout.setOnClickListener(this);

        showOrderPresenter = new ShowOrderPresenter(this);

        showOrderRecyclerView.setPullRefreshEnabled(true);

        showOrderRecyclerView.setLoadingMoreEnabled(true);

        showOrderRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                page = 1;

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

        if ("0".equals(code)) {

            final List<ShowOrderBean.DataBean> data = showOrderBean.getData();

            orderRecyclerView = new OrderRecyclerView(ShowOrderActivity.this, data);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowOrderActivity.this);

            showOrderRecyclerView.setLayoutManager(linearLayoutManager);

            showOrderRecyclerView.setOnClickListener(this);

            showOrderRecyclerView.setAdapter(orderRecyclerView);

            orderRecyclerView.setOnClickListener(new OrderRecyclerView.OnClickListener() {
                @Override
                public void OnClick(View view, final int position) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(ShowOrderActivity.this);
                    alert.setTitle("设置订单状态");
                    alert.setItems(names, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    //待支付

                                    Map<String, String> map = new HashMap<>();

                                    int orderid = data.get(position).getOrderid();

                                    map.put("uid", "" + uid);

                                    map.put("status", "" + 0);

                                    map.put("orderId", "" + orderid);

                                    showOrderPresenter.updateOrderPresenter(map);

                                    break;

                                case 1:

                                    //已支付
                                    Map<String, String> map1 = new HashMap<>();

                                    int orderid1 = data.get(position).getOrderid();

                                    map1.put("uid", "" + uid);

                                    map1.put("status", "" + 1);

                                    map1.put("orderId", "" + orderid1);

                                    showOrderPresenter.updateOrderPresenter(map1);

                                    break;

                                case 2:

                                    //已取消
                                    Map<String, String> map2 = new HashMap<>();

                                    int orderid2 = data.get(position).getOrderid();

                                    map2.put("uid", "" + uid);

                                    map2.put("status", "" + 2);

                                    map2.put("orderId", "" + orderid2);

                                    showOrderPresenter.updateOrderPresenter(map2);

                                    break;

                            }

                        }
                    });

                    alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.create();
                    alert.show();

                }
            });

            //Toast.makeText(ShowOrderActivity.this,"展示订单请求成功了",Toast.LENGTH_LONG).show();


        } else {

            Toast.makeText(ShowOrderActivity.this, "展示订单请求失败了", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getShowOrderError(Throwable throwable) {

        Toast.makeText(ShowOrderActivity.this, "服务器繁忙...", Toast.LENGTH_LONG).show();

    }

    @Override
    public void getUpdateOrderSuccess(UpdateOrderBean updateOrderBean) {

        String code = updateOrderBean.getCode();

        if ("0".equals(code)){

            showOrderPresenter.showOrderPresenter(uid, page,0);


            orderRecyclerView.notifyDataSetChanged();

        }

    }

    @Override
    public void getUpdateOrderError(Throwable throwable) {

        Toast.makeText(ShowOrderActivity.this, "服务器繁忙...", Toast.LENGTH_LONG).show();

    }

    @Override
    public void getDefaultAddressSuccess(DefaultAddressBean defaultAddressBean) {

        String code = defaultAddressBean.getCode();

        if ("0".equals(code)){

            DefaultAddressBean.DataBean data = defaultAddressBean.getData();

            String name = data.getName();

            long mobile = data.getMobile();

            String addr = data.getAddr();

            defaultAddress.setText(name+"  "+mobile+"  "+addr+"  (点击更换默认地址)");

        }

    }

    @Override
    public void getDefaultAddressError(Throwable throwable) {

        Toast.makeText(ShowOrderActivity.this, "服务器繁忙...", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.showOrderLinearLayout:

                AlertDialog.Builder alert = new AlertDialog.Builder(ShowOrderActivity.this);

                alert.setTitle("更换确认框");
                alert.setMessage("您确认要更改默认地址吗?");
                alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent it = new Intent(ShowOrderActivity.this,CommonAddressActivity.class);

                        startActivityForResult(it,10);

                    }
                });

                alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.create();
                alert.show();

                break;

            case R.id.waitPay:

                showOrderPresenter.showOrderPresenter(uid, page,0);
                waitPay.setTextColor(Color.RED);
                yetPay.setTextColor(Color.BLACK);
                cancelPay.setTextColor(Color.BLACK);


                break;

            case R.id.yetPay:

                showOrderPresenter.showOrderPresenter(uid, page,1);
                waitPay.setTextColor(Color.BLACK);
                cancelPay.setTextColor(Color.BLACK);
                yetPay.setTextColor(Color.RED);

                break;

            case R.id.cancelPay:

                showOrderPresenter.showOrderPresenter(uid, page,2);
                yetPay.setTextColor(Color.BLACK);
                waitPay.setTextColor(Color.BLACK);
                cancelPay.setTextColor(Color.RED);

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == 10){

            showOrderPresenter.defaultAddressPresenter(uid);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        showOrderPresenter.onDestory();
    }
}
