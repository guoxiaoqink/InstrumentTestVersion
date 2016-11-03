package com.example.tu4.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.HelpCenterListviewAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.img_help_center_return)
    ImageView imgHelpCenterReturn;
    private ArrayList<Map<String, String>> listDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);

        getDate();

        lvHelpCenter.setAdapter(new HelpCenterListviewAdapter(this, listDate));


    }

    /**
     * 获取网络数据
     */
    private void getDate() {
        listDate = new ArrayList<>();
        OkHttpUtils
                .post()
                .url(HELP_CENTER_URL)
                .addParams("code", "1020")
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
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Content = jsonObject.getString("Content");
                            String About = jsonObject.getString("About");
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
                            Log.w("listDate",listDate.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    @OnClick(R.id.img_help_center_return)
    public void onClick() {
        this.finish();
        JUMP_MAINACTIVITY = 2;
    }
}
