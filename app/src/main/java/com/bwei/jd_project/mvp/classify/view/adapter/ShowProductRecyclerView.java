package com.bwei.jd_project.mvp.classify.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.classify.model.bean.ShowProductBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShowProductRecyclerView extends RecyclerView.Adapter<ShowProductRecyclerView.MyViewHolder> {

    private Context context;

    private List<ShowProductBean.DataBean> list;

    public ShowProductRecyclerView(Context context, List<ShowProductBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowProductRecyclerView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.showproduct_recyclerview_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowProductRecyclerView.MyViewHolder holder, final int position) {

        String[] split = list.get(position).getImages().split("\\|");

        holder.showProductPic.setImageURI(split[0]);

        holder.showProductContent.setText(list.get(position).getSubhead());

        holder.showProductTitle.setText(list.get(position).getTitle());

        holder.showProductOldPrice.setText("原价" + list.get(position).getBargainPrice() + "元");

        holder.showProductNewPrice.setText("现价" + list.get(position).getPrice() + "元");


        holder.showProductPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickListener != null) {

                    onClickListener.OnClickListener(v, position);

                }

            }
        });


        holder.showProductTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickListener != null) {

                    onClickListener.OnClickListener(v, position);

                }

            }
        });

        holder.showProductContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickListener != null) {

                    onClickListener.OnClickListener(v, position);

                }

            }
        });

        holder.showProductNewPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickListener != null) {

                    onClickListener.OnClickListener(v, position);

                }

            }
        });

        holder.showProductOldPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickListener != null) {

                    onClickListener.OnClickListener(v, position);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView showProductPic;
        private final TextView showProductOldPrice;
        private final TextView showProductNewPrice;
        private final TextView showProductContent;
        private final TextView showProductTitle;

        public MyViewHolder(View itemView) {
            super(itemView);

            showProductPic = itemView.findViewById(R.id.showProductPic);
            showProductOldPrice = itemView.findViewById(R.id.showProductOldPrice);
            showProductNewPrice = itemView.findViewById(R.id.showProductNewPrice);
            showProductContent = itemView.findViewById(R.id.showProductContent);
            showProductTitle = itemView.findViewById(R.id.showProductTitle);

        }

    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {

        void OnClickListener(View view, int position);

    }
}
