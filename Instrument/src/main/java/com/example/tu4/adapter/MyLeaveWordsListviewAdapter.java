package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.view.ResolveConflictsScoolviewListview;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/16.
 */

public class MyLeaveWordsListviewAdapter extends BaseAdapter {
    private boolean isShow = false;
    private MyLeaveWordsCommentListviewAdapter myLeaveWordsCommentListviewAdapter;
    private int[] leaveWordsPhoto, leaveWordsCommentPhoto;
    private String[] leaveWordsName, leaveWordsContext, leaveWordsTime;
    private String[] leaveWordsCommentName, leaveWordsCommentContext, leaveWordsCommentTime;
    private Context c;
    private LayoutInflater layoutInflater;

    public MyLeaveWordsListviewAdapter(Context context, int[] leaveWordsPhoto, String[]
            leaveWordsName, String[] leaveWordsContext, String[] leaveWordsTime, int[]
                                               leaveWordsCommentPhoto, String[]
                                               leaveWordsCommentName, String[]
                                               leaveWordsCommentContext, String[]
                                               leaveWordsCommentTime) {
        this.c = context;
        this.leaveWordsPhoto = leaveWordsPhoto;
        this.leaveWordsName = leaveWordsName;
        this.leaveWordsContext = leaveWordsContext;
        this.leaveWordsTime = leaveWordsTime;

        this.leaveWordsCommentPhoto = leaveWordsCommentPhoto;
        this.leaveWordsCommentName = leaveWordsCommentName;
        this.leaveWordsCommentContext = leaveWordsCommentContext;
        this.leaveWordsCommentTime = leaveWordsCommentTime;

        this.layoutInflater = LayoutInflater.from(c);

    }

    @Override
    public int getCount() {
        return leaveWordsName.length;
    }

    @Override
    public Object getItem(int position) {
        return leaveWordsName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int positio, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_my_leave_words_lv_item, null);
            viewHolder = new ViewHolder(convertView);
            if (positio == 1) {
                viewHolder.flLeaveWordStudVideo.setVisibility(View.VISIBLE);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imgLeaveWordStudPhoto.setImageResource(leaveWordsPhoto[positio]);
        viewHolder.tvLeaveWordStudName.setText(leaveWordsName[positio]);
        viewHolder.tvLeaveWordmStudContext.setText(leaveWordsContext[positio]);
        viewHolder.tvLeaveWordStudTime.setText(leaveWordsTime[positio]);
        viewHolder.imgLeaveWordStudVideo.setImageResource(R.mipmap.a);

        myLeaveWordsCommentListviewAdapter = new MyLeaveWordsCommentListviewAdapter(c,
                leaveWordsCommentPhoto,
                leaveWordsCommentName, leaveWordsCommentContext, leaveWordsCommentTime);

        viewHolder.lvMyLeaveWordsComment.setAdapter(myLeaveWordsCommentListviewAdapter);

        viewHolder.llLeaveWordStueMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow == false) {
                    viewHolder.lvMyLeaveWordsComment.setVisibility(View.VISIBLE);
                    viewHolder.tvLeaveWordStudMore.setText("收起");
                    viewHolder.imgLeaveWordStudMore.setImageResource(R.mipmap.ic_arrow_top_blue);
                    isShow = true;
                } else {
                    viewHolder.lvMyLeaveWordsComment.setVisibility(View.GONE);
                    viewHolder.tvLeaveWordStudMore.setText("更多");
                    viewHolder.imgLeaveWordStudMore.setImageResource(R.mipmap.ic_arrow_bottom_blue);
                    isShow = false;
                }
//                viewHolder.lvMyLeaveWordsComment.setVisibility(View.VISIBLE);
//                viewHolder.llLeaveWordStueMore.setVisibility(View.GONE);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img_leave_word_stud_photo)
        ImageView imgLeaveWordStudPhoto;
        @BindView(R.id.tv_leave_word_stud_name)
        TextView tvLeaveWordStudName;
        @BindView(R.id.tv_leave_wordm_stud_context)
        TextView tvLeaveWordmStudContext;
        @BindView(R.id.tv_leave_word_stud_time)
        TextView tvLeaveWordStudTime;
        @BindView(R.id.img_leave_word_stud_video)
        ImageView imgLeaveWordStudVideo;

        @BindView(R.id.lv_my_leave_words_comment)
        ResolveConflictsScoolviewListview lvMyLeaveWordsComment;

        @BindView(R.id.ll_leave_word_stue_more)
        LinearLayout llLeaveWordStueMore;

        @BindView(R.id.tv_leave_word_stud_more)
        TextView tvLeaveWordStudMore;
        @BindView(R.id.img_leave_word_stud_more)
        ImageView imgLeaveWordStudMore;

        @BindView(R.id.fl_leave_word_stud_video)
        FrameLayout flLeaveWordStudVideo;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
