package com.example.tu4.activity.course;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

import com.example.tu4.R;
import com.example.tu4.adapter.StudentListGridviewAdapter;
import com.example.tu4.view.TitleView;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hs on
 * Descripyion: 学员列表界面
 * Version：1
 * Modify Person：gxq
 */
public class StudentListActivity extends AppCompatActivity {

    @BindView(R.id.kcxq_title_view)
    TitleView kcxqTitleView;
    private ArrayList<Map<String, String>> listData;
    private Map<String, String> mapData;

    @BindView(R.id.gv_student_list)
    GridView gvStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_student_list);
        ButterKnife.bind(this);

        setTitleBar();

        getData();

        gvStudentList.setAdapter(new StudentListGridviewAdapter(this, listData));

    }

    /**
     *状态栏
     */
    private void setTitleBar() {
        String title = "学员列表（12）".toString();
        Resources res = getResources();
        kcxqTitleView.setTitleText(title);
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        kcxqTitleView.setImgLeft(ic_return);
        kcxqTitleView.getImgLeft().setVisibility(View.VISIBLE);
        kcxqTitleView.getImgLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentListActivity.this.finish();
            }
        });
    }

    /**
     * 获取网络数据
     */
    private void getData() {

    }
}
