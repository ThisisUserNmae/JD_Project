package com.bwei.jd_project.mvp.classify.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.model.bean.CatagoryBean;

import java.util.List;

public class ProductClassifyBaseAdapter extends BaseAdapter {

    private Context context;

    private List<CatagoryBean.DataBean> list;

    public ProductClassifyBaseAdapter(Context context, List<CatagoryBean.DataBean> list) {
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

            convertView = View.inflate(context, R.layout.classify_name_item_layout,null);

            viewHolder.classifyName = convertView.findViewById(R.id.classifyName);

            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.classifyName.setText(list.get(position).getName());

        return convertView;
    }


    class ViewHolder{

        TextView classifyName;

    }
}
