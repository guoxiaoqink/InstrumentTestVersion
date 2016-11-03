package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.view.CircleImageView;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/2.
 */

public class StudentListGridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Map<String, String>> listData;

    public StudentListGridviewAdapter(Context context, ArrayList<Map<String, String>> listData) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listData = listData;

    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_student_list_gv_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img_student_list_item_photo)
        CircleImageView imgStudentListItemPhoto;
        @BindView(R.id.tv_student_list_item_name)
        TextView tvStudentListItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
