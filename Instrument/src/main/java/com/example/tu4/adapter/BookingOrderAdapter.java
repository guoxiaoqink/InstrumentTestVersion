package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.utils.GetImageByUrl;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 苏春雨 on 2016/10/18.
 */

public class BookingOrderAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Map<String,String>> listData;

    public BookingOrderAdapter(Context context,ArrayList<Map<String,String>> listData) {
        this.listData = listData;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.booking_order_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvClassName.setText(listData.get(position).get("class_name"));
        viewHolder.tvClassTime.setText(listData.get(position).get("class_time")+"课时");
        viewHolder.tvOrderTime.setText("订单时间 "+listData.get(position).get("date"));
        viewHolder.tvCost.setText("￥"+listData.get(position).get("class_price"));
        viewHolder.tvRealCost.setText("￥"+listData.get(position).get("class_price"));
        viewHolder.tvTeacherName.setText(listData.get(position).get("teacher_name"));
        viewHolder.tvIsclass.setText(listData.get(position).get("situation"));
        String url = listData.get(position).get("class_pic_url");
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(viewHolder.imgClass, url);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_order_time)
        TextView tvOrderTime;
        @BindView(R.id.tv_isclass)
        TextView tvIsclass;
        @BindView(R.id.img_class)
        ImageView imgClass;
        @BindView(R.id.tv_class_name)
        TextView tvClassName;
        @BindView(R.id.tv_class_time)
        TextView tvClassTime;
        @BindView(R.id.tv_teacher_name)
        TextView tvTeacherName;
        @BindView(R.id.tv_cost)
        TextView tvCost;
        @BindView(R.id.tv_real_cost)
        TextView tvRealCost;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}