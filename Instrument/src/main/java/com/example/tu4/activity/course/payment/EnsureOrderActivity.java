package com.example.tu4.activity.course.payment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.instrument.SelectDressActivity;
import com.example.tu4.bean.EnsureOrderPost;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.ENSURE_ORDER_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;
import static com.example.tu4.utils.IUrl.baseUrl;
import static com.zhy.http.okhttp.OkHttpUtils.postString;

/**
 * Created by WQJ on 2016/10/21
 * Descripyion: 乐器确认订购页面
 * Version: 1
 * Modify Person : wqj
 */
public class EnsureOrderActivity extends AppCompatActivity {
    public static final String RSA_PRIVATE =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJqv8DsgJULMOI6G\n" +
                    "MocoeujqQKzQ6/hH2AKsDueUEhdVu2fTIg6gbxxwWC4MNaSBkVqeSfBUVW63XACI\n" +
                    "JBIk4N+VYvwFPgZt9TNf3UrOb00A9rYH3nxH38YObR45fDtJi0cYy547gNv5YwLF\n" +
                    "ioyKGTrthpatvlVRINwt7G+1crejAgMBAAECgYAZYClZmbFjHttcThl14KchFGSd\n" +
                    "tPww06BU9+LODJVXLKI8qv8YRStVD/z3ONuH7BVrNzJL8Zm+Ougl0i+QpmoZIjuB\n" +
                    "PUdu7MhP93AoXoYeUVjypwdjH1LaWMWVvdVu6RH9aquZqk1YLMqUcBEAJJ38Z1JQ\n" +
                    "O3sJXE7fnRf/a9dnMQJBAMtIt6Gw+M+s//oHxSTEvrot0zNXLvWf3lpoKVMZFTkz\n" +
                    "2vaYoumEaPqd5MDpZfRzNVFpVve7n2KJSNEWGKs0cUkCQQDCzQ7psLfkEY8OG9Gn\n" +
                    "UjG1jPOwgBt3ZYL9DTUD1pgWFR5xqJR0tBLvdwJhjmfoxbDndhnhCDDva4CBKM/E\n" +
                    "9I2LAkAnYkI5cEj0K0c4kLLQKdHtzh0B8F0ntz5j85Q2BkEHYRWF+xJs/Xs9OsPr\n" +
                    "4AwhrQRibm8r9cyuUXyrDYXf/XwJAkA5IdUO2uKEBFZVh/ksPqIPoiBSkq/7i40o\n" +
                    "VHhJAOYoC9ea9BteQvYOv3O2UwLMtTZWHEAozLM1dO0CdIrDNApTAkEAsiNrjQSi\n" +
                    "SlKhW9b4dLg9xukHLrZl9EpzFwsgr6/5pE/w/QIG0n6NlGTFwEWuiGxEvSWSXSFp\n" +
                    "DmeyygSLiVeWFw==";
    @BindView(R.id.btn_course_return)
    ImageButton btnCourseReturn;
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
    @BindView(R.id.iv_instrument_EO)
    ImageView ivInstrumentEO;
    @BindView(R.id.tv_instrument_name_EO)
    TextView tvInstrumentNameEO;
    @BindView(R.id.tv_property_EO)
    TextView tvPropertyEO;
    @BindView(R.id.tv_money_EO)
    TextView tvMoneyEO;
    @BindView(R.id.tv_consignee_name)
    TextView tvConsigneeName;
    @BindView(R.id.tv_consignee_phone)
    TextView tvConsigneePhone;
    @BindView(R.id.tv_consignee_dress)
    TextView tvConsigneeDress;


