package com.bwei.jd_project.mvp.shoppingcar.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.CommonAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateDefaultAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.presenter.CommonAddressPresenter;
import com.bwei.jd_project.mvp.shoppingcar.view.adapter.CommonAddressRecyclerView;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.ICommonAddressView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonAddressActivity extends AppCompatActivity implements ICommonAddressView, View.OnClickListener {

    private CommonAddressPresenter commonAddressPresenter;

    private android.widget.TextView addCommonAddress;

    private android.support.v7.widget.RecyclerView commonAddressRecyclerView;
    private int uid;
    private List<CommonAddressBean.DataBean> data;
    private CommonAddressRecyclerView commonAddressRecyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_address);

        initViews();

        initDatas();
    }

    private void initDatas() {

        SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

        uid = sharedPreferences.getInt("uid", 1);

        commonAddressPresenter.commonAddressPresenter(uid);

    }

    private void initViews() {

        commonAddressRecyclerView = (RecyclerView) findViewById(R.id.commonAddressRecyclerView);

        addCommonAddress = (TextView) findViewById(R.id.addCommonAddress);

        addCommonAddress.setOnClickListener(this);

        commonAddressPresenter = new CommonAddressPresenter(this);

    }

    @Override
    public void getCommonAddressSuccess(CommonAddressBean commonAddressBean) {

        String code = commonAddressBean.getCode();

        if ("0".equals(code)) {

            data = commonAddressBean.getData();

            commonAddressRecyclerView1 = new CommonAddressRecyclerView(CommonAddressActivity.this, data);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommonAddressActivity.this);

            commonAddressRecyclerView.setLayoutManager(linearLayoutManager);

            commonAddressRecyclerView.setAdapter(commonAddressRecyclerView1);

            commonAddressRecyclerView1.setOnClickListener(new CommonAddressRecyclerView.OnClickListener() {
                @Override
                public void OnClick(View view, final int position) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(CommonAddressActivity.this);

                    alert.setTitle("提示框");
                    alert.setMessage("您确认要修改此条为默认地址吗?");
                    alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

                            int uid = sharedPreferences.getInt("uid", 1);

                            int addrid = data.get(position).getAddrid();

                            Map<String,String> map = new HashMap<>();

                            map.put("uid",uid+"");
                            map.put("addrid",""+addrid);
                            map.put("status",1+"");

                            commonAddressPresenter.updateDefaultAddress(map);

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

        }

    }

    @Override
    public void getCommonAddressError(Throwable throwable) {


    }

    @Override
    public void getUpdateDefaultAddressSuccess(UpdateDefaultAddressBean updateDefaultAddressBean) {

        String code = updateDefaultAddressBean.getCode();

        if ("0".equals(code)){

            Intent it = getIntent();

            setResult(10,it);

            finish();

        }

    }

    @Override
    public void getUpdateDefaultAddressError(Throwable throwable) {

        Toast.makeText(CommonAddressActivity.this,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.addCommonAddress:

                Intent it = new Intent(CommonAddressActivity.this,AddCommonAddressActivity.class);

                startActivityForResult(it,20);

                //Toast.makeText(CommonAddressActivity.this,"此功能待开发...",Toast.LENGTH_SHORT).show();

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 20 && resultCode == 30){

            commonAddressPresenter.commonAddressPresenter(uid);

            commonAddressRecyclerView1.notifyDataSetChanged();

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        commonAddressPresenter.onDestory();

    }
}
