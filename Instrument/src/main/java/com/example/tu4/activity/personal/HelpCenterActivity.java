package com.example.tu4.activity.personal;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.HelpCenterListviewAdapter;
import com.example.tu4.utils.CacheServerResponse;
import com.example.tu4.view.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.HELP_CENTER_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.JUMP_MAINACTIVITY;

/**
 * Created by hs on
 * Descripyion: 帮助中心界面
 * Version：1
 * Modify Person：gxq
 */
public class HelpCenterActivity extends AppCompatActivity {

    @BindView(R.id.lv_help_center)
    ListView lvHelpCenter;
    @BindView(R.id.help_title)
    TitleView helpTitle;
    private ArrayList<Map<String, String>> listDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);

        getCacheDate();

        Resources res = getResources();
        helpTitle.getImgLeft().setVisibility(View.VISIBLE);
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        helpTitle.setImgLeft(ic_return);
        helpTitle.setTitleText("帮助中心");
        helpTitle.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpCenterActivity.this.finish();
                JUMP_MAINACTIVITY = 2;
            }
        });

    }


    /**
     * 获取缓存数据
     */
    private void getCacheDate() {
        if (CacheServerResponse.isCacheDataFailure(getApplicationContext(),"HelpCenterGet")){
            Log.w("读取缓存数据","读取缓存数据失败，重新请求数据");
            getDate();
        }else {
            HelpCenterGet helpCenterGet = (HelpCenterGet)CacheServerResponse.readObject(getApplicationContext(),"HelpCenterGet");
            initData(helpCenterGet);
            Log.w("读取缓存数据","读取缓存成功");
        }
    }

    /**
     * 填充数据
     */
    private void initData(HelpCenterGet helpCenterGet) {
        listDate = new ArrayList<>();
        String Content = helpCenterGet.getContent();
        String About = helpCenterGet.getAbout();
        Log.w("onResponse", "Content = " + Content);
        Log.w("onResponse", "About = " + About);
        Map<String, String> mapDate = new HashMap<String, String>();
        mapDate.put("title", "使用文章");
        mapDate.put("text", Content);
        listDate.add(mapDate);
        Map<String, String> mapDate1 = new HashMap<String, String>();
        mapDate1.put("title", "关于");
        mapDate1.put("text", About);
        listDate.add(mapDate1);
        Log.w("listDate", listDate.toString());
        lvHelpCenter.setAdapter(new HelpCenterListviewAdapter(HelpCenterActivity.this, listDate));

    }

    /**
     * 获取网络数据
     */
    private void getDate() {
        OkHttpUtils
                .postString()
                .url(HELP_CENTER_URL)
                .content(new Gson().toJson(new HelpCenterPost("1020")))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("onError", "数据加载失败");
                        Toast.makeText(HelpCenterActivity.this, "数据加载失败", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.w("onResponse", "okokokokokokokokokokokokok");
                        HelpCenterGet helpCenterGet = new Gson().fromJson(response, HelpCenterGet
                                .class);
                        initData(helpCenterGet);
                        if(CacheServerResponse.saveObject(getApplicationContext(),"HelpCenterGet",helpCenterGet)){
                            Log.w("添加缓存","HelpCenterGet 缓存成功");
                        }else {
                            Log.w("添加缓存","HelpCenterGet 缓存失败");
                        }

                    }
                });

    }

    public class HelpCenterPost implements Serializable{
        private String code;

        public HelpCenterPost(String code) {
            this.code = code;
        }
    }


    public class HelpCenterGet implements Serializable {
        private String Content;
        private String About;

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getAbout() {
            return About;
        }

        public void setAbout(String about) {
            About = about;
        }
    }
}
