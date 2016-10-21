package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tu4.R;

/**
 * Created by WQJ on 2016/10/12.
 */

public class EnsureOrderListviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;

    public EnsureOrderListviewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 2;
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
            convertView = mInflater.inflate(R.layout.instrument_detail_withmoney, null);
        }

        return convertView;
    }
}