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

import static com.example.tu4.model.AplicationStatic.JUMP_MAINACTIVITY;

public class MyWorksActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_my_works_return)
    ImageView imgMyWorksReturn;
    private String[] myWorksTime, myWorksDate;
    private ArrayList<Integer> myWorksPisture;
    @BindView(R.id.img_my_works_delete)
    ImageView imgMyWorksDelete;

    @BindView(R.id.gv_my_works)
    GridView gvMyWorks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_works);
        ButterKnife.bind(this);

        initDate();

        MyWorksGridviewAdapter workAdapter = new MyWorksGridviewAdapter(this, myWorksPisture,
                myWorksTime, myWorksDate);
        gvMyWorks.setAdapter(workAdapter);

        imgMyWorksDelete.setOnClickListener(this);
        imgMyWorksReturn.setOnClickListener(this);

        gvMyWorks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MyWorksActivity.this, VideoRecordActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MyWorksActivity.this, VideoPlayActivity.class);
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
        myWorksTime = new String[]{"20s", "20s", "20s", "20s", "20s", "20s", "20s", "20s",};
        myWorksDate = new String[]{"2016-05-06 15:00", "2016-05-06 15:00", "2016-05-06 15:00",
                "2016-05-06 15:00", "2016-05-06 15:00", "2016-05-06 15:00", "2016-05-06 15:00",
                "2016-05-06 15:00",};
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_my_works_delete:
                Intent intent = new Intent(MyWorksActivity.this, MyWorksDeleteActivity.class);
                startActivity(intent);
                break;
            case R.id.img_my_works_return:
//                Intent intent1 = new Intent(MyWorksActivity.this, MainActivity.class);
//                startActivity(intent1);
                this.finish();
                JUMP_MAINACTIVITY = 2;
                break;
        }

    }

}
