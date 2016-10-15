package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/13.
 */

public class SystemInformationListviewAdapter extends BaseAdapter {
    private String[] name, text, time;
    private Context context;
    private LayoutInflater layoutInflater;

    public SystemInformationListviewAdapter(Context context, String[] name, String[] text,
                                            String[] time) {
        this.name = name;
        this.text = text;
        this.time = time;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
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
        viewHolder.tvSystemInformationName.setText(name[position]);
        viewHolder.tvSystemInformationContext.setText(text[position]);
        viewHolder.tvSystemInformationTime.setText(time[position]);

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
