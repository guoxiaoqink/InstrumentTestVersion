package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.CourseInfoListviewAdapter;
/**
 * Created by MR.WEN on 2016/10/13.
 */

/**
 * 课程订购页面
 */
public class CourseSubscribeActivity extends AppCompatActivity {
    private ListView course_info;
    private ImageButton return_SubDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_course_subscribe);
        course_info = (ListView) findViewById(R.id.course_info_list);
        return_SubDA = (ImageButton) findViewById(R.id.course_return);
        initListviewCourseinfo();


    }

    //    初始化课程信息列表方法
    public void initListviewCourseinfo() {
        CourseInfoListviewAdapter courseInfoListviewAdapter = new CourseInfoListviewAdapter(
                CourseSubscribeActivity.this);
        course_info.setAdapter(courseInfoListviewAdapter);

    }

    //返回按钮点击事件
    public void course_order_return(View v) {
        Intent intent = new Intent();
        intent.setClass(CourseSubscribeActivity.this, SubjectDetailActivity.class);
        startActivity(intent);
    }

    //确认支付按钮点击事件
    public void ensure_order(View v) {
        Intent intent = new Intent();
        intent.setClass(CourseSubscribeActivity.this, OrderPaymentActivity.class);
        startActivity(intent);
    }


}
