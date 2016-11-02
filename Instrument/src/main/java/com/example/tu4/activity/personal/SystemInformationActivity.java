package com.example.tu4.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.SystemInformationListviewAdapter;
import com.example.tu4.okhttp.JsonGenericsSerializator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
/**
 * Created by hs on
 * Descripyion: 系统消息界面
 * Version：1
 * Modify Person：gxq
 */
public class SystemInformationActivity extends AppCompatActivity {

    @BindView(R.id.img_system_information_return)
    ImageView imgSystemInformationReturn;

    private ArrayList<Map<String,String>> dataList;
    private Map<String,String> dataMap;

    @BindView(R.id.lv_system_information)
    ListView lvSystemInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_information);
        ButterKnife.bind(this);
       // getDate();
        lvSystemInformation.setAdapter(new SystemInformationListviewAdapter(this,dataList));

    }

    /**
     * 获取数据
     */
    private void getDate() {
        dataList = new ArrayList<>();

        String url = "";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("User_id","1")
                .addParams("role","Student")
                .build()
                .execute(new GenericsCallback<SystemInformation>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError",e.getMessage());

                    }

                    @Override
                    public void onResponse(SystemInformation response, int id) {
                        for (int i = 0;i<response.list.size();i++){
                            dataMap = new HashMap<>();
                            dataMap.put("system_title",response.list.get(i).system_title);
                            dataMap.put("system_content",response.list.get(i).system_content);
                            dataMap.put("system_time",response.list.get(i).system_time);
                            dataList.add(dataMap);
                        }
                    }
                });
    }

    @OnClick(R.id.img_system_information_return)
    public void onClick() {
        this.finish();
    }



    public class SystemInformation implements Serializable{
        private List<SysInfor> list;

        public List<SysInfor> getList() {
            return list;
        }

        public void setList(List<SysInfor> list) {
            this.list = list;
        }

        public SystemInformation(List<SysInfor> list) {
            this.list = list;
        }

        public class SysInfor implements Serializable{

            private String system_title;
            private String system_content;
            private String system_time;

            public String getSystem_title() {
                return system_title;
            }

            public void setSystem_title(String system_title) {
                this.system_title = system_title;
            }

            public String getSystem_content() {
                return system_content;
            }

            public void setSystem_content(String system_content) {
                this.system_content = system_content;
            }

            public String getSystem_time() {
                return system_time;
            }

            public void setSystem_time(String system_time) {
                this.system_time = system_time;
            }

            public SysInfor(String system_title, String system_content, String system_time) {
                this.system_title = system_title;
                this.system_content = system_content;
                this.system_time = system_time;
            }
        }
    }


}
