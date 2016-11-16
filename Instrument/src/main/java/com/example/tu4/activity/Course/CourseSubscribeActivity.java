package com.example.tu4.activity.course;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.course.payment.OrderPaymentActivity;
import com.example.tu4.view.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tu4.utils.ApplicationStaticConstants.account;

/**
 * Created by WQJ on 2016/10/20
 * Descripyion: 这个是课程订购页面
 * Version: 1
 * Modify Person : wqj
 */
public class CourseSubscribeActivity extends AppCompatActivity {
    @BindView(R.id.tv_account_details)
    TextView accountDetails;
    @BindView(R.id.et_account_name)
    EditText accountName;
    @BindView(R.id.et_account_tel)
    EditText accountTel;
    @BindView(R.id.tv_total_money)
    TextView totalMoney;
    @BindView(R.id.tv_suject_name)
    TextView tvSujectName;
    @BindView(R.id.tv_suject_level)
    TextView tvSujectLevel;
    @BindView(R.id.tv_suject_teacher)
    TextView tvSujectTeacher;
    @BindView(R.id.tv_suject_location)
    TextView tvSujectLocation;
    @BindView(R.id.tv_course_money)
    TextView tvCourseMoney;
    @BindView(R.id.course_order_title)
    TitleView courseOrderTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_course_subscribe);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences =getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        String UserName = sharedPreferences.getString("UserName", "0");
        accountDetails.setText(UserName);
        accountName.setFocusable(true);
        initview();
    }

    public void initview() {
        String titile = "课程订购";
        Intent intent = getIntent();
        String class_name = intent.getStringExtra("class_name");
        String class_tea = intent.getStringExtra("class_teacher");
        String class_level = intent.getStringExtra("class_level");
        String class_local = intent.getStringExtra("class_location");
        String class_price = intent.getStringExtra("money");
        tvSujectName.setText(class_name);
        tvSujectLevel.setText(class_level);
        tvSujectLocation.setText(class_local);
        tvSujectTeacher.setText("老师：" + class_tea);
        tvCourseMoney.setText(class_price);
        totalMoney.setText(tvCourseMoney.getText());
        courseOrderTitle.setTitleText(titile);
        courseOrderTitle.getImgLeft().setVisibility(View.VISIBLE);
        Resources res = getResources();
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        courseOrderTitle.setImgLeft(ic_return);
        courseOrderTitle.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseSubscribeActivity.this.finish();
            }
        });

    }

//    //返回按钮点击事件
//    public void course_order_return(View v) {
//        this.finish();
//    }

    //确认支付按钮点击事件
    public void ensure_order(View v) {
        if (accountTel.length() == 0 || accountName.length() == 0) {
            Toast.makeText(this, "姓名和手机号码不能为空", Toast.LENGTH_SHORT).show();
        } else if (accountTel.length() != 11) {
            Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
        } else {
            //页面之间数据的传递
            Intent intent = new Intent();
            intent.putExtra("account", account);
            intent.putExtra("TrueNmae", accountName.getText().toString().trim() + "  " + accountTel.getText().toString().trim());
            intent.putExtra("TotalMoney", totalMoney.getText().toString().trim());
            intent.putExtra("tel",accountTel.getText().toString().trim());
            intent.putExtra("name", tvSujectName.getText());
            intent.putExtra("Name",accountName.getText().toString().trim());
            intent.setClass(CourseSubscribeActivity.this, OrderPaymentActivity.class);
            startActivity(intent);
        }
    }


}
