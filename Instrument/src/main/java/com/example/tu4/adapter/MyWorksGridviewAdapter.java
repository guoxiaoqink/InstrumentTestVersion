package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tu4.R;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/11.
 */

public class MyWorksGridviewAdapter extends BaseAdapter {
    private ArrayList<Map<String,String>> listdata;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyWorksGridviewAdapter(Context context, ArrayList<Map<String,String>> listdata) {
        this.context = context;
       this.listdata = listdata;
        this.layoutInflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_my_works_gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            viewHolder.imgMyWorksVideoBg.setVisibility(View.GONE);
            viewHolder.rlMyWorksVideoTime.setVisibility(View.GONE);
            viewHolder.imgMyWorksItem.setImageResource(R.mipmap.image1);

        }else {
            int img = Integer.valueOf(listdata.get(position).get("img"));
            viewHolder.imgMyWorksItem.setImageResource(img);
            viewHolder.tvMyWorksTime.setText(listdata.get(position).get("time"));
            viewHolder.tvMyWorksDate.setText(listdata.get(position).get("date"));
        }

        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.imgMyWorksItem)
        ImageView imgMyWorksItem;

        @BindView(R.id.tv_my_works_time)
        TextView tvMyWorksTime;
        @BindView(R.id.tv_my_works_date)
        TextView tvMyWorksDate;

        @BindView(R.id.img_my_works_video_bg)
        ImageView imgMyWorksVideoBg;

        @BindView(R.id.rl_my_works_video_time)
        RelativeLayout rlMyWorksVideoTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
