package com.example.tu4.activity;

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

import com.example.tu4.R;
import com.example.tu4.adapter.EnsureOrderListviewAdapter;
import com.example.tu4.view.ResolveConflictsScoolviewListview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 乐器确认订单页面
 *
 * @auther WQJ
 * created at 2016/10/20 20:07
 */
public class EnsureOrderActivity extends AppCompatActivity {

    @BindView(R.id.btn_course_return)
    ImageButton btnCourseReturn;
    @BindView(R.id.list_yueqi)
    ResolveConflictsScoolviewListview listYueqi;
    @BindView(R.id.tv_clickToLeaveMessage)
    TextView tvClickToLeaveMessage;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_instrument_total_money)
    TextView tvInstrumentTotalMoney;
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

    }

    @OnClick({R.id.btn_course_return, R.id.tv_clickToLeaveMessage, R.id.radiobutton_zfb, R.id.radiobutton_wx, R.id.rdoBtn_leave_message_resolve, R.id.rdoBtn_leave_message_affirm})
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
        }
    }

    @OnClick(R.id.btn_ensure_order)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(EnsureOrderActivity.this, OrderDetailsMainActivity.class);
        startActivity(intent);
    }
}
