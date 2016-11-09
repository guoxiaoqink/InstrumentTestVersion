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
 * Created by Administrator on 2016/11/8.
 */

public class TransactionRecordsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Map<String, String>> listData;

    public TransactionRecordsAdapter(Context context, ArrayList<Map<String, String>> listData) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listData = listData;
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
            convertView = inflater.inflate(R.layout.transaction_parent_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTransName.setText("乐器名称：" + listData.get(position).get("name"));
        viewHolder.tvTransMode.setText(listData.get(position).get("situation"));
        viewHolder.tvTransPrice.setText("￥" + listData.get(position).get("price"));
        viewHolder.tvTransTime.setText("订单时间：" + listData.get(position).get("date"));
        viewHolder.tvTransType.setText("属性：" + listData.get(position).get("type"));
        viewHolder.tvTransRealPay.setText("实付：￥ " + listData.get(position).get("now_price"));
        viewHolder.tvTransFreigh.setText("(含运费：" + listData.get(position).get("freigh") + ")");

        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(viewHolder.imgTransPic, listData.get(position).get("pic_url"));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_trans_time)
        TextView tvTransTime;
        @BindView(R.id.tv_trans_mode)
        TextView tvTransMode;
        @BindView(R.id.img_trans_pic)
        ImageView imgTransPic;
        @BindView(R.id.tv_trans_name)
        TextView tvTransName;
        @BindView(R.id.tv_trans_type)
        TextView tvTransType;
        @BindView(R.id.tv_trans_price)
        TextView tvTransPrice;
        @BindView(R.id.tv_trans_real_pay)
        TextView tvTransRealPay;
        @BindView(R.id.tv_trans_freigh)
        TextView tvTransFreigh;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
