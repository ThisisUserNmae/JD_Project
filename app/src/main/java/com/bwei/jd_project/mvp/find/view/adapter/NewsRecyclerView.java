package com.bwei.jd_project.mvp.find.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.find.model.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class NewsRecyclerView extends RecyclerView.Adapter {

    private Context context;

    private List<NewsBean.ResultBean.DataBean> list;

    public NewsRecyclerView(Context context, List<NewsBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 2){

            View inflate = LayoutInflater.from(context).inflate(R.layout.find_news_second_item_layout, parent, false);

            ViewHolder02 viewHolder02 = new ViewHolder02(inflate);

            return viewHolder02;

        }

        if (viewType == 3){

            View inflate = LayoutInflater.from(context).inflate(R.layout.find_news_thirdly_item_layout, parent, false);

            ViewHolder03 viewHolder03 = new ViewHolder03(inflate);

            return viewHolder03;

        }

        View inflate = LayoutInflater.from(context).inflate(R.layout.find_news_first_item_layout, parent, false);

        ViewHolder01 viewHolder01 = new ViewHolder01(inflate);

        return viewHolder01;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof ViewHolder01){

            ViewHolder01 viewHolder01 = (ViewHolder01) holder;


            viewHolder01.newsTitle01.setText(list.get(position).getTitle());

            String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();

            viewHolder01.newsPic01_01.setImageURI(thumbnail_pic_s);

        }

        if (holder instanceof ViewHolder02){

            ViewHolder02 viewHolder02 = (ViewHolder02) holder;

            viewHolder02.newsTitle02.setText(list.get(position).getTitle());

            String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();
            String thumbnail_pic_s02 = list.get(position).getThumbnail_pic_s02();

            viewHolder02.newsPic02_01.setImageURI(thumbnail_pic_s);
            viewHolder02.newsPic02_02.setImageURI(thumbnail_pic_s02);


        }

        if (holder instanceof ViewHolder03){

            ViewHolder03 viewHolder03 = (ViewHolder03) holder;

            viewHolder03.newsTitle03.setText(list.get(position).getTitle());

            String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();
            String thumbnail_pic_s02 = list.get(position).getThumbnail_pic_s02();
            String thumbnail_pic_s03 = list.get(position).getThumbnail_pic_s03();

            viewHolder03.newsPic03_01.setImageURI(thumbnail_pic_s);
            viewHolder03.newsPic03_02.setImageURI(thumbnail_pic_s02);
            viewHolder03.newsPic03_03.setImageURI(thumbnail_pic_s03);

        }

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();

    }

    @Override
    public int getItemViewType(int position) {


        String thumbnail_pic_s02 = list.get(position).getThumbnail_pic_s02();

        String thumbnail_pic_s03 = list.get(position).getThumbnail_pic_s03();

        if (!TextUtils.isEmpty(thumbnail_pic_s03)){

            return 3;

        }

        if (!TextUtils.isEmpty(thumbnail_pic_s02)){

            return 2;

        }

        return 1;

    }


    class ViewHolder01 extends RecyclerView.ViewHolder{

        TextView newsTitle01;
        SimpleDraweeView newsPic01_01;

        public ViewHolder01(View itemView) {
            super(itemView);

            newsTitle01 = itemView.findViewById(R.id.newsTitle01);

            newsPic01_01 = itemView.findViewById(R.id.newsPic01_01);

        }
    }

    class ViewHolder02 extends RecyclerView.ViewHolder{

        TextView newsTitle02;
        SimpleDraweeView newsPic02_01;
        SimpleDraweeView newsPic02_02;

        public ViewHolder02(View itemView) {
            super(itemView);

            newsTitle02 = itemView.findViewById(R.id.newsTitle02);

            newsPic02_01 = itemView.findViewById(R.id.newsPic02_01);

            newsPic02_02 = itemView.findViewById(R.id.newsPic02_02);

        }
    }

    class ViewHolder03 extends RecyclerView.ViewHolder{

        TextView newsTitle03;
        SimpleDraweeView newsPic03_01;
        SimpleDraweeView newsPic03_02;
        SimpleDraweeView newsPic03_03;

        public ViewHolder03(View itemView) {
            super(itemView);

            newsTitle03 = itemView.findViewById(R.id.newsTitle03);

            newsPic03_01 = itemView.findViewById(R.id.newsPic03_01);

            newsPic03_02 = itemView.findViewById(R.id.newsPic03_02);

            newsPic03_03 = itemView.findViewById(R.id.newsPic03_03);

        }
    }

}
