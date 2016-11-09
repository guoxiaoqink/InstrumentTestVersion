package com.example.tu4.activity.course;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.view.MonthDateView;
import com.example.tu4.view.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.CALENDAR_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 日历
 * Version: 1
 * Modify Person : Moofei
 */
public class DateActivity extends AppCompatActivity {
    private static List<Map<String, Object>> data_list;
    private static List<Map<String, Object>> data_list1;
    private static int Num1;
    private static String[] from = {"time", "course", "dress"};
    private static int[] ids = new int[]{R.id.tv_date_li_time, R.id.tv_date_li_course, R.id.tv_date_li_dress};
    private static String date0;
    private static String day;
    @BindView(R.id.rl_D)
    TitleView rlD;
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;
    private ListView mListView;
    private SimpleAdapter sim_adapter;
    private Handler handler = new Handler();
    private Thread thread;
    private Handler handler2 = new Handler();
    private Handler handler3 = new Handler();
    private String time;
    private String course;
    private String dress;
    private int Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_date);
        ButterKnife.bind(this);
        Resources res = getResources();
        String title = "课程日历".toString();
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        Drawable ic_search = res.getDrawable(R.mipmap.lookup);
        rlD.setImgLeft(ic_return);
        rlD.setImgRight2(ic_search);
        rlD.getImgLeft().setVisibility(View.VISIBLE);
        rlD.getImgRight2().setVisibility(View.VISIBLE);
        rlD.setTitleText(title);
        rlD.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateActivity.this.finish();
            }
        });
        rlD.setImgRight2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(DateActivity.this, SearchActivity.class);
                startActivity(in);
                finish();
            }
        });

        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
        tv_date = (TextView) findViewById(R.id.date_text);
        getData();

        //
        mListView = (ListView) findViewById(R.id.lv_date);
        data_list = new ArrayList<Map<String, Object>>();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            // Toast.makeText(DateActivity.this,day,Toast.LENGTH_SHORT).show();
                            //tv_today.getText().toString();
                            Calendar calendar = Calendar.getInstance();
                            final int date = calendar.get(Calendar.DAY_OF_MONTH);//当前日期
                            final String nowdate_str = getDate(date);
                            monthDateView.setTextView(tv_date, tv_week);
                            if (nowdate_str.equals(day)) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("time", time);
                                map.put("course", course + "(第" + Num + "课时）");
                                map.put("dress", dress);
                                data_list.clear();
                                data_list.add(map);
                                // getData(time1,course1,dress1,Num1);
                                sim_adapter = new SimpleAdapter(DateActivity.this, data_list, R.layout.date_list_item, from, ids);
                                mListView.setAdapter(sim_adapter);
                            }
                            monthDateView.setDateClick(new MonthDateView.DateClick() {

                                @Override
                                public void onClickOnDate() {
                                    int day_int = Integer.parseInt(day);
                                    if (monthDateView.getmSelDay() == day_int) {
                                        Toast.makeText(DateActivity.this, " " + time + " " + course + " " + dress, Toast.LENGTH_SHORT).show();
                                        Map<String, Object> map = new HashMap<String, Object>();
                                        map.put("time", time);
                                        map.put("course", course + "(第" + Num + "课时）");
                                        map.put("dress", dress);
                                        data_list.clear();
                                        data_list.add(map);
                                        sim_adapter = new SimpleAdapter(DateActivity.this, data_list, R.layout.date_list_item, from, ids);
                                        mListView.setAdapter(sim_adapter);
                                    } else {
                                        Map<String, Object> map = new HashMap<String, Object>();
                                        data_list.clear();
                                        // getData(time1,course1,dress1,Num1);
                                        sim_adapter = new SimpleAdapter(DateActivity.this, data_list, R.layout.date_list_item, from, ids);
                                        mListView.setAdapter(sim_adapter);
                                    }
                                }
                            });
                            setOnlistener();

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }

    private String getDate(int date) {
        String date_str = String.valueOf(date);
        if (date_str.length() == 1) {
            date_str = "0" + date_str;
        }
        return date_str;
    }

    private void getData() {
        OkHttpUtils.postString()
                .url(CALENDAR_URL)
                .content(new Gson().toJson(new getData(UserId, "1004")))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(DateActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        // Toast.makeText(DateActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        Log.d("success", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            time = jsonObject.getString("Time");
                            course = jsonObject.getString("Name");
                            dress = jsonObject.getString("Location");
                            Num = jsonObject.getInt("Num");
                            String Date = jsonObject.getString("Date");
                            date0 = Date;
                            day = date0.substring(date0.indexOf("-") + 1);
                            int date_int = Integer.parseInt(day);//服务器日期转化整形
                            final List<Integer> list = new ArrayList<Integer>();
                            //date_int有事物标记
                            list.add(date_int);
                            monthDateView.setDaysHasThingList(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

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
