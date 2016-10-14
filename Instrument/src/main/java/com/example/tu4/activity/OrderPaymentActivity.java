package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.tu4.R;

/**
 * 订单支付界面
 */
public class OrderPaymentActivity extends AppCompatActivity {
    private RadioButton payment_zfb;
    private RadioButton payment_wx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_payment);
        payment_zfb = (RadioButton) findViewById(R.id.zfb_radiobutton);
        payment_wx = (RadioButton) findViewById(R.id.wx_radiobutton);
        payment_zfb.setOnClickListener(new View.OnClickListener() {//radiobutton添加点击事件为了互斥
            @Override
            public void onClick(View v) {
                payment_zfb.setChecked(true);
                payment_wx.setChecked(false);
            }
        });
        payment_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment_wx.setChecked(true);
                payment_zfb.setChecked(false);
            }
        });
    }

    //返回按钮点击事件
    public void order_return(View v) {
        Intent intent = new Intent();
        intent.setClass(OrderPaymentActivity.this, CourseSubscribeActivity.class);
        startActivity(intent);
    }

    //去支付文字点击事件
    public void gotopay(View v) {
        Intent intent = new Intent();
        intent.setClass(OrderPaymentActivity.this, CourseSubscribeActivity.class);
        startActivity(intent);
    }
}
