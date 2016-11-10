package com.example.tu4.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.course.SubjectDetailActivity;
import com.example.tu4.adapter.BookingOrderAdapter;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.adapter.SubjectListviewAdapter;
import com.example.tu4.adapter.TransactionRecordsAdapter;
import com.example.tu4.bean.BookingOrder;
import com.example.tu4.bean.BookingOrderPost;
import com.example.tu4.bean.CalssList;
import com.example.tu4.bean.ClassListDetails;
import com.example.tu4.bean.ClassShowPost;
import com.example.tu4.bean.SystemInformationPost;
import com.example.tu4.bean.TransactionRecords;
import com.example.tu4.okhttp.JsonGenericsSerializator;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;
import com.example.tu4.view.ResolveConflictsScoolviewListview;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.BOOKING_ORDER_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.SUBJECT_FRAGMENT_URL;
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
    @BindView(R.id.gridview_serach)
    ResolveConflictsScoolviewGridview gridviewSerach;
    @BindView(R.id.img_actionbar_serach)
    ImageView imgActionbarSerach;
    @BindView(R.id.linearlayout_topmenu_order)
    LinearLayout linearlayoutTopmenuOrder;
    @BindView(R.id.linearlayout_topmenu_record)
    LinearLayout linearlayoutTopmenuRecord;
    @BindView(R.id.listview_order_serach)
    ResolveConflictsScoolviewListview listviewOrderSerach;
    @BindView(R.id.listview_record_serach)
    ResolveConflictsScoolviewListview listviewRecordSerach;
    @BindView(R.id.edt_topbar_serach)
    EditText edtTopbarSerach;
    @BindView(R.id.listview_subject_serach)
    ResolveConflictsScoolviewListview listviewSubjectSerach;
    @BindView(R.id.scrollview_search)
    ScrollView scrollviewSearch;


    private ArrayList<Map<String, String>> OrderlistData;
    private Map<String, String> OrdermapData;
    private ArrayList<Map<String, String>> RecordlistData;
    private Map<String, String> RecordmapData;
    private String situation;
    private List<List<ClassListDetails>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        getListDataByUrl();
        getOrderDataByUrl();
        getDataRecordByUrl();
        initGridview();
        Intent intent = getIntent();
        //判断是否是搜索请求
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //获取搜索的查询内容（关键字）
            String query = intent.getStringExtra(SearchManager.QUERY);
            //执行相应的查询动作
//            doMySearch(query);
        }
    }

    @Override
    public boolean onSearchRequested() {
        Bundle appData = new Bundle();
        appData.putBoolean(edtTopbarSerach.getText().toString(), true);
        startSearch(null, false, appData, false);
        return true;
    }

    //从网上获取列表内容并显示在列表中
    public void getListDataByUrl() {
        OkHttpUtils
                .postString()
                .url(SUBJECT_FRAGMENT_URL)//
                .content(new Gson().toJson(new ClassShowPost(UserId, "student", "9527", 0)))
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(), "列表内容获取失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            Gson gson = new Gson();
                            JSONObject jsonObject = new JSONObject(response);
                            CalssList calssList = gson.fromJson(response, CalssList.class);
                            List<ClassListDetails> classListDetailses = new ArrayList<>();
                            classListDetailses = calssList.getList();
                            if (classListDetailses == null) {
                                Toast.makeText(getApplicationContext(), "列表内容为空", Toast.LENGTH_SHORT).show();
                            }
                            for (int i = 0; i < classListDetailses.size(); i++) {

                                data.add(classListDetailses);
                            }
                            BaseAdapter adapter = new SubjectListviewAdapter(getApplicationContext(), data);
                            listviewSubjectSerach.setAdapter(adapter);
                            listviewSubjectSerach.setOnItemClickListener(new AdapterView
                                    .OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int
                                        position, long id) {

                                    Intent intent = new Intent(getApplicationContext(),
                                            SubjectDetailActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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
                        OrderlistData = new ArrayList<Map<String, String>>();
                        List<BookingOrder.BookingOrderinfo> list = response.getList();
                        for (int i = 0; i < list.size(); i++) {
                            OrdermapData = new HashMap<String, String>();
                            if (list.get(i).getSituation() == 1) {
                                situation = "未上课".toString();
                            } else if (list.get(i).getSituation() == 0) {
                                situation = "已完成".toString();
                            } else {
                                situation = "已上了" + (i - 1) + "课时".toString();
                            }
                            OrdermapData.put("situation", situation);
                            OrdermapData.put("class_name", list.get(i).getClass_name());
                            OrdermapData.put("class_time", String.valueOf(list.get(i).getClass_time()));
                            OrdermapData.put("teacher_name", list.get(i).getTeacher_name());
                            OrdermapData.put("class_price", String.valueOf(list.get(i).getClass_price
                                    ()));
                            OrdermapData.put("class_pic_url", list.get(i).getClass_pic_url());
                            OrdermapData.put("date", list.get(i).getDate());
                            OrderlistData.add(OrdermapData);
                            Log.w("listData----->order", OrdermapData.toString());
                            BookingOrderAdapter adapter = new BookingOrderAdapter(SearchActivity
                                    .this, OrderlistData);
                            listviewOrderSerach.setAdapter(adapter);
                        }
                    }

                });
    }

    private void getDataRecordByUrl() {
        RecordlistData = new ArrayList<>();
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
                            RecordmapData = new HashMap<String, String>();
                            RecordmapData.put("date", order_list.get(i).getDate());
                            RecordmapData.put("price", order_list.get(i).getPrice() + "");
                            RecordmapData.put("situation", order_list.get(i).getSituation());
                            RecordmapData.put("freigh", order_list.get(i).getFreigh() + "");
                            RecordmapData.put("pic_url", list.get(0).getPic_url());
                            RecordmapData.put("now_price", list.get(0).getNow_price() + "");
                            RecordmapData.put("name", list.get(0).getName());
                            RecordmapData.put("type", list.get(0).getType());
                            RecordlistData.add(RecordmapData);
                        }
                        listviewRecordSerach.setAdapter(new TransactionRecordsAdapter(SearchActivity.this, RecordlistData));
                        Log.w("成功", "这是listDate = " + RecordlistData.toString());
                    }

                });
    }


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

                int[] orderLocation = new int[2];
                listviewOrderSerach.getLocationOnScreen(orderLocation);
                scrollviewSearch.scrollTo(0,orderLocation[1]);
                break;
            case R.id.linearlayout_topmenu_record:
//                Intent intent1 = new Intent(this, TransactionRecordsActivity.class);
//                startActivity(intent1);
                listviewRecordSerach.scrollTo(0, 20);
                break;
        }
    }
}
