package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tu4.utils.ApplicationStaticConstants.LISTVIEW_COUNT;

/**
 * Created by Adelais on 2016/10/3.
 */

public class SerachOrderListviewAdapter extends BaseAdapter {
    ViewHolder viewHolder;
    SerachOrderListviewListviewAdapter adapter;
    private LayoutInflater mInflater = null;


    public SerachOrderListviewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        adapter = new SerachOrderListviewListviewAdapter(context);
    }

    @Override
    public int getCount() {
        return LISTVIEW_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.serach_order_listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.listviewSubjectSerach.setAdapter(adapter);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.textview_time)
        TextView textviewTime;
        @BindView(R.id.listview_subject_serach)
        ListView listviewSubjectSerach;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}