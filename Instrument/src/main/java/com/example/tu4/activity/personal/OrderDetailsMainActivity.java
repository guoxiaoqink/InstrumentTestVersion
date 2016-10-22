package com.example.tu4.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.activity.SelectDressActivity;
import com.example.tu4.activity.payment.PaySuccessActivity;
import com.example.tu4.adapter.EnsureOrderListviewAdapter;
import com.example.tu4.view.ResolveConflictsScoolviewListview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsMainActivity extends AppCompatActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.order_details_listview)
    ResolveConflictsScoolviewListview orderDetailsListview;
    @BindView(R.id.radiobutton_zfb)
    RadioButton radiobuttonZfb;
    @BindView(R.id.radiobutton_wx)
    RadioButton radiobuttonWx;
    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        orderDetailsListview.setAdapter(new EnsureOrderListviewAdapter(OrderDetailsMainActivity.this));
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_address_consignee);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsMainActivity.this, SelectDressActivity.class);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.iv_return, R.id.radiobutton_zfb, R.id.radiobutton_wx, R.id.tv_go_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                this.finish();
                break;
            case R.id.radiobutton_zfb:
                radiobuttonZfb.setChecked(true);
                radiobuttonWx.setChecked(false);
                break;
            case R.id.radiobutton_wx:
                radiobuttonWx.setChecked(true);
                radiobuttonZfb.setChecked(false);
                break;
            case R.id.tv_go_pay:
                Intent intent = new Intent();
                intent.setClass(OrderDetailsMainActivity.this, PaySuccessActivity.class);
                startActivity(intent);
        }
    }

}
