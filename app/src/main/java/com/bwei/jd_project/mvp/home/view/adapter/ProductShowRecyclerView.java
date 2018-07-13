package com.bwei.jd_project.mvp.home.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.model.bean.AdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ProductShowRecyclerView extends RecyclerView.Adapter<ProductShowRecyclerView.MyViewHolder> {

    private Context context;

    private List<AdBean.MiaoshaBean.ListBeanX> list;

    public ProductShowRecyclerView(Context context, List<AdBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.home_productshow_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String[] split = list.get(position).getImages().split("\\|");

        holder.homeItemPic.setImageURI(split[1]);

        holder.homeItemPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setOnClickListener!=null){

                    setOnClickListener.onClickListener(v,position);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView homeItemPic;

        public MyViewHolder(View itemView) {
            super(itemView);

            homeItemPic = itemView.findViewById(R.id.homeItemPic);

        }

    }

    setOnClickListener setOnClickListener;

    public void setSetOnClickListener(ProductShowRecyclerView.setOnClickListener setOnClickListener) {

        this.setOnClickListener = setOnClickListener;
    }

    public interface setOnClickListener{

        void onClickListener(View view,int position);

    }

}
