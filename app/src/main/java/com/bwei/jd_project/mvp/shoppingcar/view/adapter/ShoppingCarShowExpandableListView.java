package com.bwei.jd_project.mvp.shoppingcar.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShoppingCarBean;
import com.bwei.jd_project.mvp.shoppingcar.view.MyView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShoppingCarShowExpandableListView extends BaseExpandableListAdapter {

    private Context context;

    private List<ShoppingCarBean.DataBean> list;

    private List<ShoppingCarBean.DataBean.ListBean> childList;

    public ShoppingCarShowExpandableListView(Context context, List<ShoppingCarBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList() == null ? 0 : list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ShopsViewHolder shopsViewHolder;

        if (convertView == null) {

            shopsViewHolder = new ShopsViewHolder();

            convertView = View.inflate(context, R.layout.shoppingcar_shops_item_layout, null);

            shopsViewHolder.shopsName = convertView.findViewById(R.id.shopsName);

            shopsViewHolder.shopsCehckBox = convertView.findViewById(R.id.shopsCheckBox);

            convertView.setTag(shopsViewHolder);

        } else {

            shopsViewHolder = (ShopsViewHolder) convertView.getTag();

        }

        shopsViewHolder.shopsName.setText(list.get(groupPosition).getSellerName());

        boolean currentProductSelected = isCurrentProductSelected(groupPosition);

        shopsViewHolder.shopsCehckBox.setChecked(currentProductSelected);

        shopsViewHolder.shopsCehckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onCartListChangeListener != null) {

                    onCartListChangeListener.onSellerCheckedChange(groupPosition);

                }

            }
        });


        return convertView;
    }

    public boolean isCurrentProductSelected(int groupPosition) {

        List<ShoppingCarBean.DataBean.ListBean> childlist = this.list.get(groupPosition).getList();

        for (ShoppingCarBean.DataBean.ListBean listBean : childlist) {

            if (listBean.getSelected() == 0) {
                return false;
            }
        }

        return true;
        /*for (int i = 0; i < childlist.size(); i++) {

            if (childlist.get(i).getSelected() == 1) {

                return true;

            }

        }*/
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ProductViewHolder productViewHolder;

        if (convertView == null) {

            productViewHolder = new ProductViewHolder();

            convertView = View.inflate(context, R.layout.shoppingcar_product_content_item_layout, null);

            productViewHolder.productCheckBox = convertView.findViewById(R.id.productCheckbox);

            productViewHolder.productMyView = convertView.findViewById(R.id.productMyView);

            productViewHolder.productName = convertView.findViewById(R.id.productName);

            productViewHolder.productPic = convertView.findViewById(R.id.productPic);

            productViewHolder.productPrice = convertView.findViewById(R.id.productPrice);

            convertView.setTag(productViewHolder);

        } else {

            productViewHolder = (ProductViewHolder) convertView.getTag();

        }

        childList = this.list.get(groupPosition).getList();

        productViewHolder.productPrice.setText(childList.get(childPosition).getBargainPrice() + "元");

        String[] split = childList.get(childPosition).getImages().split("\\|");

        productViewHolder.productPic.setImageURI(split[0]);

        productViewHolder.productName.setText(childList.get(childPosition).getTitle());

        productViewHolder.productCheckBox.setChecked(childList.get(childPosition).getSelected() == 1);

        productViewHolder.productCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onCartListChangeListener != null) {

                    onCartListChangeListener.onProductCheckedChange(groupPosition, childPosition);

                }

            }
        });

        productViewHolder.productMyView.setNum(childList.get(childPosition).getNum());

        productViewHolder.productMyView.setOnSumNumberClickListener(new MyView.onSumNumberClickListener() {
            @Override
            public void onClicklistener(int num) {

                if (onCartListChangeListener != null) {

                    onCartListChangeListener.onProductNumberChange(groupPosition, childPosition, num);

                }

            }
        });

        productViewHolder.productMyView.setIsDeleteCurrentProduct(new MyView.isDeleteCurrentProduct() {
            @Override
            public void OnIsDeleteClickListener() {

                if (onDeleteClickListener != null) {

                    onDeleteClickListener.OnClickListener(groupPosition, childPosition);

                }

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void isCurrentAllSelected(int groupPosition, boolean b) {

        List<ShoppingCarBean.DataBean.ListBean> childlist = this.list.get(groupPosition).getList();

        for (int i = 0; i < childlist.size(); i++) {

            ShoppingCarBean.DataBean.ListBean listBean = childlist.get(i);

            listBean.setSelected(b ? 1 : 0);

        }

    }

    public boolean isCurrentCheckBoxSelected() {

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> childlist = this.list.get(i).getList();

            for (int j = 0; j < childlist.size(); j++) {

                if (childlist.get(j).getSelected() == 0) {

                    return false;

                }

            }

        }

        return true;
    }

    public int sumAllNumber() {

        int toalNumber = 0;

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> childlist = this.list.get(i).getList();

            for (int j = 0; j < childlist.size(); j++) {

                ShoppingCarBean.DataBean.ListBean listBean = childlist.get(j);

                if (listBean.getSelected() == 1) {

                    int num = listBean.getNum();

                    toalNumber += num;

                }

            }

        }

        return toalNumber;
    }

    public double sumAllPrice() {
        double toalPrice = 0;

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> childlist = this.list.get(i).getList();

            for (int j = 0; j < childlist.size(); j++) {

                if (childlist.get(j).getSelected() == 1) {

                    int num = childlist.get(j).getNum();

                    double price = childlist.get(j).getBargainPrice();

                    toalPrice += (num * price);

                }

            }

        }

        return toalPrice;
    }

    public void ChangeCurrentProductStatus(int groupPosition, int childPosition) {

        ShoppingCarBean.DataBean dataBean = list.get(groupPosition);
        ShoppingCarBean.DataBean.ListBean listBean = dataBean.getList().get(childPosition);

        listBean.setSelected(listBean.getSelected() == 0 ? 1 : 0);

    }

    public void ChangeCurrentProductNumber(int groupPosition, int childPosition, int number) {

        List<ShoppingCarBean.DataBean.ListBean> childList = this.list.get(groupPosition).getList();

        ShoppingCarBean.DataBean.ListBean listBean = childList.get(childPosition);

        listBean.setNum(number);

    }

    public boolean AllShopsAndProductSelected() {

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> childList = this.list.get(i).getList();

            for (int j = 0; j < childList.size(); j++) {

                if (childList.get(j).getSelected() == 0) {

                    return false;

                }

            }

        }

        return true;
    }

    public void isShopsAndProductSelected(boolean b) {

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> childList = list.get(i).getList();

            for (int j = 0; j < childList.size(); j++) {

                childList.get(j).setSelected(b ? 1 : 0);

            }

        }

    }

    class ShopsViewHolder {

        TextView shopsName;

        CheckBox shopsCehckBox;

    }

    class ProductViewHolder {

        CheckBox productCheckBox;

        SimpleDraweeView productPic;

        TextView productName;

        TextView productPrice;

        MyView productMyView;
    }

    onCartListChangeListener onCartListChangeListener;

    public void setOnCartListChangeListener(ShoppingCarShowExpandableListView.onCartListChangeListener onCartListChangeListener) {
        this.onCartListChangeListener = onCartListChangeListener;
    }

    public interface onCartListChangeListener {

        void onSellerCheckedChange(int groupPosition);

        void onProductCheckedChange(int groupPosition, int childPosition);

        void onProductNumberChange(int groupPosition, int childPosition, int number);
    }


    OnDeleteClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public interface OnDeleteClickListener {

        void OnClickListener(int groupPoistion, int childPoistion);

    }

}
