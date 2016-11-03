package com.example.tu4.adapter;

import android.content.Context;
import android.util.Log;
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
 * 帮助中心
 */
public class HelpCenterListviewAdapter extends BaseAdapter {
    private ArrayList<Map<String,String>> listDate;
    private Context context;
    private LayoutInflater layoutInflater = null;

    public HelpCenterListviewAdapter(Context context,ArrayList<Map<String,String>> listDate) {
        this.context = context;
        this.listDate = listDate;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listDate.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_help_center_listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tvHelpCenterTitle.setText(listDate.get(position).get("title"));
        viewHolder.tvHelpCenterText.setText(listDate.get(position).get("text"));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_help_center_title)
        TextView tvHelpCenterTitle;
        @BindView(R.id.tv_help_center_text)
        TextView tvHelpCenterText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
