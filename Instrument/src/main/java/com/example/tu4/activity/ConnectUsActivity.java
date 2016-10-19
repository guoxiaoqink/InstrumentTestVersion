package com.example.tu4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.ConnectUsGridviewAdapter;
import com.example.tu4.model.AplicationStatic;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConnectUsActivity extends AppCompatActivity {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.tv_telephone_call)
    TextView tvTelephoneCall;
    @BindView(R.id.gv_service)
    GridView gvService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_us);
        ButterKnife.bind(this);
        initGridview();
    }


    public void initGridview() {
        ConnectUsGridviewAdapter connectUsGridviewAdapter = new ConnectUsGridviewAdapter(AplicationStatic.getCustomServeName(), getBaseContext());
        gvService.setAdapter(connectUsGridviewAdapter);
        gvService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ConnectUsActivity.this, "click server" + position + 1, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.img_return, R.id.tv_telephone_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                this.finish();
                break;
            case R.id.tv_telephone_call:
                break;
        }
    }
}
