package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tu4.R;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/13.
 */

public class SystemInformationListviewAdapter extends BaseAdapter {
    private ArrayList<Map<String,String>> dataList;
    private Context context;
    private LayoutInflater layoutInflater;

    public SystemInformationListviewAdapter(Context context, ArrayList<Map<String,String>> dataList) {
       this.dataList = dataList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.activity_system_information_listview_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)convertView.getTag();

        }

        viewHolder.tvSystemInformationName.setText(dataList.get(position).get("title"));
        viewHolder.tvSystemInformationContext.setText(dataList.get(position).get("content"));
        viewHolder.tvSystemInformationTime.setText(dataList.get(position).get("date"));

        return convertView;
    }
    public class ViewHolder{

        @BindView(R.id.tvSystemInformationName)
        TextView tvSystemInformationName;

        @BindView(R.id.tvSystemInformationContext)
        TextView tvSystemInformationContext;

        @BindView(R.id.tvSystemInformationTime)
        TextView tvSystemInformationTime;

         ViewHolder(View view){
            ButterKnife.bind(this, view);

        }
    }
}
