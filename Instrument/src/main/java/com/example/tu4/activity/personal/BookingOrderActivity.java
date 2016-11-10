package com.example.tu4.activity.personal;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.activity.course.SubjectDetailActivity;
import com.example.tu4.adapter.BookingOrderAdapter;
import com.example.tu4.bean.BookingOrder;
import com.example.tu4.bean.BookingOrderPost;
import com.example.tu4.okhttp.JsonGenericsSerializator;
import com.example.tu4.view.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.BOOKING_ORDER_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;
import static com.example.tu4.utils.ApplicationStaticConstants.listOrderSearch;

/**
 * Created by scy on
 * Descripyion:预约订单界面
 * Version：1
 * Modify Person：gxq
 */
public class BookingOrderActivity extends AppCompatActivity {

    @BindView(R.id.booking_order_title)
    TitleView bookingOrderTitle;

    private ListView listView;
    private BookingOrderAdapter adapter;
    private ArrayList<Map<String, String>> listData;
    private Map<String, String> mapData;
    private String situation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_booking_order);
        ButterKnife.bind(this);
        Resources res = getResources();
        listView = (ListView) findViewById(R.id.listview);

        getDataByUrl();

//        adapter = new BookingOrderAdapter(this,listData);
//        listView.setAdapter(adapter);
        //title
        bookingOrderTitle.getImgLeft().setVisibility(View.VISIBLE);
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        Drawable ic_look = res.getDrawable(R.mipmap.lookup);
        bookingOrderTitle.setImgLeft(ic_return);
        bookingOrderTitle.setImgRight2(ic_look);
        bookingOrderTitle.setTitleText("预约订单");
        bookingOrderTitle.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingOrderActivity.this.finish();
            }
        });
        bookingOrderTitle.setImgRight2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BookingOrderActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(BookingOrderActivity.this, SubjectDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 从网络获取数据
     */
    private void getDataByUrl() {
        OkHttpUtils
                .postString()
                .url(BOOKING_ORDER_URL)
                .content(new Gson().toJson(new BookingOrderPost(UserId, "2010")))
                .build()
                .execute(new GenericsCallback<BookingOrder>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("onError","数据加载失败");
                        Toast.makeText(BookingOrderActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(BookingOrder response, int id) {
                        listData = new ArrayList<Map<String, String>>();
                        List<BookingOrder.BookingOrderinfo> list = response.getList();
                        for (int i = 0; i < list.size(); i++) {
                            mapData = new HashMap<String, String>();
                            if (list.get(i).getSituation() == 1) {
                                situation = "未上课".toString();
                            } else if (list.get(i).getSituation() == 0) {
                                situation = "已完成".toString();
                            } else {
                                situation = "已上了" + (i - 1) + "课时".toString();
                            }
                            mapData.put("situation", situation);
                            mapData.put("class_name", list.get(i).getClass_name());
                            mapData.put("class_time", String.valueOf(list.get(i).getClass_time()));
                            mapData.put("teacher_name", list.get(i).getTeacher_name());
                            mapData.put("class_price", String.valueOf(list.get(i).getClass_price
                                    ()));
                            mapData.put("class_pic_url", list.get(i).getClass_pic_url());
                            mapData.put("date", list.get(i).getDate());
                            listData.add(mapData);
                            Log.w("listData",listData.toString());
                            listOrderSearch = listData;
                            adapter = new BookingOrderAdapter(BookingOrderActivity.this,listData);
                            listView.setAdapter(adapter);
                        }
                    }

                });
    }



}
