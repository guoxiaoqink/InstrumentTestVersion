package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.tu4.R;
import com.example.tu4.adapter.MyWorksGridviewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWorksActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Integer> myWorksPisture;
    @BindView(R.id.imgMyWorksDelete)
    ImageView imgMyWorksDelete;

    @BindView(R.id.gvMyWorks)
    GridView gvMyWorks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_works);
        ButterKnife.bind(this);
        initDate();
        MyWorksGridviewAdapter workAdapter = new MyWorksGridviewAdapter(this, myWorksPisture);
        gvMyWorks.setAdapter(workAdapter);
        imgMyWorksDelete.setOnClickListener(this);
        gvMyWorks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0 ){
                    Intent intent = new Intent(MyWorksActivity.this,VideoRecordActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MyWorksActivity.this,VideoPlayActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    void initDate() {
        myWorksPisture = new ArrayList<>();
        myWorksPisture.add(R.mipmap.image1);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMyWorksDelete:
                Intent intent = new Intent(MyWorksActivity.this, MyWorksDeleteActivity.class);
                startActivity(intent);
        }

    }

}
