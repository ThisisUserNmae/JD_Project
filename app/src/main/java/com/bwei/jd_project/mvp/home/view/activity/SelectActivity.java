package com.bwei.jd_project.mvp.home.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.database.DaoManager;
import com.bwei.jd_project.database.DaoSession;
import com.bwei.jd_project.database.ProductDao;
import com.bwei.jd_project.mvp.classify.view.activity.ShowProductActivity;
import com.bwei.jd_project.mvp.home.model.bean.Product;
import com.bwei.jd_project.mvp.home.model.bean.SearchBean;
import com.bwei.jd_project.mvp.home.presenter.SearchPresenter;
import com.bwei.jd_project.mvp.home.view.adapter.FlowLayout;
import com.bwei.jd_project.mvp.home.view.adapter.SelectKeyWrodRecyclerView;
import com.bwei.jd_project.mvp.home.view.iview.ISearchView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener,ISearchView{

    private android.widget.EditText edselect;

    private android.widget.Button select;

    private com.bwei.jd_project.mvp.home.view.adapter.FlowLayout flowLayout;

    private android.support.v7.widget.RecyclerView keyWord;

    private SearchPresenter searchPresenter;

    private String[] names = {"笔记本电脑", "手机", "月饼", "坚果炒货", "连衣裙", "男装"};

    private DaoSession daoSession;

    private ProductDao productDao;
    private TextView deleteAll;
    private SelectKeyWrodRecyclerView selectKeyWrodRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);


        initViews();

        initDatas();
    }

    private void initDatas() {


    }

    private void initViews() {


        deleteAll = findViewById(R.id.deleteAll);

        keyWord = (RecyclerView) findViewById(R.id.keyWord);
        flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        select = (Button) findViewById(R.id.select);
        edselect = (EditText) findViewById(R.id.ed_select);

        daoSession = DaoManager.getIntent(SelectActivity.this).getDaoSession();

        productDao = daoSession.getProductDao();

        searchPresenter = new SearchPresenter(this);

        select.setOnClickListener(this);

        deleteAll.setOnClickListener(this);

        for (int i = 0; i < names.length; i++) {

            final TextView tv = new TextView(SelectActivity.this);

            tv.setPadding(10,10,10,10);

            tv.setText(names[i]);

            flowLayout.addView(tv);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String s = tv.getText().toString();

                    searchPresenter.searchPresenter(s);

                }
            });

        }

        final List<Product> list = productDao.queryBuilder()
                .build().list();

        SelectKeyWrodRecyclerView selectKeyWrodRecyclerView1 = new SelectKeyWrodRecyclerView(SelectActivity.this,list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectActivity.this);

        keyWord.setLayoutManager(linearLayoutManager);

        keyWord.setAdapter(selectKeyWrodRecyclerView1);

        selectKeyWrodRecyclerView1.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.select:

                 String  s = edselect.getText().toString();

                if (!TextUtils.isEmpty(s)){

                    //请求搜索关键字的接口
                    searchPresenter.searchPresenter(s);

                }

                break;

            case R.id.deleteAll:

                productDao.deleteAll();

                final List<Product> list = productDao.queryBuilder()
                        .build().list();

                SelectKeyWrodRecyclerView selectKeyWrodRecyclerView1 = new SelectKeyWrodRecyclerView(SelectActivity.this,list);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectActivity.this);

                keyWord.setLayoutManager(linearLayoutManager);

                keyWord.setAdapter(selectKeyWrodRecyclerView1);

                selectKeyWrodRecyclerView1.notifyDataSetChanged();

                break;
        }

    }

    @Override
    public void getSceess(SearchBean searchBean) {

        String code = searchBean.getCode();

        if ("0".equals(code)){

            Toast.makeText(SelectActivity.this,"您的请求成功了",Toast.LENGTH_SHORT).show();

            List<SearchBean.DataBean> data = searchBean.getData();

            int pscid = data.get(0).getPscid();

            String s = edselect.getText().toString();

            productDao.insert(new Product(null,s,pscid));

            final List<Product> list = productDao.queryBuilder()
                    .build().list();

            selectKeyWrodRecyclerView = new SelectKeyWrodRecyclerView(SelectActivity.this,list);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectActivity.this);

            keyWord.setLayoutManager(linearLayoutManager);

            keyWord.setAdapter(selectKeyWrodRecyclerView);

            selectKeyWrodRecyclerView.notifyDataSetChanged();

            selectKeyWrodRecyclerView.setOnClickListener(new SelectKeyWrodRecyclerView.OnClickListener() {
                @Override
                public void OnClick(View view, int position) {

                    int pscid1 = list.get(position).getPscid();

                    Intent it = new Intent(SelectActivity.this, ShowProductActivity.class);

                    it.putExtra("pscid",pscid1);

                    startActivity(it);

                }
            });

            Intent it = new Intent(SelectActivity.this, ShowProductActivity.class);

            it.putExtra("pscid",pscid);

            startActivity(it);
        }

    }

    @Override
    public void getError(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.onDestory();
    }
}
