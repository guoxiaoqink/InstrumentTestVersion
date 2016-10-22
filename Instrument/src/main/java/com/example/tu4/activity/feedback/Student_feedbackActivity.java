package com.example.tu4.activity.feedback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.adapter.FeedbackListviewAdapter;
import com.example.tu4.adapter.FeedbackListviewAdapter_1;
import com.example.tu4.adapter.FeedbackListviewAdapter_2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WQJ on 2016/10/19
 * Descripyion: 这个是学员反馈页面
 * Version: 1
 * Modify Person : wqj
 */
public class Student_feedbackActivity extends AppCompatActivity {
    @BindView(R.id.list_stu_feedback_details)
    ListView stuFeedbackDetails;
    @BindView(R.id.btn_course_return)
    ImageButton courseReturn;
    @BindView(R.id.btn_pre_course)
    ImageButton preCourse;
    @BindView(R.id.tv_course_time_x)
    TextView courseTimeX;
    @BindView(R.id.btn_next_course)
    ImageButton nextCourse;
    private int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_student_feedback);
        ButterKnife.bind(this);
        courseTimeX.setText("课时1");
        if (courseTimeX.getText().toString().equals("课时1")) {
            preCourse.setImageResource(R.mipmap.stu_feedback_pre_noclick);
            preCourse.setClickable(false);
            FeedbackListviewAdapter feedbackListviewAdapter = new FeedbackListviewAdapter(this);
            stuFeedbackDetails.setAdapter(feedbackListviewAdapter);
        }

    }

    @OnClick({R.id.btn_course_return, R.id.btn_pre_course, R.id.tv_course_time_x, R.id.btn_next_course})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_course_return:
                this.finish();
                break;
            case R.id.btn_pre_course:
                a = a - 1;
                courseTimeX.setText("课时" + a);
                nextCourse.setImageResource(R.mipmap.ic_feedback_right);
                nextCourse.setClickable(true);
                FeedbackListviewAdapter_1 feedbackListviewAdapter_1 = new FeedbackListviewAdapter_1(this);
                stuFeedbackDetails.setAdapter(feedbackListviewAdapter_1);
                if (courseTimeX.getText().toString().equals("课时1")) {
                    FeedbackListviewAdapter feedbackListviewAdapter = new FeedbackListviewAdapter(this);
                    stuFeedbackDetails.setAdapter(feedbackListviewAdapter);
                    preCourse.setImageResource(R.mipmap.stu_feedback_pre_noclick);
                    preCourse.setClickable(false);
                }
                break;
            case R.id.btn_next_course:
                a = a + 1;
                courseTimeX.setText("课时" + a);
                preCourse.setImageResource(R.mipmap.ic_feedback_left);
                preCourse.setClickable(true);
                FeedbackListviewAdapter_1 feedbackListviewAdapter = new FeedbackListviewAdapter_1(this);
                stuFeedbackDetails.setAdapter(feedbackListviewAdapter);
                if (courseTimeX.getText().toString().equals("课时3")) {
                    FeedbackListviewAdapter_2 feedbackListviewAdapter_2 = new FeedbackListviewAdapter_2(this);
                    stuFeedbackDetails.setAdapter(feedbackListviewAdapter_2);
                    nextCourse.setImageResource(R.mipmap.stu_feedback_next_noclick);
                    nextCourse.setClickable(false);
                }
                break;
        }
    }
}
