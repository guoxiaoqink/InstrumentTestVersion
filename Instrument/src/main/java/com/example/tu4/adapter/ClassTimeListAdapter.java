package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.bean.ClassCouInfo;

import java.util.List;

/**
 * Created by MR.WEN on 2016/11/4.
 */

public class ClassTimeListAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    // 要显示的数据的集合
    private List<List<ClassCouInfo>> data;

    public ClassTimeListAdapter(Context context, List<List<ClassCouInfo>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.class_time_list_item, null);
            viewHolder.class_num = (TextView) convertView.findViewById(R.id.subject);
            viewHolder.type = (TextView) convertView.findViewById(R.id.class_type);
            viewHolder.class_time_date = (TextView) convertView.findViewById(R.id.class_time_date);
            viewHolder.class_time_time = (TextView) convertView.findViewById(R.id.class_time_time);
            viewHolder.content = (TextView) convertView.findViewById(R.id.class_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        List<ClassCouInfo> list = data.get(position);
        String class_num = list.get(position).getNum();
        String type = list.get(position).getType();
        String class_date = list.get(position).getDate();
        String class_time = list.get(position).getTime();
        String content = list.get(position).getContent();
        viewHolder.class_num.setText("课时" + class_num);
        if (type.equals("true")) {
            viewHolder.type.setText("(已完成)");
        } else {
            viewHolder.type.setText("未完成");
        }
        viewHolder.class_time_time.setText(class_time);
        viewHolder.class_time_date.setText(class_date);
        viewHolder.content.setText(content);

        return convertView;
    }

    class ViewHolder {
        private TextView class_num;
        private TextView type;
        private TextView class_time_date;
        private TextView content;
        private TextView class_time_time;
    }
}
