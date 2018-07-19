package com.bwei.jd_project.mvp.home.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.model.bean.Product;

import java.util.List;

public class SelectKeyWrodRecyclerView extends RecyclerView.Adapter<SelectKeyWrodRecyclerView.MyViewHolder> {


    private Context context;

    private List<Product> list;

    public SelectKeyWrodRecyclerView(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.home_selectproductname_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.productName.setText(list.get(position).getProductName());

        holder.productName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickListener != null) {

                    onClickListener.OnClick(v, position);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView productName;

        public MyViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);

        }
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {

        void OnClick(View view, int position);

    }

}
