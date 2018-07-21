package com.bwei.jd_project.mvp.shoppingcar.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddCommonAddressBean;
import com.bwei.jd_project.mvp.shoppingcar.presenter.AddCommonAddressPresenter;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IAddCommonAddressView;

import java.util.HashMap;
import java.util.Map;

public class AddCommonAddressActivity extends AppCompatActivity implements IAddCommonAddressView,View.OnClickListener{

    private android.widget.EditText addCommonAddressName;
    private android.widget.EditText addCommonAddressPhone;
    private android.widget.EditText addCommonAddress;
    private android.widget.Button verify;
    private AddCommonAddressPresenter addCommonAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_common_address);

        initViews();

        initDatas();

    }

    private void initDatas() {


    }

    private void initViews() {

        verify = (Button) findViewById(R.id.add);

        verify.setOnClickListener(this);

        addCommonAddress = (EditText) findViewById(R.id.addCommonAddress);

        addCommonAddressPhone = (EditText) findViewById(R.id.addCommonAddressPhone);

        addCommonAddressName = (EditText) findViewById(R.id.addCommonAddressName);

        addCommonAddressPresenter = new AddCommonAddressPresenter(this);

    }

    @Override
    public void getAddCommonAddressSuccess(AddCommonAddressBean addCommonAddressBean) {

        String code = addCommonAddressBean.getCode();

        if ("0".equals(code)){

            Intent it = getIntent();

            setResult(30,it);

            finish();

        }

    }

    @Override
    public void getAddCommonAddressError(Throwable throwable) {


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add:

                SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

                int uid = sharedPreferences.getInt("uid", 1);

                String address = addCommonAddress.getText().toString();

                String name = addCommonAddressName.getText().toString();

                String cellPhoneNumber = addCommonAddressPhone.getText().toString();

                //创建一个Map集合 存放需要存放的数据
                Map<String,String> map = new HashMap<>();

                map.put("uid",uid+"");
                map.put("addr",address);
                map.put("mobile",cellPhoneNumber);
                map.put("name",name);

                addCommonAddressPresenter.addCommonAddress(map);

                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        addCommonAddressPresenter.onDestory();
    }
}
