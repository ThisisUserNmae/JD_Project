package com.bwei.jd_project.mvp.shoppingcar.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.ShowOrderBean;
import com.bwei.jd_project.mvp.shoppingcar.view.MyView;

import java.util.List;

public class OrderRecyclerView extends RecyclerView.Adapter<OrderRecyclerView.MyViewHolder>{

    private Context context;

    private List<ShowOrderBean.DataBean> list;

    public OrderRecyclerView(Context context, List<ShowOrderBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.shoppingcar_showorder_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.orderTime.setText(list.get(position).getCreatetime());

        holder.orderTitle.setText(list.get(position).getTitle());

        holder.orderToalPrice.setText(list.get(position).getPrice()+" 元");

        int status = list.get(position).getStatus();

        if (status == 0){

            holder.orderStatus.setText("待支付");

        }

        if (status == 1){

            holder.orderStatus.setText("已支付");

        }

        if (status == 2){

            holder.orderStatus.setText("已取消");
            holder.orderStatus.setTextColor(Color.RED);

        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView orderTime;
        private final TextView orderTitle;
        private final TextView orderToalPrice;
        private final TextView orderStatus;

        public MyViewHolder(View itemView) {
            super(itemView);

            orderTime = itemView.findViewById(R.id.orderTime);
            orderTitle = itemView.findViewById(R.id.orderTitle);
            orderToalPrice = itemView.findViewById(R.id.orderToalPrice);
            orderStatus = itemView.findViewById(R.id.orderStatus);

        }
    }
}
