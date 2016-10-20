package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.adapter.OrderDetailsListviewAdapter;
import com.example.tu4.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsMainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.btn_payment_top)
    Button btnPaymentTop;
    @BindView(R.id.rl_payment)
    RelativeLayout rlPayment;
    @BindView(R.id.image_listItem)
    ImageView imageListItem;
    @BindView(R.id.tv_express)
    TextView tvExpress;
    @BindView(R.id.rl_express)
    RelativeLayout rlExpress;
    @BindView(R.id.tv_line_Tel)
    TextView tvLineTel;
    @BindView(R.id.iv_adress)
    ImageView ivAdress;
    @BindView(R.id.tv_consignee)
    TextView tvConsignee;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.textview_line_10_1)
    TextView textviewLine101;
    @BindView(R.id.rl_address_consignee)
    RelativeLayout rlAddressConsignee;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.textview_line_2)
    TextView textviewLine2;
    @BindView(R.id.tv_freight_text)
    TextView tvFreightText;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_actual_text)
    TextView tvActualText;
    @BindView(R.id.tv_actual)
    TextView tvActual;
    @BindView(R.id.textview_line_3)
    TextView textviewLine3;
    @BindView(R.id.tv_buyer_message)
    TextView tvBuyerMessage;
    @BindView(R.id.textview_line_10_2)
    TextView textviewLine102;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_found_time)
    TextView tvFoundTime;
    @BindView(R.id.tv_payment_time)
    TextView tvPaymentTime;
    @BindView(R.id.tv_delivery_time)
    TextView tvDeliveryTime;
    @BindView(R.id.tv_receiving_time)
    TextView tvReceivingTime;
    @BindView(R.id.textview_line_10_3)
    TextView textviewLine103;
    @BindView(R.id.tv_return_address)
    TextView tvReturnAddress;
    @BindView(R.id.textview_line_10_4)
    TextView textviewLine104;
    @BindView(R.id.rl_freight)
    RelativeLayout rlFreight;
    @BindView(R.id.btn_cancel_order)
    Button btnCancelOrder;
    @BindView(R.id.btn_return_it)
    Button btnReturnIt;
    @BindView(R.id.btn_payment)
    Button btnPayment;
    @BindView(R.id.btn_recrive)
    Button btnRecrive;
    @BindView(R.id.textview_line_10_5)
    TextView textviewLine105;
    @BindView(R.id.rb_paytype_of_weixin)
    RadioButton rbPaytypeOfWeixin;
    @BindView(R.id.ll_paytype_of_weixin)
    LinearLayout llPaytypeOfWeixin;
    @BindView(R.id.rb_paytype_of_zhifubao)
    RadioButton rbPaytypeOfZhifubao;
    @BindView(R.id.ll_paytype_of_zhifubao)
    LinearLayout llPaytypeOfZhifubao;
    @BindView(R.id.ll_paychoice)
    LinearLayout llPaychoice;
    @BindView(R.id.tv_cencle)
    TextView tvCencle;
    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;
    @BindView(R.id.rl_pay_or_cencle)
    RelativeLayout rlPayOrCencle;
    private ListView listView;
    private OrderDetailsListviewAdapter adapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_activity_main);
        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.listview);
        initdata();
        /*adapter = new OrderDetailsListviewAdapter(this, users);
        listView.setAdapter(adapter);*/
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_address_consignee);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsMainActivity.this, SelectDressActivity.class);
                startActivity(intent);
            }
        });
    }

    //为了测试，特地将不同的布局的数据混乱的添加到list里
    private void initdata() {
        users = new ArrayList<User>();
        users.add(new User("乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX", null, null));
        users.add(new User("乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX", null, null));
        users.add(new User("乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX", null, null));


    }

    @OnClick(R.id.iv_return)
    public void onClick() {
        this.finish();
    }
}
