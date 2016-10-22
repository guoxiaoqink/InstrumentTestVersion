package com.example.tu4.activity.Course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.activity.feedback.IssiuFeedbackActivity;
import com.example.tu4.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tu4.model.AplicationStatic.MAX_STUDENT_NUMBER;
import static com.example.tu4.model.AplicationStatic.MAX_STUDENT_NUMBER_BACK;
import static com.example.tu4.model.AplicationStatic.STUDENT_NUMBER;

public class SubjectDetailActivity extends AppCompatActivity {

    LinearLayout mLinearLayout, mLinearLayoutFeedback;
    LayoutInflater mInflater = null;
    @BindView(R.id.imageview_instrument_show)
    ImageView imageviewInstrumentShow;
    @BindView(R.id.iv_topbar_arrow)
    ImageView ivTopbarArrow;

    @BindView(R.id.tv_course_name)
    TextView tvCourseName;
    @BindView(R.id.tv_course_level)
    TextView tvCourseLevel;
    @BindView(R.id.tv_money_subjectdetail)
    TextView tvMoneySubjectdetail;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.person_image)
    CircleImageView personImage;
    @BindView(R.id.textview_studentnumber_subjectdetail)
    TextView textviewStudentnumberSubjectdetail;
    @BindView(R.id.line_studentnumber)
    TextView lineStudentnumber;
    @BindView(R.id.linearlayout_studenimage_subjectdetail)
    LinearLayout linearlayoutStudenimageSubjectdetail;
    @BindView(R.id.jiantou)
    ImageView jiantou;
    @BindView(R.id.subject)
    TextView subject;
    @BindView(R.id.line_subject)
    TextView lineSubject;
    @BindView(R.id.textview_cintent)
    TextView textviewCintent;
    @BindView(R.id.subject1)
    TextView subject1;
    @BindView(R.id.line_subject1)
    TextView lineSubject1;
    @BindView(R.id.textview_cintent1)
    TextView textviewCintent1;
    @BindView(R.id.linearlayout_studentback_subjectdetail)
    LinearLayout linearlayoutStudentbackSubjectdetail;
    @BindView(R.id.image_jiantoufankui)
    ImageView imageJiantoufankui;
    private TextView money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        ButterKnife.bind(this);
        money = (TextView) findViewById(R.id.tv_money_subjectdetail);
        mInflater = LayoutInflater.from(this);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout_studenimage_subjectdetail);
        mLinearLayoutFeedback = (LinearLayout) findViewById(
                R.id.linearlayout_studentback_subjectdetail);

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SubjectDetailActivity.this, CourseSubscribeActivity.class);
                startActivity(intent);
            }
        });
        initLinearlayoutImage();
        initLinearlayouFeedback();
    }

    private void initLinearlayouFeedback() {
        addHeadImgToLinearlayout(STUDENT_NUMBER, MAX_STUDENT_NUMBER_BACK,
                R.layout.subject_detati_studentback_linearlayout_item, mLinearLayoutFeedback);
    }

    public void initLinearlayoutImage() {
        addHeadImgToLinearlayout(STUDENT_NUMBER, MAX_STUDENT_NUMBER,
                R.layout.subject_detati_studentimage_linearlayout_item, mLinearLayout);
    }

    public void addHeadImgToLinearlayout(int studentNumber, int MaxNumber, int itemlayout,
                                         LinearLayout linearLayout) {
        if (studentNumber <= MaxNumber) {
            for (int i = 0; i < studentNumber; i++) {
                View v = mInflater.inflate(itemlayout, null);
                linearLayout.addView(v);
            }
        } else {
            for (int i = 0; i < MaxNumber; i++) {
                View v = mInflater.inflate(itemlayout, null);
                if (i == MaxNumber - 1) {
                    CircleImageView civ = (CircleImageView) v.findViewById(R.id.person_image);
                    civ.setImageResource(R.mipmap.subjectdetail);
                }
                linearLayout.addView(v);
            }
        }
    }

    @OnClick({R.id.iv_topbar_arrow, R.id.imageview_instrument_show})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_topbar_arrow:
                this.finish();
                break;
            case R.id.imageview_instrument_show:
                Intent intentTofeedback = new Intent();
                intentTofeedback.setClass(SubjectDetailActivity.this, IssiuFeedbackActivity.class);
                startActivity(intentTofeedback);
                break;
        }
    }



/*
    @OnClick(R.id.imageview_instrument_show)
    public void onClick() {
        Intent intentTofeedback = new Intent();
        intentTofeedback.setClass(SubjectDetailActivity.this, Student_feedbackActivity.class);
        startActivity(intentTofeedback);
    }

    @OnClick(R.id.iv_topbar_arrow)
    public void onClick() {
    }*/
}
