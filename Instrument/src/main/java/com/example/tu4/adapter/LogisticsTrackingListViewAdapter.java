package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tu4.model.AplicationStatic.LISTVIEW_COUNT;

/**
 * Created by Adelais on 2016/9/27.
 */

public class LogisticsTrackingListViewAdapter extends BaseAdapter {
    ViewHolder1 viewHolder12;

    private LayoutInflater mInflater = null;

    public LogisticsTrackingListViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.logistics_tracking_listview_item, null);
            viewHolder12 = new ViewHolder1(convertView);
            convertView.setTag(viewHolder12);
        }
        viewHolder12 = (ViewHolder1) convertView.getTag();
        if (position == 0) {
        /*    ViewGroup.LayoutParams linearParams = viewHolder12.circleimage.getLayoutParams();
            linearParams.height = 25;
            linearParams.width = 25;
            viewHolder12.circleimage.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件aaa
            viewHolder12.circleimage.setImageResource(R.mipmap.color97c8cd);*/
            viewHolder12.circleimage.setImageResource(R.mipmap.ic_log_selected);
            viewHolder12.textviewVerticalline.setVisibility(View.VISIBLE);
        } else {
            viewHolder12.circleimage.setImageResource(R.mipmap.ic_log_normal);
        }

        if (position == LISTVIEW_COUNT - 1) {
            viewHolder12.textviewVerticalline.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static class ViewHolder1 {
        @BindView(R.id.circleimage)
        CircleImageView circleimage;
        @BindView(R.id.textview_verticalline)
        TextView textviewVerticalline;
        @BindView(R.id.textview_content)
        TextView textviewContent;
        @BindView(R.id.textview_time)
        TextView textviewTime;

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

