package com.example.tu4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.SystemInformationListviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemInformationActivity extends AppCompatActivity {

    @BindView(R.id.img_system_information_return)
    ImageView imgSystemInformationReturn;
    private String[] name = {"通知标题", "通知标题", "通知标题", "通知标题",};
    private String[] text = {"内容1111111111111", "内容1111111111111", "内容1111111111111",
            "内容1111111111111",};
    private String[] time = {"2016-05-05", "2016-06-06", "2016-07-07", "2016-08-08",};

    @BindView(R.id.lv_system_information)
    ListView lvSystemInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_information);
        ButterKnife.bind(this);
        lvSystemInformation.setAdapter(new SystemInformationListviewAdapter(this, name, text,
                time));

    }

    @OnClick(R.id.img_system_information_return)
    public void onClick() {
//        Intent intent = new Intent(SystemInformationActivity.this,MyLeaveWordsActivity.class);
//        startActivity(intent);
        this.finish();
    }
}
