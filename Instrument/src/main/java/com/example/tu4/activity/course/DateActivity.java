package com.example.tu4.activity.course;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.view.MonthDateView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateActivity extends AppCompatActivity {
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
    private List<Map<String, Object>> data_list;

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
        String[] from = {"time", "course", "dress"};
        int[] id = new int[]{R.id.tv_date_li_time, R.id.tv_date_li_course, R.id.tv_date_li_dress};
        getData();
        sim_adapter = new SimpleAdapter(DateActivity.this, data_list, R.layout.date_list_item, from, id);
        mListView.setAdapter(sim_adapter);

    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("time", "14:00");
            map.put("course", "课程XXXX");
            map.put("dress", "XX小区XX大厦XX楼");
            data_list.add(map);
        }
        return data_list;
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

    @OnClick(R.id.imgbtn_date_left)
    public void onClick() {
        this.finish();
    }
}
