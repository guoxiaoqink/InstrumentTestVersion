package com.example.tu4.activity.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WQJ on 2016/10/20
 * Descripyion: 购买课程的订单支付页面
 * Version: 1
 * Modify Person : wqj
 */
public class OrderPaymentActivity extends AppCompatActivity {
    @BindView(R.id.tv_pay_person)
    TextView payPerson;
    @BindView(R.id.tv_pay_money)
    TextView payMoney;
    @BindView(R.id.tv_pay_account)
    TextView payAccount;
    @BindView(R.id.radiobutton_zfb)
    RadioButton zfbRadiobutton;
    @BindView(R.id.radiobutton_wx)
    RadioButton wxRadiobutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_order_payment);
        ButterKnife.bind(this);
        //获得意图
        Intent intent = getIntent();
        //读取数据
        String pay_account = intent.getStringExtra("account");
        String pay_preson = intent.getStringExtra("TrueNmae");
        String pay_money = intent.getStringExtra("TotalMoney");
        payPerson.setText(pay_preson);
        payAccount.setText(pay_account);
        payMoney.setText(pay_money);
    }

    //返回按钮点击事件
    public void order_return(View v) {
        this.finish();
    }

    //去支付文字点击事件
    public void gotopay(View v) {
        Intent intent = new Intent();
        intent.setClass(OrderPaymentActivity.this, PaySuccessActivity.class);
        startActivity(intent);
    }

    //不同支付事件的切换
    @OnClick({R.id.radiobutton_zfb, R.id.radiobutton_wx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radiobutton_zfb:
                zfbRadiobutton.setChecked(true);
                wxRadiobutton.setChecked(false);
                break;
            case R.id.radiobutton_wx:
                wxRadiobutton.setChecked(true);
                zfbRadiobutton.setChecked(false);
                break;
        }
    }
}