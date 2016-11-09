package com.example.tu4.activity.instrument;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.course.payment.EnsureOrderActivity;
import com.example.tu4.adapter.ChooseAddressListViewAdapter;
import com.example.tu4.bean.AddressDetails;
import com.example.tu4.bean.AddressInfo;
import com.example.tu4.view.ResolveConflictsScoolviewListview;
import com.example.tu4.view.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.CHOOSE_DERSS_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 选择收货地址
 * Version: 1
 * Modify Person : Moofei
 */
public class SelectDressActivity extends AppCompatActivity {
    @BindView(R.id.rl_S_D)
    TitleView rlSD;
    @BindView(R.id.lv_select_dress)
    ResolveConflictsScoolviewListview lvSelectDress;
    @BindView(R.id.btn_add_dress)
    Button btnAddDress;
    private List<List<AddressDetails>> data = new ArrayList<>();
    List<AddressDetails> addressDetailses = new ArrayList<AddressDetails>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_serect_dress);
        Resources res = getResources();
        String title = "选择地址".toString();
        ButterKnife.bind(this);
        getOrder();
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        rlSD.setImgLeft(ic_return);
        rlSD.getImgLeft().setVisibility(View.VISIBLE);
        rlSD.setTitleText(title);rlSD.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDressActivity.this.finish();
            }
        });

    }
    private void getOrder() {
        OkHttpUtils
                .postString()
                .url(CHOOSE_DERSS_URL)
                .content(new Gson().toJson(new OrderDetails(UserId, "1008")))
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("error", e);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.w("SUCCESS", response);
                        Gson gson = new Gson();
                        AddressInfo addressInfo = gson.fromJson(response, AddressInfo.class);
                        addressDetailses = addressInfo.getContent();
                        if (addressDetailses == null) {
                            Toast.makeText(SelectDressActivity.this, "收货地址为空，请添加一个收货地址吧", Toast.LENGTH_SHORT).show();
                        } else {
                            for (int i = 0; i < addressDetailses.size(); i++) {
                                data.add(addressDetailses);
                            }
                            final ChooseAddressListViewAdapter chooseAddressAdapter = new ChooseAddressListViewAdapter(getApplicationContext(), data);
                            lvSelectDress.setAdapter(chooseAddressAdapter);
                            lvSelectDress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    chooseAddressAdapter.setcheck(position);
                                    Intent in = new Intent();
                                    in.putExtra("name",addressDetailses.get(position).getRecipient());
                                    in.putExtra("tel",addressDetailses.get(position).getTelephone());
                                    in.putExtra("address",addressDetailses.get(position).getAddress());
                                    in.setClass(getApplicationContext(), EnsureOrderActivity.class);
                                    SelectDressActivity.this.setResult(0, in);
                                    finish();
                                    //startActivity(in);
                                }
                            });

                        }
                    }
                });
    }

    @OnClick(R.id.btn_add_dress)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(SelectDressActivity.this, EditDressActivity.class);
        startActivity(intent);
        finish();
    }

    private class OrderDetails {
        private String code;
        private int User_id;

        public OrderDetails(int User_id, String code) {
            this.code = code;
            this.User_id = User_id;
        }

        @Override
        public String toString() {
            return "InsDetails{" +
                    "User_id='" + User_id + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

}
