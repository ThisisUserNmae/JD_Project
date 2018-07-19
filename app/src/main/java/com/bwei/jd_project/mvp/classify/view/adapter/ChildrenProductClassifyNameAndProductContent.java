package com.bwei.jd_project.mvp.classify.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.classify.model.bean.ProductCatagoryBean;

import java.util.List;

public class ChildrenProductClassifyNameAndProductContent extends RecyclerView.Adapter<ChildrenProductClassifyNameAndProductContent.MyViewHolder> {

    private Context context;

    private List<ProductCatagoryBean.DataBean> list;

    public ChildrenProductClassifyNameAndProductContent(Context context, List<ProductCatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.classify_productchildrenname_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.childrenProductClassifyName.setText(list.get(position).getName());

        final List<ProductCatagoryBean.DataBean.ListBean> list = this.list.get(position).getList();

        ChildrenProductContentRecyclerView childrenProductContentRecyclerView = new ChildrenProductContentRecyclerView(context, list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);

        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        holder.childrenProductContent.setLayoutManager(gridLayoutManager);

        holder.childrenProductContent.setAdapter(childrenProductContentRecyclerView);

        childrenProductContentRecyclerView.setOnClickLisener(new ChildrenProductContentRecyclerView.setOnClickLisener() {
            @Override
            public void OnClickListener(View view, int position) {

                if (onClickListener != null) {

                    onClickListener.onClickListener(view, position);

                }

            }
        });


    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView childrenProductClassifyName;
        private final RecyclerView childrenProductContent;

        public MyViewHolder(View itemView) {
            super(itemView);

            childrenProductClassifyName = itemView.findViewById(R.id.childrenProductClassifyName);

            childrenProductContent = itemView.findViewById(R.id.childrenProductContent);

        }


    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {

        void onClickListener(View view, int position);

    }
}
