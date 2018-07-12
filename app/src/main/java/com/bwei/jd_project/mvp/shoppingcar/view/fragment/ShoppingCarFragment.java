package com.bwei.jd_project.mvp.shoppingcar.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.base.BaseFragment;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.presenter.ShoppingCarPresenter;
import com.bwei.jd_project.mvp.shoppingcar.view.adapter.ShoppingCarShowExpandableListView;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShoppingCarView;

import java.util.List;

public class ShoppingCarFragment extends BaseFragment<ShoppingCarPresenter> implements IShoppingCarView ,View.OnClickListener{

    private int uid = 71;
    private CheckBox checkBoxAll;
    private TextView toalPrice;
    private Button toalNumber;
    private ExpandableListView shoppingCarExpandableListView;
    private ShoppingCarShowExpandableListView shoppingCarShowExpandableListView;

    @Override
    protected void initDatas() {


    }

    @Override
    protected void initViews() {

        shoppingCarExpandableListView = view.findViewById(R.id.shoppingCarExpandableListView);

        checkBoxAll = view.findViewById(R.id.CheckboxAll);

        toalPrice = view.findViewById(R.id.toalPrice);

        toalNumber = view.findViewById(R.id.toalNumber);

        presenter = new ShoppingCarPresenter(this);

        checkBoxAll.setOnClickListener(this);

    }

    @Override
    protected int ByLayout() {
        return R.layout.shoppingcar_fragment_layout;
    }

    @Override
    public void getSuccess(ShoppingCarBean shoppingCarBean) {

        String code = shoppingCarBean.getCode();

        if ("0".equals(code)) {

            Toast.makeText(getActivity(), "您的请求成功了", Toast.LENGTH_LONG).show();

            List<ShoppingCarBean.DataBean> data = shoppingCarBean.getData();

            shoppingCarShowExpandableListView = new ShoppingCarShowExpandableListView(getActivity(),data);

            shoppingCarExpandableListView.setAdapter(shoppingCarShowExpandableListView);

            shoppingCarExpandableListView.setGroupIndicator(null);

            for (int i = 0; i < data.size(); i++) {

                shoppingCarExpandableListView.expandGroup(i);

            }

            for (int i = 0; i < data.size(); i++) {

                List<ShoppingCarBean.DataBean.ListBean> list = data.get(i).getList();

                for (int j = 0; j < list.size(); j++) {

                    list.get(j).setSelected(0);

                }

            }

            toalPrice.setText("总计: 0元");

            shoppingCarShowExpandableListView.setOnCartListChangeListener(new ShoppingCarShowExpandableListView.onCartListChangeListener() {
                @Override
                public void onSellerCheckedChange(int groupPosition) {

                    boolean currentProductSelected = shoppingCarShowExpandableListView.isCurrentProductSelected(groupPosition);

                    shoppingCarShowExpandableListView.isCurrentAllSelected(groupPosition,!currentProductSelected);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    sumPriceAndNumber();

                }

                @Override
                public void onProductCheckedChange(int groupPosition, int childPosition) {

                    shoppingCarShowExpandableListView.ChangeCurrentProductStatus(groupPosition,childPosition);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    sumPriceAndNumber();

                }

                @Override
                public void onProductNumberChange(int groupPosition, int childPosition, int number) {

                    shoppingCarShowExpandableListView.ChangeCurrentProductNumber(groupPosition,childPosition,number);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    sumPriceAndNumber();
                }
            });

        } else {

            Toast.makeText(getActivity(), "您的请求失败了", Toast.LENGTH_LONG).show();

        }

    }

    public void sumPriceAndNumber(){

        boolean currentCheckBoxSelected = shoppingCarShowExpandableListView.isCurrentCheckBoxSelected();

        checkBoxAll.setSelected(currentCheckBoxSelected);

        int number = shoppingCarShowExpandableListView.sumAllNumber();

        toalNumber.setText("去结算("+number+")");

        double Price = shoppingCarShowExpandableListView.sumAllPrice();

        toalPrice.setText("总价: "+Price+"元");

    }

    @Override
    public void getError(Throwable throwable) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && presenter != null) {

            presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

        } else {

            presenter = new ShoppingCarPresenter(this);

            presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.CheckboxAll:

                boolean b = shoppingCarShowExpandableListView.AllShopsAndProductSelected();

                shoppingCarShowExpandableListView.isShopsAndProductSelected(!b);

                shoppingCarShowExpandableListView.notifyDataSetChanged();

                sumPriceAndNumber();

                break;
        }

    }
}
