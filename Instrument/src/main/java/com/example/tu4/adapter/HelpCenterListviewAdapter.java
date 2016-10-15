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
 * 帮助中心
 */
public class HelpCenterListviewAdapter extends BaseAdapter {
    private String[] title, text;
    private Context context;
    private LayoutInflater layoutInflater = null;

    public HelpCenterListviewAdapter(Context context, String[] title, String[] text) {
        this.context = context;
        this.text = text;
        this.title = title;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return title[position];
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

        viewHolder.tvHelpCenterTitle.setText(title[position]);
        viewHolder.tvHelpCenterText.setText(text[position]);

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
