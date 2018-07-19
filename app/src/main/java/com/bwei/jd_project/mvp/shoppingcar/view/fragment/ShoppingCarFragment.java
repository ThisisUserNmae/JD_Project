package com.bwei.jd_project.mvp.shoppingcar.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.base.BaseFragment;
import com.bwei.jd_project.http.HttpConfig;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.AddOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.DeleteCartBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.UpdateShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.presenter.ShoppingCarPresenter;
import com.bwei.jd_project.mvp.shoppingcar.view.activity.ShowOrderActivity;
import com.bwei.jd_project.mvp.shoppingcar.view.adapter.ShoppingCarShowExpandableListView;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShoppingCarView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCarFragment extends BaseFragment<ShoppingCarPresenter> implements IShoppingCarView, View.OnClickListener {

    private CheckBox checkBoxAll;

    private TextView toalPrice;

    private Button toalNumber;

    private ExpandableListView shoppingCarExpandableListView;

    private ShoppingCarShowExpandableListView shoppingCarShowExpandableListView;

    private List<ShoppingCarBean.DataBean> data;
    private int uid;
    private SharedPreferences sharedPreferences;
    private double toalPrice1;

    @Override
    protected void initDatas() {

        sharedPreferences = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

        uid = sharedPreferences.getInt("uid", 1);

        boolean isOn = sharedPreferences.getBoolean("isOn", false);

        if (isOn == true) {

            //Toast.makeText(getActivity(), "你进入了为true的方法", Toast.LENGTH_LONG).show();

            presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

        } else {

            if (shoppingCarExpandableListView != null) {

               // data.clear();

                //shoppingCarShowExpandableListView.notifyDataSetChanged();

            }

        }

    }

    @Override
    protected void initViews() {

        shoppingCarExpandableListView = view.findViewById(R.id.shoppingCarExpandableListView);

        checkBoxAll = view.findViewById(R.id.CheckboxAll);

        toalPrice = view.findViewById(R.id.toalPrice);

        toalNumber = view.findViewById(R.id.toalNumber);

        toalNumber.setOnClickListener(this);

        presenter = new ShoppingCarPresenter(this);

        checkBoxAll.setOnClickListener(this);

        if (presenter == null) {

            presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

        }


    }

    @Override
    protected int ByLayout() {
        return R.layout.shoppingcar_fragment_layout;
    }

    @Override
    public void getSuccess(ShoppingCarBean shoppingCarBean) {

        String code = shoppingCarBean.getCode();

        if ("0".equals(code)) {

            //Toast.makeText(getActivity(), "您的请求成功了", Toast.LENGTH_LONG).show();

            data = shoppingCarBean.getData();

            shoppingCarShowExpandableListView = new ShoppingCarShowExpandableListView(getActivity(), data);

            shoppingCarExpandableListView.setAdapter(shoppingCarShowExpandableListView);

            sumPriceAndNumber();

            shoppingCarExpandableListView.setGroupIndicator(null);

            for (int i = 0; i < data.size(); i++) {

                shoppingCarExpandableListView.expandGroup(i);

            }

            shoppingCarShowExpandableListView.setOnCartListChangeListener(new ShoppingCarShowExpandableListView.onCartListChangeListener() {
                @Override
                public void onSellerCheckedChange(int groupPosition) {

                    boolean currentProductSelected = shoppingCarShowExpandableListView.isCurrentProductSelected(groupPosition);

                    shoppingCarShowExpandableListView.isCurrentAllSelected(groupPosition, !currentProductSelected);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    refreshShopsCheckedStatusOnLine(!currentProductSelected, groupPosition);

                    sumPriceAndNumber();

                }

                @Override
                public void onProductCheckedChange(int groupPosition, int childPosition) {

                    shoppingCarShowExpandableListView.ChangeCurrentProductStatus(groupPosition, childPosition);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    refreshProductCheckedStatusOnLine(groupPosition, childPosition);

                    sumPriceAndNumber();

                }

                @Override
                public void onProductNumberChange(int groupPosition, int childPosition, int number) {

                    shoppingCarShowExpandableListView.ChangeCurrentProductNumber(groupPosition, childPosition, number);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    refreshNumberStatusOnLine(groupPosition, childPosition, number);

                    sumPriceAndNumber();
                }

            });

            //处理删除的逻辑

            shoppingCarShowExpandableListView.setOnDeleteClickListener(new ShoppingCarShowExpandableListView.OnDeleteClickListener() {
                @Override
                public void OnClickListener(int groupPoistion, int childPoistion) {

                    final int pid = data.get(groupPoistion).getList().get(childPoistion).getPid();

                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("删除商品对话框");

                    alert.setMessage("您确认要删除" + pid + "号的商品吗?");

                    alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            presenter.deleteCart(uid, pid);

                        }
                    });

                    alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Toast.makeText(getActivity(), "您不能再点减了", Toast.LENGTH_SHORT).show();

                        }
                    });

                    alert.create();
                    alert.show();

                }
            });

        } else {

            //Toast.makeText(getActivity(), "您的请求失败了", Toast.LENGTH_LONG).show();

        }

    }

    //查询是否选中 累加价格 数量
    public void sumPriceAndNumber() {

        boolean currentCheckBoxSelected = shoppingCarShowExpandableListView.isCurrentCheckBoxSelected();

        checkBoxAll.setChecked(currentCheckBoxSelected);

        int number = shoppingCarShowExpandableListView.sumAllNumber();

        toalNumber.setText("去结算(" + number + ")");

        double Price = shoppingCarShowExpandableListView.sumAllPrice();

        toalPrice1 = Price;

        toalPrice.setText("总价: " + Price + "元");

    }

    @Override
    public void getError(Throwable throwable) {

        if (shoppingCarShowExpandableListView != null) {

            data.clear();

            shoppingCarShowExpandableListView.notifyDataSetChanged();

        }

    }

    //处理删除成功数据的回调
    @Override
    public void getDeleteCartSuccess(DeleteCartBean deleteCartBean) {

        String code = deleteCartBean.getCode();

        if ("0".equals(code)) {

            //Toast.makeText(getActivity(),"删除成功了",Toast.LENGTH_SHORT).show();
            //查询购物车

            presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

            shoppingCarShowExpandableListView.notifyDataSetChanged();

        } else {

            //Toast.makeText(getActivity(), "删除失败了", Toast.LENGTH_SHORT).show();


        }

    }

    @Override
    public void getDeleteCarError(Throwable throwable) {

        //Toast.makeText(getActivity(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getUpdateCartSuccess(UpdateShoppingCarBean updateShoppingCarBean) {

        String code = updateShoppingCarBean.getCode();

        if ("0".equals(code)) {

            //Toast.makeText(getActivity(), "修改购物车成功了", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void getUpdateCartError(Throwable throwable) {

        //Toast.makeText(getActivity(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getAddOrderSuccess(AddOrderBean addOrderBean) {

        String code = addOrderBean.getCode();

        if ("0".equals(code)){

            //Toast.makeText(getActivity(),"创建订单成功",Toast.LENGTH_LONG).show();

            Intent it = new Intent(getActivity(), ShowOrderActivity.class);

            startActivity(it);

        }else{

            Toast.makeText(getActivity(),"创建订单失败",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getAddOrderError(Throwable throwable) {

        Toast.makeText(getActivity(),"创建订单失败",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //处理全选
            case R.id.CheckboxAll:

                boolean b = shoppingCarShowExpandableListView.AllShopsAndProductSelected();

                shoppingCarShowExpandableListView.isShopsAndProductSelected(!b);

                shoppingCarShowExpandableListView.notifyDataSetChanged();

                sumPriceAndNumber();

                break;

            case R.id.toalNumber:

                if (toalPrice1<1){

                    Toast.makeText(getActivity(),"MMP,请您选中商品再结算",Toast.LENGTH_SHORT).show();

                }else{

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

                    boolean isOn = sharedPreferences.getBoolean("isOn", false);

                    if (isOn){

                        //请求接口

                        presenter.addOrderPresenter(uid,toalPrice1);



                    }else{

                        Toast.makeText(getActivity(),"请您先去登陆",Toast.LENGTH_SHORT).show();

                    }

                }

                break;
        }

    }

    public void refreshShopsCheckedStatusOnLine(boolean b, int groupPosition) {

        int isCheck = 0;

        if (b == false) {

            isCheck = 0;

        } else {

            isCheck = 1;

        }

        SharedPreferences uid = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

        int uid1 = uid.getInt("uid", 1);

        List<ShoppingCarBean.DataBean.ListBean> list = data.get(groupPosition).getList();

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {

            int sellerid = list.get(i).getSellerid();

            int pid = list.get(i).getPid();

            int num = list.get(i).getNum();

            map.put("sellerid", sellerid + "");

            map.put("pid", pid + "");

            map.put("num", num + "");

            map.put("selected", isCheck + "");

            map.put("uid", uid1 + "");

            presenter.updateCaartPresenter(map);

        }

    }

    public void refreshProductCheckedStatusOnLine(int groupPosition, int childPosition) {

        SharedPreferences uid = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

        int uid1 = uid.getInt("uid", 1);

        Map<String, String> map = new HashMap<>();

        List<ShoppingCarBean.DataBean.ListBean> list = data.get(groupPosition).getList();

        int sellerid = list.get(childPosition).getSellerid();

        int pid = list.get(childPosition).getPid();

        int num = list.get(childPosition).getNum();

        int selected = list.get(childPosition).getSelected();

        map.put("sellerid", sellerid + "");

        map.put("pid", pid + "");

        map.put("num", num + "");

        map.put("selected", (selected == 0 ? 1 : 0) + "");

        map.put("uid", uid1 + "");

        presenter.updateCaartPresenter(map);

    }

    public void refreshNumberStatusOnLine(int groupPosition, int childPosition, int number) {

        SharedPreferences uid = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

        int uid1 = uid.getInt("uid", 1);

        Map<String, String> map = new HashMap<>();

        List<ShoppingCarBean.DataBean.ListBean> list = data.get(groupPosition).getList();

        int sellerid = list.get(childPosition).getSellerid();

        int pid = list.get(childPosition).getPid();

        int selected = list.get(childPosition).getSelected();

        map.put("sellerid", sellerid + "");

        map.put("pid", pid + "");

        map.put("num", number + "");

        map.put("selected", selected + "");

        map.put("uid", uid1 + "");

        presenter.updateCaartPresenter(map);

    }

}
