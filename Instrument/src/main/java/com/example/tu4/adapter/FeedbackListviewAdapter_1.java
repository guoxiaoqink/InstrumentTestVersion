package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.VideoView;

import com.example.tu4.R;
import com.example.tu4.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WQJ on 2016/10/16.
 */

public class FeedbackListviewAdapter_1 extends BaseAdapter {
    private LayoutInflater mInflater = null;

    public FeedbackListviewAdapter_1(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.stu_feedback_details_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.circleImageView.setImageResource(R.mipmap.circle);
        if (position != 1) {
            viewHolder.feedbackVideo.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.circleImageView)
        CircleImageView circleImageView;
        @BindView(R.id.feedback_video)
        VideoView feedbackVideo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}