package com.example.tu4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.CourseInfoListviewAdapter;

public class CourseSubscribeActivity extends AppCompatActivity {
    private ListView course_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_course_subscribe);
        course_info = (ListView) findViewById(R.id.course_info_list);
        initListviewCourseinfo();
    }

    //    初始化课程信息列表方法
    public void initListviewCourseinfo() {
        CourseInfoListviewAdapter courseInfoListviewAdapter = new CourseInfoListviewAdapter(
                CourseSubscribeActivity.this);
        course_info.setAdapter(courseInfoListviewAdapter);

    }
}
