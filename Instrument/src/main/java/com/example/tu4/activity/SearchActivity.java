package com.example.tu4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.BookingOrderAdapter;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.adapter.TransactionRecordsAdapter;
import com.example.tu4.bean.BookingOrder;
import com.example.tu4.bean.BookingOrderPost;
import com.example.tu4.bean.SystemInformationPost;
import com.example.tu4.bean.TransactionRecords;
import com.example.tu4.okhttp.JsonGenericsSerializator;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;
import com.example.tu4.view.ResolveConflictsScoolviewListview;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.BOOKING_ORDER_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.TRANSACTION_RECORDS_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;
import static com.example.tu4.utils.ApplicationStaticConstants.listDataIns;

/**
 * Created by gxq on
 * Descripyion: 查询界面
 * Version：1
 * Modify Person：gxq
 */
public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.img_topmenu_subject)
    ImageView imgTopmenuSubject;
    @BindView(R.id.textview_topmenu_subject)
    TextView textviewTopmenuSubject;
    @BindView(R.id.linearlayout_topmenu_subject)
    LinearLayout linearlayoutTopmenuSubject;

    @BindView(R.id.img_topmenu_instrument)
    ImageView imgTopmenuInstrument;
    @BindView(R.id.textview_topmenu_instrument)
    TextView textviewTopmenuInstrument;
    @BindView(R.id.linearlayout_topmenu_instrument)
    LinearLayout linearlayoutTopmenuInstrument;

    @BindView(R.id.img_topmenu_order)
    ImageView imgTopmenuOrder;
    @BindView(R.id.textview_topmenu_order)
    TextView textviewTopmenuOrder;

    @BindView(R.id.img_topmenu_record)
    ImageView imgTopmenuRecord;
    @BindView(R.id.textview_topmenu_record)
    TextView textviewTopmenuRecord;

    @BindView(R.id.listview_subject_serach)
    ListView listviewSubjectSerach;

    @BindView(R.id.gridview_serach)
    ResolveConflictsScoolviewGridview gridviewSerach;
    @BindView(R.id.img_actionbar_serach)
    ImageView imgActionbarSerach;
    @BindView(R.id.linearlayout_topmenu_order)
    LinearLayout linearlayoutTopmenuOrder;
    @BindView(R.id.linearlayout_topmenu_record)
    LinearLayout linearlayoutTopmenuRecord;
    @BindView(R.id.listview_record_serach)
    ResolveConflictsScoolviewListview listviewRecordSerach;
    @BindView(R.id.listview_order_serach)
    ResolveConflictsScoolviewListview listviewOrderSerach;


    private ArrayList<Map<String, String>> listData;
    private Map<String, String> mapData;
    private String situation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

//        initListviewSubject();
        getOrderDataByUrl();
        getDataRecordByUrl();
        initGridview();
//        initListviewOrder();
    }

    private void getOrderDataByUrl() {
        OkHttpUtils
                .postString()
                .url(BOOKING_ORDER_URL)
                .content(new Gson().toJson(new BookingOrderPost(UserId, "2010")))
                .build()
                .execute(new GenericsCallback<BookingOrder>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("onError", "数据加载失败");
                        Toast.makeText(SearchActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(BookingOrder response, int id) {
                        listData = new ArrayList<Map<String, String>>();
                        List<BookingOrder.BookingOrderinfo> list = response.getList();
                        for (int i = 0; i < list.size(); i++) {
                            mapData = new HashMap<String, String>();
                            if (list.get(i).getSituation() == 1) {
                                situation = "未上课".toString();
                            } else if (list.get(i).getSituation() == 0) {
                                situation = "已完成".toString();
                            } else {
                                situation = "已上了" + (i - 1) + "课时".toString();
                            }
                            mapData.put("situation", situation);
                            mapData.put("class_name", list.get(i).getClass_name());
                            mapData.put("class_time", String.valueOf(list.get(i).getClass_time()));
                            mapData.put("teacher_name", list.get(i).getTeacher_name());
                            mapData.put("class_price", String.valueOf(list.get(i).getClass_price
                                    ()));
                            mapData.put("class_pic_url", list.get(i).getClass_pic_url());
                            mapData.put("date", list.get(i).getDate());
                            listData.add(mapData);
                            Log.w("listData", listData.toString());
                            BookingOrderAdapter adapter = new BookingOrderAdapter(SearchActivity
                                    .this, listData);
                            listviewOrderSerach.setAdapter(adapter);
                        }
                    }

                });
    }

    private void getDataRecordByUrl() {
        listData = new ArrayList<>();
        OkHttpUtils
                .postString()
                .url(TRANSACTION_RECORDS_URL)
                .content(new Gson().toJson(new SystemInformationPost(UserId, "2009", "student")))
                .build()
                .execute(new GenericsCallback<TransactionRecords>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("失败", e.getMessage());
                        Toast.makeText(SearchActivity.this, "获取数据失败", Toast
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
                        listviewRecordSerach.setAdapter(new TransactionRecordsAdapter
                                (SearchActivity.this, listData));
                        Log.w("成功", "这是listDate = " + listData.toString());
                    }

                });
    }

//    /*
//    *
//    * */
//    public void initListviewSubject() {
//        SubjectListviewAdapter adapter = new SubjectListviewAdapter(getBaseContext(),);
//        listviewSubjectSerach.setAdapter(adapter);
//    }

    /*
    * 初始化订单信息
    * */
//    public void initListviewOrder() {
//        SerachOrderListviewAdapter adapter = new SerachOrderListviewAdapter(getBaseContext());
//        listviewOrderSerach.setAdapter(adapter);
//    }

    /*
    *
    * */
    public void initGridview() {
        InstrumentGridviewAdapter mGridviewAdapter = new InstrumentGridviewAdapter(
                listDataIns, this);
        gridviewSerach.setAdapter(mGridviewAdapter);
        gridviewSerach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @OnClick(R.id.img_actionbar_serach)
    public void onClick() {
        this.finish();
    }

    @OnClick({R.id.linearlayout_topmenu_order, R.id.linearlayout_topmenu_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout_topmenu_order:
//                Intent intent = new Intent(this, BookingOrderActivity.class);
//                startActivity(intent);
                listviewOrderSerach.scrollTo(0, 20);
                break;
            case R.id.linearlayout_topmenu_record:
//                Intent intent1 = new Intent(this, TransactionRecordsActivity.class);
//                startActivity(intent1);
                listviewRecordSerach.scrollTo(0, 20);
                break;
        }
    }
}
