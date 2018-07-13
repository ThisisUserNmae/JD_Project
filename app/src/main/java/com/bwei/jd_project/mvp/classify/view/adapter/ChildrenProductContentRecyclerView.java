package com.bwei.jd_project.mvp.classify.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.classify.model.bean.ProductCatagoryBean;
import com.bwei.jd_project.mvp.home.model.MyGridView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ChildrenProductContentRecyclerView extends RecyclerView.Adapter<ChildrenProductContentRecyclerView.MyViewHolder> {

    private Context context;

    private List<ProductCatagoryBean.DataBean.ListBean> list;

    public ChildrenProductContentRecyclerView(Context context, List<ProductCatagoryBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.classify_product_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String icon = list.get(position).getIcon();

        holder.classifyProductPic.setImageURI(icon);

        holder.classifyProductName.setText(list.get(position).getName());

        holder.classifyProductPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setOnClickLisener!=null){

                    setOnClickLisener.OnClickListener(v,position);

                }

            }
        });

        holder.classifyProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setOnClickLisener!=null){

                    setOnClickLisener.OnClickListener(v,position);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView classifyProductPic;
        private final TextView classifyProductName;

        public MyViewHolder(View itemView) {
            super(itemView);

            classifyProductPic = itemView.findViewById(R.id.classifyProductPic);

            classifyProductName = itemView.findViewById(R.id.classifyProductName);

        }
    }

    setOnClickLisener setOnClickLisener;

    public void setOnClickLisener(ChildrenProductContentRecyclerView.setOnClickLisener setOnClickLisener) {
        this.setOnClickLisener = setOnClickLisener;
    }

    public interface setOnClickLisener{

        void OnClickListener(View view,int position);

    }


}
