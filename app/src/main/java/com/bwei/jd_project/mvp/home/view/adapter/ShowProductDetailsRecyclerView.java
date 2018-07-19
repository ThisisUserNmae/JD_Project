package com.bwei.jd_project.mvp.home.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.model.bean.ShowDetailsBean;

public class ShowProductDetailsRecyclerView extends RecyclerView.Adapter<ShowProductDetailsRecyclerView.MyViewHolder> {

    private Context context;

    private ShowDetailsBean.DataBean data;

    private ShowDetailsBean.SellerBean seller;

    public ShowProductDetailsRecyclerView(Context context, ShowDetailsBean.DataBean data, ShowDetailsBean.SellerBean seller) {
        this.context = context;
        this.data = data;
        this.seller = seller;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.showproductdrtails_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.showDetailsContent.setText(data.getSubhead());

        holder.showDetailsTitle.setText(data.getTitle());

        holder.showDetailsPrice.setText("现价: " + data.getPrice() + "元");

        holder.showDetailsShopsName.setText(seller.getName());


        holder.showDetailsAddShoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onAddShoppingCarClickLiener != null) {

                    onAddShoppingCarClickLiener.OnClickListener(v, position);

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView showDetailsTitle;
        private final TextView showDetailsShopsName;
        private final TextView showDetailsContent;
        private final TextView showDetailsPrice;
        private final TextView showDetailsAddShoppingCar;
        private final TextView showDetailsBuy;

        public MyViewHolder(View itemView) {
            super(itemView);


            showDetailsTitle = itemView.findViewById(R.id.showDetailsTitle);

            showDetailsShopsName = itemView.findViewById(R.id.showDetailsShopsName);

            showDetailsContent = itemView.findViewById(R.id.showDetailsContent);

            showDetailsPrice = itemView.findViewById(R.id.showDetailsPrice);

            showDetailsAddShoppingCar = itemView.findViewById(R.id.showDetailsAddShoppingCar);

            showDetailsBuy = itemView.findViewById(R.id.showDetailsContent);


        }

    }


    OnAddShoppingCarClickLiener onAddShoppingCarClickLiener;

    public void setOnAddShoppingCarClickLiener(OnAddShoppingCarClickLiener onAddShoppingCarClickLiener) {
        this.onAddShoppingCarClickLiener = onAddShoppingCarClickLiener;
    }

    public interface OnAddShoppingCarClickLiener {

        void OnClickListener(View view, int position);


    }
}
