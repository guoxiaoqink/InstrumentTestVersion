package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 苏春雨 on 2016/10/18.
 */

public class BookingOrderAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public BookingOrderAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
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