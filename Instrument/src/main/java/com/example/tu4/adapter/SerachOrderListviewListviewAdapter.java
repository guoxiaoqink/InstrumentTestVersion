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

import static com.example.tu4.model.AplicationStatic.LISTVIEW_COUNT_THREE;

/**
 * Created by Adelais on 2016/10/3.
 */

public class SerachOrderListviewListviewAdapter extends BaseAdapter {
    ViewHolder viewHolder;
    private LayoutInflater mInflater = null;

    public SerachOrderListviewListviewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return LISTVIEW_COUNT_THREE;
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
            convertView = mInflater.inflate(R.layout.serach_order_listview_item_listview_item,
                    null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.textview_time_order_listviewitem_item)
        TextView textviewTimeOrderListviewitemItem;
        @BindView(R.id.textview_subject_order_listviewitem_item)
        TextView textviewSubjectOrderListviewitemItem;
        @BindView(R.id.textview_address_order_listviewitem_item)
        TextView textviewAddressOrderListviewitemItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}