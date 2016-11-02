package com.example.tu4.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.course.feedback.IssiuFeedbackActivity;
import com.example.tu4.bean.ClassDetailsPost;
import com.example.tu4.bean.SubjectDetails;
import com.example.tu4.bean.SubjectInfo;
import com.example.tu4.view.CircleImageView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.model.AplicationStatic.MAX_STUDENT_NUMBER;
import static com.example.tu4.model.AplicationStatic.MAX_STUDENT_NUMBER_BACK;
import static com.example.tu4.model.AplicationStatic.STUDENT_NUMBER;
import static com.example.tu4.model.IUrl.baseUrl;

/**
 * Created by gxq on
 * Descripyion:课程详情界面
 * Version：1
 * Modify Person：gxq
 */
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
    @BindView(R.id.tv_control_num)
    TextView tvControlNum;
    @BindView(R.id.tv_sub_time)
    TextView tvSubTime;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_notes)
    TextView tvNotes;
    @BindView(R.id.tv_class_teacher)
    TextView tvClassTeacher;
    @BindView(R.id.tv_teacher_tel)
    TextView tvTeacherTel;
    @BindView(R.id.tv_money_subjectdetail)
    TextView tvMoneySubjectdetail;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_numof_feedback)
    TextView tvNumofFeedback;
    private TextView money;
    private int class_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        ButterKnife.bind(this);
        mInflater = LayoutInflater.from(this);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout_studenimage_subjectdetail);
        mLinearLayoutFeedback = (LinearLayout) findViewById(
                R.id.linearlayout_studentback_subjectdetail);


//        Intent intent = getIntent();
//        class_id = Integer.parseInt(intent.getStringExtra("class_id"));
        getDataByUrl();
        initLinearlayoutImage();
        initLinearlayouFeedback();
    }

    private void initLinearlayouFeedback() {

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

    @OnClick({R.id.iv_topbar_arrow, R.id.imageview_instrument_show, R.id.tv_money_subjectdetail, R.id.tv_money})
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
            case R.id.tv_money_subjectdetail:
                Intent intent = new Intent();
                intent.putExtra("class_name", tvCourseName.getText());
                intent.putExtra("class_level", tvCourseLevel.getText());
                intent.putExtra("class_teacher", tvClassTeacher.getText());
                intent.putExtra("class_location", tvLocation.getText());
                intent.putExtra("money", tvMoneySubjectdetail.getText());
                intent.setClass(SubjectDetailActivity.this, CourseSubscribeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_money:
                Intent intent1 = new Intent();
                intent1.putExtra("class_name", tvCourseName.getText());
                intent1.putExtra("class_level", tvCourseLevel.getText());
                intent1.putExtra("class_teacher", tvClassTeacher.getText());
                intent1.putExtra("class_location", tvLocation.getText());
                intent1.putExtra("money", tvMoneySubjectdetail.getText());
                intent1.setClass(SubjectDetailActivity.this, CourseSubscribeActivity.class);
                startActivity(intent1);
                break;
        }
    }

    //从网上获取列表内容并显示在当前页面中
    public void getDataByUrl() {
        String url = baseUrl + "/music/api_classdetail";
        OkHttpUtils
                .postString()
                .url(url)//
                .content(new Gson().toJson(new ClassDetailsPost("1", "1003")))
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(SubjectDetailActivity.this, "内容获取失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.d("success", response);
//                        System.out.println(response);
                        try {
                            Gson gson = new Gson();
                            SubjectDetails subjectDetails = gson.fromJson(response, SubjectDetails.class);
                            List<SubjectInfo> subjectInfos = new ArrayList<SubjectInfo>();
                            subjectInfos = subjectDetails.getTeacher();
                            tvCourseName.setText(subjectDetails.getClass_name().toString());
                            tvCourseLevel.setText("等级：" + subjectDetails.getClass_level().toString());
                            tvClassTeacher.setText(subjectInfos.get(0).getTeacher_name());
                            tvTeacherTel.setText(subjectInfos.get(0).getTeacher_telephone());
                            textviewStudentnumberSubjectdetail.setText("共" + subjectInfos.get(0).getStudent_number() + "名学员");
                            int price = subjectDetails.getClass_price();
                            tvMoneySubjectdetail.setText(String.valueOf(price) + ".00");
                            tvControlNum.setText("编号：" + String.valueOf(subjectDetails.getClass_id()));
                            tvSubTime.setText("课时：" + String.valueOf(subjectDetails.getClass_number()));
                            tvNotes.setText("备注：" + subjectDetails.getClass_remark().toString());
                            tvLocation.setText("地点：" + subjectDetails.getClass_location().toString());
                            tvNumofFeedback.setText(subjectDetails.getFeedback_number()+"条学生反馈");
                            addHeadImgToLinearlayout(subjectDetails.getFeedback_number(), MAX_STUDENT_NUMBER_BACK,
                                    R.layout.subject_detati_studentback_linearlayout_item, mLinearLayoutFeedback);
//                            System.out.println(subjectInfos.get(0).getTeacher_telephone());
                            JSONObject jsonObject = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
