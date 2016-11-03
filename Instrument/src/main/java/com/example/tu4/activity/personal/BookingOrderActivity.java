package com.example.tu4.activity.personal;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.adapter.BookingOrderAdapter;
import com.example.tu4.bean.User;
import com.example.tu4.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scy on
 * Descripyion:预约订单界面
 * Version：1
 * Modify Person：gxq
 */
public class BookingOrderActivity extends AppCompatActivity {

    @BindView(R.id.booking_order_title)
    TitleView bookingOrderTitle;
    //    @BindView(R.id.iv_return)
//    ImageView ivReturn;
    private ListView listView;
    private BookingOrderAdapter adapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_booking_order);
        ButterKnife.bind(this);
        Resources res = getResources();
        listView = (ListView) findViewById(R.id.listview);
        initdata();
        adapter = new BookingOrderAdapter(this, users);
        listView.setAdapter(adapter);
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
    }

    //为了测试，特地将不同的布局的数据混乱的添加到list里
    private void initdata() {
        users = new ArrayList<User>();
        users.add(new User("课程XXXXXX", null, null));
        users.add(new User("课程XXXXXX", null, null));
        users.add(new User("课程XXXXXX", null, null));
    }

//    @OnClick(R.id.iv_return)
//    public void onClick() {
//        this.finish();
//    }
}
