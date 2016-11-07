package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.tu4.R;
import com.example.tu4.bean.FeedbackInfo;
import com.example.tu4.view.CircleImageView;

import java.util.List;

/**
 * Created by WQJ on 2016/10/16.
 */

public class FeedbackListviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    // 要显示的数据的集合
    private List<List<FeedbackInfo>> data;

    public FeedbackListviewAdapter(Context context, List<List<FeedbackInfo>> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.stu_feedback_details_list_item, null);
            viewHolder.stu_name = (TextView) convertView.findViewById(R.id.tv_stu_name);
            viewHolder.circleImageView = (CircleImageView) convertView.findViewById(R.id.circleImageView);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_stu_feedback_details_content);
            viewHolder.time = (TextView) convertView.findViewById(R.id.tv_feedback_time);
            viewHolder.videoView = (VideoView) convertView.findViewById(R.id.feedback_video);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        List<FeedbackInfo> list = data.get(position);
//        list.clear();
        String name = list.get(position).getName();
        String time = list.get(position).getDate();
        String content =list.get(position).getContent();
        viewHolder.circleImageView.setImageResource(R.mipmap.circle);
        viewHolder.stu_name.setText(name);
        viewHolder.time.setText(time);
        viewHolder.content.setText(content);
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.stu_feedback_details_list_item, null);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.circleImageView.setImageResource(R.mipmap.circle);
//        if (position != 0) {
//            viewHolder.feedbackVideo.setVisibility(View.GONE);
//        }

        return convertView;
    }


    static class ViewHolder {
        private CircleImageView circleImageView;
        private TextView stu_name;
        private TextView time;
        private TextView content;
        private VideoView videoView;

    }

}