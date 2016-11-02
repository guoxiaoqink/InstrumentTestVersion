package com.example.tu4.activity.course;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.view.MonthDateView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.model.IUrl.baseUrl;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 日历
 * Version: 1
 * Modify Person : Moofei
 */
public class DateActivity extends AppCompatActivity {
    private static List<Map<String, Object>> data_list;
    private static int Num1;
    private static String[] from = {"time", "course", "dress"};
    private static int[] ids = new int[]{R.id.tv_date_li_time, R.id.tv_date_li_course, R.id.tv_date_li_dress};
    @BindView(R.id.imgbtn_date_left)
    ImageView imgbtnDateLeft;
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;
    private ListView mListView;
    private SimpleAdapter sim_adapter;
    private int Num;
    private Handler handler = new Handler();
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Integer> list = new ArrayList<Integer>();
        //10.12.15.16有事物标记
        list.add(10);
        list.add(12);
        list.add(15);
        list.add(16);
        setContentView(R.layout.activity_date);
        ButterKnife.bind(this);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
        tv_date = (TextView) findViewById(R.id.date_text);
        monthDateView.setTextView(tv_date, tv_week);
        monthDateView.setDaysHasThingList(list);
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                Toast.makeText(getApplication(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
            }
        });
        setOnlistener();
        //
        mListView = (ListView) findViewById(R.id.lv_date);
        data_list = new ArrayList<Map<String, Object>>();


        String url = baseUrl + "/music-stju-test/api_calendar";

        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new getData(1, "1004")))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(DateActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(DateActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        Log.d("success", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String time = jsonObject.getString("Time");
                            String course = jsonObject.getString("Name");
                            String dress = jsonObject.getString("Location");
                            int Num = jsonObject.getInt("Num");
                            String Date = jsonObject.getString("Date");
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("time", time);
                            map.put("course", course + "(第" + Num + "课时）");
                            map.put("dress", dress);
                            data_list.add(map);
                            // getData(time1,course1,dress1,Num1);
                            sim_adapter = new SimpleAdapter(DateActivity.this, data_list, R.layout.date_list_item, from, ids);
                            mListView.setAdapter(sim_adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

//    public List<Map<String, Object>> getData(String time,String course,String dress,int num) {
//        //cion和iconName的长度是相同的，这里任选其一都可以
//
//        for (int i = 0; i < 2; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("time", time);
//            map.put("course", course+"(第"+num+"课时）");
//            map.put("dress", dress);
//            data_list.add(map);
//        }
//        return data_list;
//    }

    private void setOnlistener() {
        iv_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onLeftClick();
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onRightClick();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            mListView.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    @OnClick(R.id.imgbtn_date_left)
    public void onClick() {
        this.finish();
    }

    private class getData {
        private String code;
        private int User_id;

        public getData(int User_id, String code) {
            this.User_id = User_id;
            this.code = code;
        }

        @Override
        public String toString() {
            return "getData{" +
                    "User_id='" + User_id + '\'' +
                    "code='" + code + '\'' +
                    "}";
        }
    }

}
