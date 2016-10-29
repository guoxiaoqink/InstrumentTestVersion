package com.example.tu4.activity.course.payment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.instrument.SelectDressActivity;
import com.example.tu4.adapter.EnsureOrderListviewAdapter;
import com.example.tu4.utils.AliPay;
import com.example.tu4.view.ResolveConflictsScoolviewListview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WQJ on 2016/10/21
 * Descripyion: 乐器确认订购页面
 * Version: 1
 * Modify Person : wqj
 */
public class EnsureOrderActivity extends AppCompatActivity {
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL63YgQKppqEkuZK\n" +
            "QfkHuAeulDImk/Gf/A7Acx0S7RzjaLJIzSXKocs/OM+sZENO1EcHQpWcLF7J2dqk\n" +
            "5E7CzzR2s2Kqpx2Y7Gt0qEj/6YsuekceYISeIYfzct7kBi5TAFRhBmZRG+T3HdfL\n" +
            "7eWZ8CKGCfZVxfdmOTkHgiBjR15PAgMBAAECgYBScpzg+mnD6wRGolua+QdJ6uMO\n" +
            "OzdCAdPJ3NDnBMOU9YrRaD+n0s3XO+GnhfNiVbXXbwAOBZH4+HJB338DvZwtZiij\n" +
            "nh/F1IYWte04m/xLPQPJc3dnh8osPV8fuB1LQblu8ooqNtL/qfdKgTsDyyKCHPKU\n" +
            "76baWZEGaGADQykqQQJBAPMS4SskaVKWcMj22JBNgvQfOdJCzaS/CW3jjX513oer\n" +
            "k4LyyBQXkRfWA1nEXMS94O9fp7uVVOZ30GdKQl9JpokCQQDI27pcrKFejWSkNlBL\n" +
            "Rxp34TEoZjC17T8YV4DlNhGQqjmoP/Vn8ZVINnoexFM/58pk7SeC/Pfv4FjkG9wb\n" +
            "hCgXAkEA7aXFs3JT7sTa3ABK1zqRD6//zgw2Fh4YT/GsArmmqSocfGh1KP+pgY5h\n" +
            "lEAhGtExu5bf3MwxR2pYvMOjhJnzUQJAMtt/+oszaM+AVhvyhL0CNKxuV/5YAxiJ\n" +
            "S06MsnzYQbr4UmOvbZw74kb48OVg9uNn2do6kO4gkTpLB6cOOmFiDQJAKnGK+Lz9\n" +
            "FTz1y/n+/63IBwcFRPx0n+tOHUvRK15L2sW03aXBwtTRMHYWC5fomKuBPHthj85T\n" +
            "s3QOhFjFwY2iSA==";
    @BindView(R.id.btn_course_return)
    ImageButton btnCourseReturn;
    @BindView(R.id.list_yueqi)
    ResolveConflictsScoolviewListview listYueqi;
    @BindView(R.id.tv_clickToLeaveMessage)
    TextView tvClickToLeaveMessage;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_payment_style)
    TextView tvPaymentStyle;
    @BindView(R.id.radiobutton_zfb)
    RadioButton radiobuttonZfb;
    @BindView(R.id.radiobutton_wx)
    RadioButton radiobuttonWx;
    @BindView(R.id.re_payment)
    RelativeLayout rePayment;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_message_board_buyer)
    TextView tvMessageBoardBuyer;
    @BindView(R.id.et_message_board_buyer)
    EditText etMessageBoardBuyer;
    @BindView(R.id.view_divider_line7)
    View viewDividerLine7;
    @BindView(R.id.rdoBtn_leave_message_resolve)
    RadioButton rdoBtnLeaveMessageResolve;
    @BindView(R.id.view_divider_line8)
    View viewDividerLine8;
    @BindView(R.id.rdoBtn_leave_message_affirm)
    RadioButton rdoBtnLeaveMessageAffirm;
    @BindView(R.id.rg_leave_message_submit)
    RadioGroup rgLeaveMessageSubmit;
    @BindView(R.id.rl_message_board)
    RelativeLayout rlMessageBoard;
    @BindView(R.id.activity_ensure_order)
    RelativeLayout activityEnsureOrder;
    @BindView(R.id.sv_ensure_order)
    ScrollView svEnsureOrder;
    @BindView(R.id.re_position)
    RelativeLayout rePosition;
    @BindView(R.id.tv_instrument_total_money)
    TextView tvInstrumentTotalMoney;


    public static void scrollToBottom(final ScrollView scrollView) {

        Handler mHandler = new Handler();

        mHandler.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public static void scrollToTop(final ScrollView scrollView) {

        Handler mHandler = new Handler();

        mHandler.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_ensure_order);
        ButterKnife.bind(this);
        listYueqi.setAdapter(new EnsureOrderListviewAdapter(EnsureOrderActivity.this));
        svEnsureOrder.smoothScrollTo(0, 0);
        tvTotalMoney.setText(tvInstrumentTotalMoney.getText().toString());

    }

    @OnClick({R.id.btn_course_return, R.id.tv_clickToLeaveMessage, R.id.radiobutton_zfb, R.id.radiobutton_wx, R.id.rdoBtn_leave_message_resolve, R.id.rdoBtn_leave_message_affirm, R.id.re_position})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_course_return:
                this.finish();
                break;
            case R.id.tv_clickToLeaveMessage:
                scrollToBottom(svEnsureOrder);
                rlMessageBoard.setVisibility(View.VISIBLE);
                break;
            case R.id.radiobutton_zfb:
                radiobuttonZfb.setChecked(true);
                radiobuttonWx.setChecked(false);
                break;
            case R.id.radiobutton_wx:
                radiobuttonWx.setChecked(true);
                radiobuttonZfb.setChecked(false);
                break;
            case R.id.rdoBtn_leave_message_resolve:
                rlMessageBoard.setVisibility(View.INVISIBLE);
                break;
            case R.id.rdoBtn_leave_message_affirm:
                tvClickToLeaveMessage.setText(etMessageBoardBuyer.getText().toString().trim());
                rlMessageBoard.setVisibility(View.INVISIBLE);
                scrollToTop(svEnsureOrder);
                break;
            case R.id.re_position:
                Intent in = new Intent();
                in.setClass(EnsureOrderActivity.this, SelectDressActivity.class);
                startActivity(in);
                break;
        }
    }

    @OnClick(R.id.btn_ensure_order)
    public void onClick() {
        if (radiobuttonZfb.isChecked() == true) {
            AliPay.Builder builder = new AliPay.Builder(this);
            builder.setSELLER("17090403292")//卖家支付宝账号
                    .setPARTNER("2088702803595690")//签约合作人身份PID
                    .setRSA_PRIVATE(RSA_PRIVATE)//商户私钥
                    .setPrice("0.01")//商品价格
                    .setNotifyURL("")//通知网址（可以不填）
                    .setSubTitle("商品详情")//商品详情
                    .setOrderTitle("测试商品")//商品名称
                    .setPayCallBackListener(new AliPay.Builder.PayCallBackListener() {
                        @Override
                        public void onPayCallBack(int status, String resultStatus, String progress) {
                            Toast.makeText(EnsureOrderActivity.this, progress, Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.pay();
        } else if (radiobuttonWx.isChecked() == true) {
            Toast.makeText(EnsureOrderActivity.this, "微信支付正在开发中，请选择其他支付方式", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
        }
//        Intent intent = new Intent();
//        intent.setClass(EnsureOrderActivity.this, OrderDetailsMainActivity.class);
//        startActivity(intent);
    }


}
