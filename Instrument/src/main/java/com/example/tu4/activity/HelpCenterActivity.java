package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.HelpCenterListviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tu4.model.AplicationStatic.JUMP_MAINACTIVITY;

public class HelpCenterActivity extends AppCompatActivity {


    @BindView(R.id.lv_help_center)
    ListView lvHelpCenter;
    @BindView(R.id.img_help_center_return)
    ImageView imgHelpCenterReturn;
    private String[] title = {"标题", "标题", "标题",};
    private String[] text = {"内容1", "内容2", "内容3",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);

        lvHelpCenter.setAdapter(new HelpCenterListviewAdapter(this, title, text));


    }

    @OnClick(R.id.img_help_center_return)
    public void onClick() {
        Intent intent = new Intent(HelpCenterActivity.this, MainActivity.class);
        startActivity(intent);
        JUMP_MAINACTIVITY = 2;

    }
}
