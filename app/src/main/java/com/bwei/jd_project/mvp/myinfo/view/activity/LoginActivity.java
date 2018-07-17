package com.bwei.jd_project.mvp.myinfo.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.myinfo.model.bean.LoginBean;
import com.bwei.jd_project.mvp.myinfo.presenter.MyInfoPresenter;
import com.bwei.jd_project.mvp.myinfo.view.iview.IMyInfoView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IMyInfoView {


    private MyInfoPresenter myInfoPresenter;

    private android.widget.EditText eduserMobile;

    private android.widget.EditText edpassword;

    private android.widget.Button login;

    private android.widget.Button register;

    private android.widget.ImageView weixinlogin;

    private android.widget.ImageView qqlogin;

    private android.widget.ImageView xinlianglogin;

    private static final String TAG = "LoginActivity---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        initDatas();
    }

    private void initDatas() {


    }

    private void initViews() {

        xinlianglogin = (ImageView) findViewById(R.id.xinliang_login);
        qqlogin = (ImageView) findViewById(R.id.qq_login);
        weixinlogin = (ImageView) findViewById(R.id.weixin_login);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        edpassword = (EditText) findViewById(R.id.ed_password);
        eduserMobile = (EditText) findViewById(R.id.ed_userMobile);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        xinlianglogin.setOnClickListener(this);
        qqlogin.setOnClickListener(this);
        register.setOnClickListener(this);

        myInfoPresenter = new MyInfoPresenter(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.login:


                // Toast.makeText(LoginActivity.this,"你要登陆了",Toast.LENGTH_SHORT).show();

                String mobile = eduserMobile.getText().toString();

                String password = edpassword.getText().toString();

                if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)){

                    myInfoPresenter.loginPresenter(mobile,password);

                }

                break;

            case R.id.register:

                break;

        }

    }

    @Override
    public void getSuccess(LoginBean loginBean) {

        String code = loginBean.getCode();

        if ("0".equals(code)){

            //Toast.makeText(LoginActivity.this,"你的请求成功了",Toast.LENGTH_SHORT).show();

            LoginBean.DataBean data = loginBean.getData();

            int uid = data.getUid();

            String icon = data.getIcon();

            Log.d(TAG, "getSuccess: "+icon.toString());

            String username = data.getUsername();

            SharedPreferences sharedPreferences = getSharedPreferences("uid",MODE_PRIVATE);

            SharedPreferences.Editor edit =  sharedPreferences.edit();

            edit.putInt("uid",uid);

            edit.putString("username",username);

            edit.putBoolean("isOn",true);

            edit.putString("icon",icon);

            edit.commit();

            finish();

        }

    }

    @Override
    public void getError(Throwable throwable) {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        myInfoPresenter.onDestory();

    }
}
