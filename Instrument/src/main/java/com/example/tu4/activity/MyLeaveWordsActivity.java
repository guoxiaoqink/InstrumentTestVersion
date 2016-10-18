package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.tu4.R;
import com.example.tu4.adapter.MyLeaveWordsListviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tu4.model.AplicationStatic.JUMP_MAINACTIVITY;

public class MyLeaveWordsActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.lv_my_leave_words)
    ListView lvMyLeaveWords;
    @BindView(R.id.rl_system_information)
    RelativeLayout rlSystemInformation;
    @BindView(R.id.img_my_leave_words_return)
    ImageView imgMyLeaveWordsReturn;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_leave_words);
        ButterKnife.bind(this);

        myLeaveWordsListviewAdapter = new MyLeaveWordsListviewAdapter(this, leaveWordsPhoto,
                leaveWordsName, leaveWordsContext, leaveWordsTime, leaveWordsCommentPhoto,
                leaveWordsCommentName, leaveWordsCommentContext, leaveWordsCommentTime);
        lvMyLeaveWords.setAdapter(myLeaveWordsListviewAdapter);

        imgMyLeaveWordsReturn.setOnClickListener(this);
        rlSystemInformation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_system_information:
                Intent intent = new Intent(MyLeaveWordsActivity.this, SystemInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.img_my_leave_words_return:
                Intent intent1 = new Intent(MyLeaveWordsActivity.this, MainActivity.class);
                startActivity(intent1);
                JUMP_MAINACTIVITY = 2;
                break;
        }
    }
}
