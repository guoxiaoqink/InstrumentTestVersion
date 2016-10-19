package com.example.tu4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.OrderDetailsListviewAdapter;
import com.example.tu4.bean.User;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsMainActivity extends AppCompatActivity {

    private ListView listView;
    private OrderDetailsListviewAdapter adapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        initdata();
        adapter = new OrderDetailsListviewAdapter(this, users);
        listView.setAdapter((ListAdapter) adapter);
    }

    //为了测试，特地将不同的布局的数据混乱的添加到list里
    private void initdata() {
        users = new ArrayList<User>();
        users.add(new User("乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX", null, null));
        users.add(new User("乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX", null, null));
        users.add(new User("乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX", null, null));


    }
}
