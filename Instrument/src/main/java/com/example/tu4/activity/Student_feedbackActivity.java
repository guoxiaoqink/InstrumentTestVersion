package com.example.tu4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.FeedbackListviewAdapter;
import com.example.tu4.adapter.FeedbackListviewAdapter_1;
import com.example.tu4.adapter.FeedbackListviewAdapter_2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 学员反馈详情页面
 *
 * @auther WQJ
 * created at 2016/10/16 9:49
 */
public class Student_feedbackActivity extends AppCompatActivity {
    @BindView(R.id.stu_feedback_details)
    ListView stuFeedbackDetails;
    @BindView(R.id.course_return)
    ImageButton courseReturn;
    @BindView(R.id.pre_course)
    ImageButton preCourse;
    @BindView(R.id.course_time_x)
    TextView courseTimeX;
    @BindView(R.id.next_course)
    ImageButton nextCourse;
    private int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);
        ButterKnife.bind(this);
        courseTimeX.setText("课时1");
        if (courseTimeX.getText().toString().equals("课时1")) {
            preCourse.setImageResource(R.mipmap.stu_feedback_pre_noclick);
            preCourse.setClickable(false);
            FeedbackListviewAdapter feedbackListviewAdapter = new FeedbackListviewAdapter(this);
            stuFeedbackDetails.setAdapter(feedbackListviewAdapter);
            Toast.makeText(this, "为什么无法上传啊", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "为什么无法上传啊", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.course_return, R.id.pre_course, R.id.course_time_x, R.id.next_course})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.course_return:
                break;
            case R.id.pre_course:
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
            case R.id.next_course:
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
