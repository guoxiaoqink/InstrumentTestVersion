package com.example.tu4.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.BookingOrderAdapter;
import com.example.tu4.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingOrderActivity extends AppCompatActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    private ListView listView;
    private BookingOrderAdapter adapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_order);
        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.listview);
        initdata();
        adapter = new BookingOrderAdapter(this, users);
        listView.setAdapter(adapter);
    }

    //为了测试，特地将不同的布局的数据混乱的添加到list里
    private void initdata() {
        users = new ArrayList<User>();
        users.add(new User("课程XXXXXX", null, null));
        users.add(new User("课程XXXXXX", null, null));
        users.add(new User("课程XXXXXX", null, null));
    }

    @OnClick(R.id.iv_return)
    public void onClick() {
        this.finish();
    }
}
