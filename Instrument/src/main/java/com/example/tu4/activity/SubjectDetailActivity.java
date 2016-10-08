package com.example.tu4.activity;

import static com.example.tu4.temporarydata.AplicationStatic.MAX_STUDENT_NUMBER;
import static com.example.tu4.temporarydata.AplicationStatic.MAX_STUDENT_NUMBER_BACK;
import static com.example.tu4.temporarydata.AplicationStatic.STUDENT_NUMBER;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tu4.R;
import com.example.tu4.view.CircleImageView;

public class SubjectDetailActivity extends AppCompatActivity {


    LinearLayout mLinearLayout, mLinearLayoutFeedback;
    LayoutInflater mInflater = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        mInflater = LayoutInflater.from(this);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout_studenimage_subjectdetail);
        mLinearLayoutFeedback = (LinearLayout) findViewById(R.id.linearlayout_studentback_subjectdetail);
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

    public void addHeadImgToLinearlayout(int studentNumber, int MaxNumber, int itemlayout, LinearLayout linearLayout) {
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

}
