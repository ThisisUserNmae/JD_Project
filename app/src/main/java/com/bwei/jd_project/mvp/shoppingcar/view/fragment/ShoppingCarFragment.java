package com.bwei.jd_project.mvp.shoppingcar.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.bwei.jd_project.mvp.shoppingcar.model.bean.DeleteCartBean;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.presenter.ShoppingCarPresenter;
import com.bwei.jd_project.mvp.shoppingcar.view.adapter.ShoppingCarShowExpandableListView;
import com.bwei.jd_project.mvp.shoppingcar.view.iview.IShoppingCarView;

import java.util.List;

public class ShoppingCarFragment extends BaseFragment<ShoppingCarPresenter> implements IShoppingCarView, View.OnClickListener {


    private CheckBox checkBoxAll;

    private TextView toalPrice;

    private Button toalNumber;

    private ExpandableListView shoppingCarExpandableListView;

    private ShoppingCarShowExpandableListView shoppingCarShowExpandableListView;

    private List<ShoppingCarBean.DataBean> data;
    private int uid;
    private SharedPreferences sharedPreferences;

    @Override
    protected void initDatas() {

        presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

    }

    @Override
    protected void initViews() {

        shoppingCarExpandableListView = view.findViewById(R.id.shoppingCarExpandableListView);

        checkBoxAll = view.findViewById(R.id.CheckboxAll);

        toalPrice = view.findViewById(R.id.toalPrice);

        toalNumber = view.findViewById(R.id.toalNumber);

        presenter = new ShoppingCarPresenter(this);

        checkBoxAll.setOnClickListener(this);

        sharedPreferences = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

        uid = sharedPreferences.getInt("uid", 1);

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

                    shoppingCarShowExpandableListView.isCurrentAllSelected(groupPosition, !currentProductSelected);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    sumPriceAndNumber();

                }

                @Override
                public void onProductCheckedChange(int groupPosition, int childPosition) {

                    shoppingCarShowExpandableListView.ChangeCurrentProductStatus(groupPosition, childPosition);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

                    sumPriceAndNumber();

                }

                @Override
                public void onProductNumberChange(int groupPosition, int childPosition, int number) {

                    shoppingCarShowExpandableListView.ChangeCurrentProductNumber(groupPosition, childPosition, number);

                    shoppingCarShowExpandableListView.notifyDataSetChanged();

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

                            presenter.deleteCart(uid,pid);

                        }
                    });

                    alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getActivity(),"您不能再点减了",Toast.LENGTH_SHORT).show();

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

        checkBoxAll.setSelected(currentCheckBoxSelected);

        int number = shoppingCarShowExpandableListView.sumAllNumber();

        toalNumber.setText("去结算(" + number + ")");

        double Price = shoppingCarShowExpandableListView.sumAllPrice();

        toalPrice.setText("总价: " + Price + "元");

    }

    @Override
    public void getError(Throwable throwable) {

    }

    //处理删除成功数据的回调
    @Override
    public void getDeleteCartSuccess(DeleteCartBean deleteCartBean) {

        String code = deleteCartBean.getCode();

        if ("0".equals(code)){

            //Toast.makeText(getActivity(),"删除成功了",Toast.LENGTH_SHORT).show();
            //查询购物车

                presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

                shoppingCarShowExpandableListView.notifyDataSetChanged();

        }else{

            Toast.makeText(getActivity(),"删除失败了",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void getDeleteCarError(Throwable throwable) {

        Toast.makeText(getActivity(),""+throwable.getMessage(),Toast.LENGTH_SHORT).show();

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
        }

    }

    //使用懒加载加载网络数据
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && presenter != null) {

            if (uid != 1){



            }else{

/*                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("登陆提示框");

                alert.setMessage("您确认要去登陆吗?");
                alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent it = new Intent(getActivity(), LoginActivity.class);

                        startActivity(it);

                    }
                });

                alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
                alert.create();*/

            }

        } else {

            if (uid != 1){

                //presenter = new ShoppingCarPresenter(this);

                //presenter.selectShoppingCar(HttpConfig.SHOPPINGCAR_URL + uid);

            }else{

               /* AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("登陆提示框");

                alert.setMessage("您确认要去登陆吗?");
                alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent it = new Intent(getActivity(), LoginActivity.class);

                        startActivity(it);

                    }
                });

                alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
                alert.create();*/

            }

        }

    }
}
