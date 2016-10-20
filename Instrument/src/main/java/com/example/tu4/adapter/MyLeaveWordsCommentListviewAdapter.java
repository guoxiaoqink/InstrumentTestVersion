package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/16.
 */

public class MyLeaveWordsCommentListviewAdapter extends BaseAdapter {
    private int[] leaveWordsCommentPhoto;
    private String[] leaveWordsCommentName, leaveWordsCommentContext, leaveWordsCommentTime;
    private Context mcontext;
    private LayoutInflater layoutInflater;

    public MyLeaveWordsCommentListviewAdapter(Context context, int[] leaveWordsCommentPhoto,
                                              String[] leaveWordsCommentName, String[]
                                                      leaveWordsCommentContext, String[]
                                                      leaveWordsCommentTime) {
        this.mcontext = context;

        this.leaveWordsCommentPhoto = leaveWordsCommentPhoto;
        this.leaveWordsCommentName = leaveWordsCommentName;
        this.leaveWordsCommentContext = leaveWordsCommentContext;
        this.leaveWordsCommentTime = leaveWordsCommentTime;
        this.layoutInflater = LayoutInflater.from(mcontext);

    }

    @Override
    public int getCount() {
        return leaveWordsCommentName.length;
    }

    @Override
    public Object getItem(int position) {
        return leaveWordsCommentName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout
                    .activity_my_leave_words_comment_lv_item, null);
            viewHolder = new ViewHolder(convertView);
//            if (position == leaveWordsCommentName.length-1){
//                viewHolder.llLeaveWordCommentMore.setVisibility(View.VISIBLE);
//            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imgLeaveWordCommentPhoto.setImageResource(leaveWordsCommentPhoto[position]);
        viewHolder.tvLeaveWordCommentName.setText(leaveWordsCommentName[position]);
        viewHolder.tvLeaveWordmCommentContext.setText(leaveWordsCommentContext[position]);
        viewHolder.tvLeaveWordCommentTime.setText(leaveWordsCommentTime[position]);
        viewHolder.imgLeaveWordCommentVideo.setImageResource(R.mipmap.a);

//        viewHolder.llLeaveWordCommentMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View view = layoutInflater.inflate(R.layout.activity_my_leave_words_lv_item,null);
//                ListView lvMyComment = (ListView)view.findViewById(R.id.lv_my_leave_words_comment);
//                lvMyComment.setVisibility(View.GONE);
//                LinearLayout llMore = (LinearLayout)view.findViewById(R.id.ll_leave_word_stue_more);
//                llMore.setVisibility(View.VISIBLE);
//            }
//        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img_leave_word_comment_photo)
        ImageView imgLeaveWordCommentPhoto;
        @BindView(R.id.tv_leave_word_comment_name)
        TextView tvLeaveWordCommentName;
        @BindView(R.id.tv_leave_wordm_comment_context)
        TextView tvLeaveWordmCommentContext;
        @BindView(R.id.tv_leave_word_comment_time)
        TextView tvLeaveWordCommentTime;
        @BindView(R.id.img_leave_word_comment_video)
        ImageView imgLeaveWordCommentVideo;

        @BindView(R.id.ll_leave_word_comment_more)
        LinearLayout llLeaveWordCommentMore;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
