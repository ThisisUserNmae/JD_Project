package com.bwei.jd_project.mvp.classify.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.classify.model.bean.ShowProductBean;
import com.bwei.jd_project.mvp.classify.presenter.ClassifyShowProductPresenter;
import com.bwei.jd_project.mvp.classify.view.adapter.ShowProductRecyclerView;
import com.bwei.jd_project.mvp.classify.view.iview.IClassifyShowProductView;
import com.bwei.jd_project.mvp.home.view.activity.ProductDetailsActivity;

import java.util.List;

public class ShowProductActivity extends AppCompatActivity implements View.OnClickListener ,IClassifyShowProductView{

    private android.widget.TextView btn1;

    private android.widget.TextView btn2;

    private android.widget.TextView btn3;

    private android.support.v7.widget.RecyclerView ProductRecyclerView;

    private ClassifyShowProductPresenter classifyShowProductPresenter;

    private String DEFAULT = "0";

    private String SALES = "1";

    private String PRICE = "2";

    private int pscid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        Intent it = getIntent();

        pscid = it.getIntExtra("pscid", 1);

        initViews();

        initDatas();

    }

    private void initDatas() {

        classifyShowProductPresenter.showProduct(pscid,"0");

    }

    private void initViews() {

        classifyShowProductPresenter = new ClassifyShowProductPresenter(this);

        btn3 = (TextView) findViewById(R.id.btn3);

        btn2 = (TextView) findViewById(R.id.btn2);

        btn1 = (TextView) findViewById(R.id.btn1);

        ProductRecyclerView = (RecyclerView) findViewById(R.id.ProductRecyclerView);

        btn1.setOnClickListener(this);

        btn2.setOnClickListener(this);

        btn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn1:

                classifyShowProductPresenter.showProduct(pscid,"0");

                break;

            case R.id.btn2:

                classifyShowProductPresenter.showProduct(pscid,"1");

                break;

            case R.id.btn3:

                classifyShowProductPresenter.showProduct(pscid,"2");

                break;

        }

    }

    @Override
    public void getSuccess(ShowProductBean showProductBean) {

        String code = showProductBean.getCode();

        if ("0".equals(code)){

            final List<ShowProductBean.DataBean> data = showProductBean.getData();

            ShowProductRecyclerView showProductRecyclerView = new ShowProductRecyclerView(ShowProductActivity.this,data);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowProductActivity.this);

            ProductRecyclerView.setLayoutManager(linearLayoutManager);

            ProductRecyclerView.setAdapter(showProductRecyclerView);

            showProductRecyclerView.setOnClickListener(new ShowProductRecyclerView.OnClickListener() {
                @Override
                public void OnClickListener(View view, int position) {

                    int pid = data.get(position).getPid();

                    Intent it = new Intent(ShowProductActivity.this, ProductDetailsActivity.class);

                    it.putExtra("pid",pid);

                    startActivity(it);

                }
            });

        }else{

            Toast.makeText(ShowProductActivity.this,"您的展示商品的请求失败了",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getError(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        classifyShowProductPresenter.oonDestory();

    }
}
