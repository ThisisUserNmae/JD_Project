package com.bwei.jd_project.mvp.home.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ProductClassifyShowRecyclerView extends RecyclerView.Adapter<ProductClassifyShowRecyclerView.MyViewHolder> {

    private Context context;

    private List<CatagoryBean.DataBean> list;

    public ProductClassifyShowRecyclerView(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.home_product_classify_show_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String icon = list.get(position).getIcon();

        holder.productClassifyPic.setImageURI(icon);

        holder.productClassifyName.setText(list.get(position).getName());


        holder.productClassifyPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setOnClickListener != null) {

                    setOnClickListener.OnClickListener(v, position);

                }

            }
        });

        holder.productClassifyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setOnClickListener != null) {

                    setOnClickListener.OnClickListener(v, position);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView productClassifyPic;
        private final TextView productClassifyName;

        public MyViewHolder(View itemView) {
            super(itemView);

            productClassifyPic = itemView.findViewById(R.id.productClassifyPic);

            productClassifyName = itemView.findViewById(R.id.productclassifyName);

        }
    }

    setOnClickListener setOnClickListener;

    public void setSetOnClickListener(ProductClassifyShowRecyclerView.setOnClickListener setOnClickListener) {
        this.setOnClickListener = setOnClickListener;
    }

    public interface setOnClickListener {

        void OnClickListener(View view, int position);


    }


}
