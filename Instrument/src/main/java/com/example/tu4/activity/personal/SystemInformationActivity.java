package com.example.tu4.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.SystemInformationListviewAdapter;
import com.example.tu4.bean.SystemInformation;
import com.example.tu4.bean.SystemInformationPost;
import com.example.tu4.okhttp.JsonGenericsSerializator;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.SYSTEM_INFORMATION_URL;

/**
 * Created by hs on
 * Descripyion: 系统消息界面
 * Version：1
 * Modify Person：gxq
 */
public class SystemInformationActivity extends AppCompatActivity {

    @BindView(R.id.img_system_information_return)
    ImageView imgSystemInformationReturn;

    private ArrayList<Map<String,String>> dataList = new ArrayList<>();
    private Map<String,String> dataMap;

    @BindView(R.id.lv_system_information)
    ListView lvSystemInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_information);
        ButterKnife.bind(this);
        getDate();


    }

    /**
     * 获取数据
     */
    private void getDate() {
        dataList = new ArrayList<>();
        OkHttpUtils
                .postString()
                .url(SYSTEM_INFORMATION_URL)
                .content(new Gson().toJson(new SystemInformationPost(1,"2000","student")))
                .build()
                .execute(new GenericsCallback<SystemInformation>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("onError",e.getMessage());
                    }

                    @Override
                    public void onResponse(SystemInformation response, int id) {
                        Log.w("成功","...............");
                        for (int i = 0;i<response.getList().size();i++){
                            dataMap = new HashMap<>();
                            dataMap.put("title",response.getList().get(i).getTitle());
                            dataMap.put("content",response.getList().get(i).getContent());
                            dataMap.put("date",response.getList().get(i).getDate());
                            dataList.add(dataMap);
                        }
                        Log.w("成功",dataList.toString());
                        lvSystemInformation.setAdapter(new SystemInformationListviewAdapter(SystemInformationActivity.this,dataList));
                    }
                });
    }
    @OnClick(R.id.img_system_information_return)
    public void onClick() {
        this.finish();
    }

}
