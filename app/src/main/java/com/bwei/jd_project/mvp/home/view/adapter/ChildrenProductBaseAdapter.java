package com.bwei.jd_project.mvp.home.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.model.bean.AdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ChildrenProductBaseAdapter extends BaseAdapter {

    private Context context;

    private List<AdBean.TuijianBean.ListBean> list;

    public ChildrenProductBaseAdapter(Context context, List<AdBean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){

            viewHolder = new ViewHolder();

            convertView = View.inflate(context, R.layout.home_childrenproduct_item_layout,null);

            viewHolder.CProductItemPic = convertView.findViewById(R.id.CProductItemPic);
            viewHolder.CProductItemTitle = convertView.findViewById(R.id.CProductItemTitle);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        String[] split = list.get(position).getImages().split("\\|");

        viewHolder.CProductItemPic.setImageURI(split[0]);

        viewHolder.CProductItemTitle.setText(list.get(position).getTitle());

        return convertView;
    }

    class ViewHolder{

        SimpleDraweeView CProductItemPic;

        TextView CProductItemTitle;


    }

}
