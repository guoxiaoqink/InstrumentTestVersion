package com.example.tu4.activity.course.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.bean.PayOrderPost;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.ORDER_PAYMENT_URL;

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
    @BindView(R.id.tv_pay_course_name)
    TextView tvPayCourseName;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    private String time_com = "";
    private String pay_tel = "";
    private String pay_money = "";
    private String pay_name = "";
    private String true_name = "";

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
        pay_money = intent.getStringExtra("TotalMoney");
        pay_name = intent.getStringExtra("name");
        pay_tel = intent.getStringExtra("tel");
        true_name=intent.getStringExtra("Name");
        tvPayCourseName.setText(pay_name);
        payPerson.setText(pay_preson);
        payAccount.setText(pay_account);
        payMoney.setText(pay_money);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        System.out.println(curDate);
        String time[] = str.split(":");
        for (int i = 0; i < time.length; i++) {
            time_com += time[i];
        }
        tvOrderNum.setText(time_com + 123);
//        getDataByUrl();

    }

    //返回按钮点击事件
    public void order_return(View v) {
        this.finish();
    }

    //去支付文字点击事件
    public void gotopay(View v) {
        postDataByUrl();
        Toast.makeText(this, "支付测试", Toast.LENGTH_SHORT).show();
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

    //从网上获取列表内容并显示在当前页面中
    public void postDataByUrl() {
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String num = tvOrderNum.getText().toString();
        int ordernum = Integer.parseInt(num);
//        double price= Integer.parseInt(pay_money);
        OkHttpUtils
                .postString()
                .url(ORDER_PAYMENT_URL)//
                .content(new Gson().toJson(new PayOrderPost("1",pay_tel,true_name,str,1,ordernum,pay_money,1)))
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(OrderPaymentActivity.this, "内容获取失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("注意这里啊。。。。。。。", response);
                        System.out.println(response);
                        Gson gson = new Gson();
                    }
                });
    }
}
