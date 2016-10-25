package com.example.tu4.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.SystemInformationListviewAdapter;

import java.util.List;

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
       // getDate();
        lvSystemInformation.setAdapter(new SystemInformationListviewAdapter(this, name, text,
                time));

    }

    /**
     * 获取数据
     */
//    private void getDate() {
//        String url = "";
//        OkHttpUtils
//                .post()
//                .url(url)
//                .build()
//                .execute(new GenericsCallback<SystemInformation>(new JsonGenericsSerializator()) {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(SystemInformation response, int id) {
//
//
//                    }
//
//                });
//    }

    @OnClick(R.id.img_system_information_return)
    public void onClick() {
        this.finish();
    }



    public class SystemInformation {
        private List<SysInfor> list;

        public SystemInformation(List<SysInfor> list) {
            this.list = list;
        }

        public class SysInfor {

            private String system_title;
            private String system_content;
            private String system_time;


            public SysInfor(String system_title, String system_content, String system_time) {
                this.system_title = system_title;
                this.system_content = system_content;
                this.system_time = system_time;
            }
        }
    }


}
