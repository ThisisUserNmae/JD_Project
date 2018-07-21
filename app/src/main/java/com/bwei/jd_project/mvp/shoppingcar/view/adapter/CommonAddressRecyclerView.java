package com.bwei.jd_project.mvp.shoppingcar.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.shoppingcar.model.bean.CommonAddressBean;

import java.util.List;

public class CommonAddressRecyclerView extends RecyclerView.Adapter<CommonAddressRecyclerView.MyViewHolder> {

    private Context context;

    private List<CommonAddressBean.DataBean> list;

    public CommonAddressRecyclerView(Context context, List<CommonAddressBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(context).inflate(R.layout.shoppingcar_commonaddress_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.commonAddress.setText("地址: " + list.get(position).getAddr());

        holder.commonAddressName.setText("姓名: " + list.get(position).getName());

        holder.commonAddressPhoto.setText("手机号: " + list.get(position).getMobile() + "");

        int status = list.get(position).getStatus();

        if (status == 1){

            holder.useAddress.setVisibility(View.INVISIBLE);

        }

        if (status == 0){

            holder.useAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onClickListener != null) {

                        onClickListener.OnClick(v, position);

                    }

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView commonAddressName;

        private final TextView commonAddressPhoto;

        private final TextView commonAddress;

        private final Button useAddress;

        public MyViewHolder(View itemView) {

            super(itemView);

            commonAddressName = itemView.findViewById(R.id.commonAddressName);
            commonAddressPhoto = itemView.findViewById(R.id.commonAddressPhoto);
            commonAddress = itemView.findViewById(R.id.commonAddress);
            useAddress = itemView.findViewById(R.id.useAddress);


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
