package com.example.tu4.activity.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.tu4.R;
import com.example.tu4.activity.Course.SubjectDetailActivity;
import com.example.tu4.activity.MainActivity;

/**
 * Created by WQJ on 2016/10/20
 * Descripyion: 支付成功页面
 * Version: 1
 * Modify Person : wqj
 */
public class PaySuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_pay_success);
    }

    public void show_course_details(View v) {
        Intent intent = new Intent();
        intent.setClass(PaySuccessActivity.this, SubjectDetailActivity.class);
        startActivity(intent);
    }

    public void return_course(View v) {
        Intent intentToCourse = new Intent();
        intentToCourse.setClass(PaySuccessActivity.this, MainActivity.class);
        startActivity(intentToCourse);
    }

    public void success_return(View v) {
        this.finish();
    }
}
