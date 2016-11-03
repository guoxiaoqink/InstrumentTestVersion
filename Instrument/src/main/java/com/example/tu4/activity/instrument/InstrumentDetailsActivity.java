package com.example.tu4.activity.instrument;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.course.payment.EnsureOrderActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.INS_DETAILS_URL;

/**
 * Created by scy on
 * Descripyion: 乐器详情界面
 * Version：1
 * Modify Person：gxq
 */
public class InstrumentDetailsActivity extends AppCompatActivity {
    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_instrument_name)
    TextView tvInstrumentName;
    @BindView(R.id.tv_instrument_level)
    TextView tvInstrumentLevel;
    @BindView(R.id.tv_old_level)
    TextView tvOldLevel;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.tv_parameter)
    TextView tvParameter;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tv_altogether)
    TextView tvAltogether;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.tv_canshu1)
    TextView tvCanshu1;
    @BindView(R.id.tv_canshu2)
    TextView tvCanshu2;
    @BindView(R.id.tv_canshu3)
    TextView tvCanshu3;
    @BindView(R.id.tv_moneyNum)
    TextView tvMoneyNum;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_details);
        ButterKnife.bind(this);
        textView = (TextView) findViewById(R.id.tv_old_level);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        getInsDetails();
    }

    @OnClick({R.id.img_return, R.id.btn_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                this.finish();
                break;
            case R.id.btn_buy:
                Intent intent = new Intent();
                intent.putExtra("Ins_name", tvInstrumentName.getText().toString());
                intent.putExtra("Freight", tvFreight.getText().toString());
                intent.putExtra("MoneyNum", tvMoneyNum.getText().toString());
                intent.putExtra("price", tvInstrumentLevel.getText().toString());
                intent.putExtra("para", tvCanshu1.getText().toString() + "   " + tvCanshu2.getText().toString());
                intent.setClass(InstrumentDetailsActivity.this, EnsureOrderActivity.class);
                startActivity(intent);
        }

    }

    private void getInsDetails() {
        OkHttpUtils.postString()
                .url(INS_DETAILS_URL)
                .content(new Gson().toJson(new InsDetails("1", "1005")))
                .tag(this)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(InstrumentDetailsActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("success", response);

                        System.out.print(1);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Instrument_name = jsonObject.getString("Instrument_name");
                            String Instrument_now_price = jsonObject.getString("Instrument_now_price");
                            String Instrument_pre_price = jsonObject.getString("Instrument_pre_price");
                            Double Freight = jsonObject.getDouble("Freight");
                            String Instrument_location = jsonObject.getString("Instrument_location");
                            JSONArray array = jsonObject.getJSONArray("Product");
                            JSONObject a = array.getJSONObject(0);
                            tvCanshu1.setText("参数1：" + a.getString("Product_Parameter"));
                            JSONObject b = array.getJSONObject(1);
                            tvCanshu2.setText("参数2：" + b.getString("Product_Parameter"));
                            tvInstrumentLevel.setText("¥" + Instrument_now_price);
                            tvOldLevel.setText("¥" + Instrument_pre_price);
                            tvInstrumentName.setText(Instrument_name);
                            tvFreight.setText("运费： " + Freight);
                            tvPlace.setText(Instrument_location);
                            double pNum = Integer.parseInt(Instrument_now_price) + Freight;
                            tvMoneyNum.setText("" + pNum);

//                            JSONArray Pic_url = jsonObject.getJSONArray("Pic_url");
//                            JSONObject Ins_pic1 = Pic_url.getJSONObject(0);
//                            String Ins_pic_url1 = Ins_pic1.getString("Ins_pic_url");
//                            Toast.makeText(InstrumentDetailsActivity.this, Ins_pic_url1, Toast.LENGTH_SHORT).show();
//                            Log.d("Ins_pic_url1",Ins_pic_url1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private class InsDetails {

        private String Ins_id;
        private String code;

        public InsDetails(String Ins_id, String code) {
            this.Ins_id = Ins_id;
            this.code = code;
        }

        @Override
        public String toString() {
            return "InsDetails{" +
                    "Ins_id='" + Ins_id + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}
