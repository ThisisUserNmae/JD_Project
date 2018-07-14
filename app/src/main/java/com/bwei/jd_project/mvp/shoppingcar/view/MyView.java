package com.bwei.jd_project.mvp.shoppingcar.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;

public class MyView extends LinearLayout implements View.OnClickListener {

    private View inflate;

    private TextView add;

    private TextView number;

    private TextView jian;

    private int num = 0;


    public int getNum() {
        return num;

    }

    public void setNum(int num) {
        this.num = num;

        number.setText(num + "");
    }

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate = View.inflate(context, R.layout.shoppingcar_myview_item_layout, this);

        initViews();

    }

    private void initViews() {

        add = inflate.findViewById(R.id.add);
        number = inflate.findViewById(R.id.number);
        jian = inflate.findViewById(R.id.jian);

        add.setOnClickListener(this);

        jian.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add:

                num++;

                number.setText(num + "");

                if (onSumNumberClickListener != null) {

                    onSumNumberClickListener.onClicklistener(num);

                }

                break;

            case R.id.jian:

                if (num > 1) {

                    --num;

                    number.setText(num + "");

                    if (onSumNumberClickListener != null) {

                        onSumNumberClickListener.onClicklistener(num);

                    }

                } else {

                    if (isDeleteCurrentProduct != null) {

                        isDeleteCurrentProduct.OnIsDeleteClickListener();

                    }

                }

                break;

        }

    }

    onSumNumberClickListener onSumNumberClickListener;

    public void setOnSumNumberClickListener(MyView.onSumNumberClickListener onSumNumberClickListener) {
        this.onSumNumberClickListener = onSumNumberClickListener;
    }

    public interface onSumNumberClickListener {

        void onClicklistener(int num);

    }


    isDeleteCurrentProduct isDeleteCurrentProduct;


    public void setIsDeleteCurrentProduct(MyView.isDeleteCurrentProduct isDeleteCurrentProduct) {
        this.isDeleteCurrentProduct = isDeleteCurrentProduct;
    }

    public interface isDeleteCurrentProduct {

        void OnIsDeleteClickListener();

    }

}
