package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ConnectUsGridviewAdapter extends BaseAdapter {
    private LayoutInflater minflater;
    private List<String> mdata;

    public ConnectUsGridviewAdapter(List<String> list, Context context) {
        super();
        mdata = new ArrayList<String>();
        minflater = LayoutInflater.from(context);
        mdata = list;
    }

    @Override
    public int getCount() {
        if (null != mdata) {
            return mdata.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConnectUsGridviewAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.connect_us_gridview_item, null);
            viewHolder = new ConnectUsGridviewAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ConnectUsGridviewAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.imgCustomServe.setText(mdata.get(position));
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.img_isonline)
        ImageView imgIsonline;
        @BindView(R.id.img_custom_serve)
        TextView imgCustomServe;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
