package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.tu4.R;

/**
 * Created by MR.WEN on 2016/10/14.
 * 这是支付成功页面
 */
public class PaySuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_pay_success);
        Toast.makeText(this, "ceshi", Toast.LENGTH_SHORT).show();
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
