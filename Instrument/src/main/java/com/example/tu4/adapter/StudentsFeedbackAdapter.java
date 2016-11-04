package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.tu4.R;
import com.example.tu4.bean.StudentsFeedBackEntity;
import com.example.tu4.view.CircleImageView;

import java.util.List;

/**
 * Created by MR.WEN on 2016/11/4.
 */

public class StudentsFeedbackAdapter extends BaseAdapter {
    private List<StudentsFeedBackEntity.StudentsFeedBackListEntity> mList;
    private Context mContext;

    public StudentsFeedbackAdapter(Context context, List<StudentsFeedBackEntity.StudentsFeedBackListEntity> list) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder_StudentsFeedBack holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.stu_feedback_details_list_item, null);
            holder = new ViewHolder_StudentsFeedBack();
            holder.student_civ = (CircleImageView) convertView.findViewById(R.id.circleImageView);
            holder.student_name = (TextView) convertView.findViewById(R.id.tv_stu_name);
            holder.feedback_time = (TextView) convertView.findViewById(R.id.tv_feedback_time);
            holder.feedback_context = (TextView) convertView.findViewById(R.id.tv_stu_feedback_details_content);
            holder.feedback_video = (VideoView) convertView.findViewById(R.id.feedback_video);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder_StudentsFeedBack) convertView.getTag();
        }
        holder.feedback_context.setText(mList.get(position).getContent());
        holder.feedback_time.setText(mList.get(position).getDate());
        holder.student_name.setText(mList.get(position).getName());
        return convertView;

    }
}

final class ViewHolder_StudentsFeedBack {
    CircleImageView student_civ;
    TextView student_name;
    TextView feedback_time;
    TextView feedback_context;
    VideoView feedback_video;
}

