package com.example.tu4.activity.personal;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.adapter.MyLeaveWordsListviewAdapter;
import com.example.tu4.view.TitleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tu4.utils.ApplicationStaticConstants.JUMP_MAINACTIVITY;

public class MyLeaveWordsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.lv_my_leave_words)
    ListView lvMyLeaveWords;
    @BindView(R.id.rl_system_information)
    RelativeLayout rlSystemInformation;
    //    @BindView(R.id.img_my_leave_words_return)
//    ImageView imgMyLeaveWordsReturn;
    @BindView(R.id.tv_sys_infor_num)
    TextView tvSysInforNum;
    @BindView(R.id.tv_sys_infor_context)
    TextView tvSysInforContext;
    @BindView(R.id.tv_sys_infor_time)
    TextView tvSysInforTime;
    @BindView(R.id.leave_word_title)
    TitleView leaveWordTitle;
    private MyLeaveWordsListviewAdapter myLeaveWordsListviewAdapter;

    private int[] leaveWordsPhoto = new int[]{R.mipmap.a, R.mipmap.a, R.mipmap.a,};
    private String[] leaveWordsName = new String[]{"学员xxx", "学员xxx", "学员xxx",};
    private String[] leaveWordsContext = new String[]{"内容xxxxxxxxx", "内容xxxxxxxxx", "内容xxxxxxxxx",};
    private String[] leaveWordsTime = new String[]{"2016-05-05", "2016-05-05", "2016-05-05",};

    private int[] leaveWordsCommentPhoto = new int[]{R.mipmap.a, R.mipmap.a, R.mipmap.a,};
    private String[] leaveWordsCommentName = new String[]{"自己xxx", "自己xxx", "自己xxx",};
    private String[] leaveWordsCommentContext = new String[]{"内容xxxxxxxxx", "内容xxxxxxxxx",
            "内容xxxxxxxxx",};
    private String[] leaveWordsCommentTime = new String[]{"2016-05-05", "2016-05-05",
            "2016-05-05",};

    private ArrayList<String> systemInforTime;
    private String systemInforContext = "内容内容内容";
    private ArrayList<String> systemInforNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_my_leave_words);
        ButterKnife.bind(this);
        initInformationDate();
        tvSysInforTime.setText(systemInforTime.get(6));
        tvSysInforNum.setText(systemInforNumber.get(11));
        tvSysInforContext.setText(systemInforContext);


        myLeaveWordsListviewAdapter = new MyLeaveWordsListviewAdapter(this, leaveWordsPhoto,
                leaveWordsName, leaveWordsContext, leaveWordsTime, leaveWordsCommentPhoto,
                leaveWordsCommentName, leaveWordsCommentContext, leaveWordsCommentTime);
        lvMyLeaveWords.setAdapter(myLeaveWordsListviewAdapter);

//        imgMyLeaveWordsReturn.setOnClickListener(this);
        rlSystemInformation.setOnClickListener(this);
        leaveWordTitle.getImgLeft().setVisibility(View.VISIBLE);
        Resources res = getResources();
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        leaveWordTitle.setImgLeft(ic_return);
        leaveWordTitle.setTitleText("我的留言");
        leaveWordTitle.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLeaveWordsActivity.this.finish();
                JUMP_MAINACTIVITY = 2;
            }
        });
    }


    private void initInformationDate() {
        systemInforNumber = new ArrayList<>();
        systemInforTime = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            systemInforNumber.add(i + "");
        }
        for (int i = 0; i <= 60; i++) {
            systemInforTime.add(i + "分钟前");
        }
    }

    @OnClick(R.id.rl_system_information)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(MyLeaveWordsActivity.this, SystemInformationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
