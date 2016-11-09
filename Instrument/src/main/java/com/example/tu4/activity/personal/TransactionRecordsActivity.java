package com.example.tu4.activity.personal;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.adapter.TransactionRecordsAdapter;
import com.example.tu4.bean.SystemInformationPost;
import com.example.tu4.bean.TransactionRecords;
import com.example.tu4.okhttp.JsonGenericsSerializator;
import com.example.tu4.view.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.TRANSACTION_RECORDS_URL;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 查看订单,交易记录
 * Version: 1
 * Modify Person : Moofei
 */
public class TransactionRecordsActivity extends AppCompatActivity {

    @BindView(R.id.rl_TR)
    TitleView rlTR;
    private ListView mListView;
    private ArrayList<Map<String, String>> listData;
    private Map<String, String> mapData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_transaction_records);
        Resources res = getResources();
        String title = "交易记录".toString();
        ButterKnife.bind(this);
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        Drawable ic_search = res.getDrawable(R.mipmap.lookup);
        rlTR.setImgLeft(ic_return);
        rlTR.setImgRight2(ic_search);
        rlTR.getImgLeft().setVisibility(View.VISIBLE);
        rlTR.getImgRight2().setVisibility(View.VISIBLE);
        rlTR.setTitleText(title);
        rlTR.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionRecordsActivity.this.finish();
            }
        });
        rlTR.setImgRight2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(TransactionRecordsActivity.this, SearchActivity.class);
                startActivity(in);
                finish();
            }
        });
        mListView = (ListView) findViewById(R.id.lv_TR_parent);
        getDataByUrl();
    }

    private void getDataByUrl() {
        listData = new ArrayList<>();
        OkHttpUtils
                .postString()
                .url(TRANSACTION_RECORDS_URL)
                .content(new Gson().toJson(new SystemInformationPost(1, "2009", "student")))
                .build()
                .execute(new GenericsCallback<TransactionRecords>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("失败", e.getMessage());
                        Toast.makeText(TransactionRecordsActivity.this, "获取数据失败", Toast
                                .LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onResponse(TransactionRecords response, int id) {
                        List<TransactionRecords.OrderList> order_list = response.getOrder_list();
                        for (int i = 0; i < order_list.size(); i++) {
                            List<TransactionRecords.TransList> list = order_list.get(i).getList();
                            mapData = new HashMap<String, String>();
                            mapData.put("date", order_list.get(i).getDate());
                            mapData.put("price", order_list.get(i).getPrice() + "");
                            mapData.put("situation", order_list.get(i).getSituation());
                            mapData.put("freigh", order_list.get(i).getFreigh() + "");
                            mapData.put("pic_url", list.get(0).getPic_url());
                            mapData.put("now_price", list.get(0).getNow_price() + "");
                            mapData.put("name", list.get(0).getName());
                            mapData.put("type", list.get(0).getType());
                            listData.add(mapData);
                        }
                        mListView.setAdapter(new TransactionRecordsAdapter(TransactionRecordsActivity.this,listData));
                        Log.w("成功", "这是listDate = " + listData.toString());
                    }

                });
    }

}