    public static void scrollToBottom(final ScrollView scrollView) {//滚动到底部

        Handler mHandler = new Handler();

        mHandler.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public static void scrollToTop(final ScrollView scrollView) {//滚动到顶部

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
        Intent in = getIntent();
        // listYueqi.setAdapter(new EnsureOrderListviewAdapter(EnsureOrderActivity.this));
        svEnsureOrder.smoothScrollTo(0, 0);
        tvTotalMoney.setText(in.getStringExtra("MoneyNum"));
        tvFreight.setText(in.getStringExtra("Freight"));
        tvInstrumentNameEO.setText(in.getStringExtra("Ins_name"));
        tvMoneyEO.setText(in.getStringExtra("price"));
        tvInstrumentTotalMoney.setText(in.getStringExtra("MoneyNum"));
        tvPropertyEO.setText(in.getStringExtra("para"));


    }

    @OnClick({R.id.btn_course_return, R.id.tv_clickToLeaveMessage, R.id.radiobutton_zfb, R.id
            .radiobutton_wx, R.id.rdoBtn_leave_message_resolve, R.id.rdoBtn_leave_message_affirm,
            R.id.re_position})
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
                startActivityForResult(in, 0);
                break;
        }
    }

    /**
     * 从选择地址界面返回的数据显示在收货地址上
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String name = data.getStringExtra("name");
        String tel = data.getStringExtra("tel");
        String address = data.getStringExtra("address");
        Log.w("看啊看看看看看看看看那", "name =" + name + " tel =" + tel + " add =" + address);
        tvConsigneeName.setText(name);
        tvConsigneePhone.setText(tel);
        tvConsigneeDress.setText(address);
    }

    @OnClick(R.id.btn_ensure_order)
    public void onClick() {
        postOrder();
//        if (radiobuttonZfb.isChecked() == true) {//接入支付宝，由于不是商家支付宝账户所以无法完成支付
//            AliPay.Builder builder = new AliPay.Builder(this);
//            builder.setSELLER("17090403292")//卖家支付宝账号
//                    .setPARTNER("2088702803595690")//签约合作人身份PID
//                    .setRSA_PRIVATE(RSA_PRIVATE)//商户私钥
//                    .setPrice("0.01")//商品价格
//                    .setRSA_PUBLIC
//
// ("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB")
//                    .setNotifyURL("")//通知网址（可以不填）
//                    .setSubTitle("商品详情")//商品详情
//                    .setOrderTitle("测试商品")//商品名称
//                    .setPayCallBackListener(new AliPay.Builder.PayCallBackListener() {
//                        @Override
//                        public void onPayCallBack(int status, String resultStatus, String
//                                progress) {
//                            Toast.makeText(EnsureOrderActivity.this, progress, Toast
//                                    .LENGTH_SHORT).show();
//                        }
//                    });
//            builder.pay();
//        } else if (radiobuttonWx.isChecked() == true) {//微信支付由于需要收费，所以就没有开发。如果可以支付300元的话就可以了
//            Toast.makeText(EnsureOrderActivity.this, "微信支付正在开发中，请选择其他支付方式", Toast.LENGTH_SHORT)
//                    .show();
//        } else {
//            Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
//        }
////        Intent intent = new Intent();
////        intent.setClass(EnsureOrderActivity.this, OrderDetailsMainActivity.class);
////        startActivity(intent);
    }

    /**
     * 提交购买乐器信息，确认支付
     */
    private void postOrder() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = sDateFormat.format(new java.util.Date());
        Log.w("时间是啊金时间时间时间", date);

        ArrayList<EnsureOrderPost.Orderslist> orderslist = new ArrayList<EnsureOrderPost
                .Orderslist>();
        EnsureOrderPost.Orderslist orders = new EnsureOrderPost.Orderslist(1, tvPropertyEO
                .getText().toString());
        orderslist.add(orders);
        //int user_id, String code, Double price, Double method, String
        // situation, String message, int date, int order_num, int receiverAddressID,
        Double price = Double.valueOf(tvTotalMoney.getText().toString().trim());
        String method = "alipay".toString();
        String situation = "未支付".toString();
        String message = tvClickToLeaveMessage.getText().toString().trim();
        Log.w("提交的信息", price + method + situation + message);
        int ordernum = 1;
        int receiverAddressID = 1;

        OkHttpUtils
                .postString()
                .url(ENSURE_ORDER_URL)
                .content(new Gson().toJson(new EnsureOrderPost(1, "2071", price, method,
                        situation, message, date, ordernum, receiverAddressID, orderslist)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("失败", "提交失败");
                        Toast.makeText(EnsureOrderActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            String result = jsonobject.getString("result");
                            Log.w("看这里onResponse", "   " + result);
                            if (result.equals("yes")) {
                                Toast.makeText(EnsureOrderActivity.this, "提交成功", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    private void getOrder() {
        String url = baseUrl + "";
        postString()
                .url(url)
                .content(new Gson().toJson(new OrderDetails(UserId, "1008")))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("SUCCESS", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray1 = jsonObject.getJSONArray("Content");
                            JSONObject order1 = jsonArray1.getJSONObject(0);
                            String Recipient = order1.getString("Recipient");
                            tvConsigneeName.setText(Recipient);
                            String Telephone = order1.getString("Telephone");
                            tvConsigneePhone.setText(Telephone);
                            String Address = order1.getString("Address");
                            tvConsigneeDress.setText(Address);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private class OrderDetails {
        private String code;
        private int userId;

        public OrderDetails(int userId, String code) {
            this.code = code;
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "InsDetails{" +
                    "User_id='" + userId + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }


}
