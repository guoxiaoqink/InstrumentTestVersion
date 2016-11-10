package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.course.SubjectDetailActivity;
import com.example.tu4.activity.personal.BookingOrderActivity;
import com.example.tu4.activity.personal.TransactionRecordsActivity;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.adapter.SerachOrderListviewAdapter;
import com.example.tu4.adapter.SubjectListviewAdapter;
import com.example.tu4.bean.CalssList;
import com.example.tu4.bean.ClassListDetails;
import com.example.tu4.bean.ClassShowPost;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.SUBJECT_FRAGMENT_URL;
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
//    @BindView(R.id.linearlayout_topmenu_order)
//    LinearLayout linearlayoutTopmenuOrder;

    @BindView(R.id.img_topmenu_record)
    ImageView imgTopmenuRecord;
    @BindView(R.id.textview_topmenu_record)
    TextView textviewTopmenuRecord;
//    @BindView(R.id.linearlayout_topmenu_record)
//    LinearLayout linearlayoutTopmenuRecord;

    @BindView(R.id.listview_subject_serach)
    ListView listviewSubjectSerach;
    @BindView(R.id.listview_order_serach)
    ListView listviewOrderSerach;
    @BindView(R.id.listview_record_serach)
    ListView listviewRecordSerach;
    @BindView(R.id.gridview_serach)
    ResolveConflictsScoolviewGridview gridviewSerach;
    @BindView(R.id.img_actionbar_serach)
    ImageView imgActionbarSerach;
    @BindView(R.id.linearlayout_topmenu_order)
    LinearLayout linearlayoutTopmenuOrder;
    @BindView(R.id.linearlayout_topmenu_record)
    LinearLayout linearlayoutTopmenuRecord;

    //    private List<String> ImageCricleViewList_image = new ArrayList<>();
//    private List<String> ImageCricleViewList_name = new ArrayList<>();
    private List<List<ClassListDetails>> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        getListDataByUrl();
//        Toast.makeText(this, "ceshi", Toast.LENGTH_SHORT).show();
//        initListviewSubject();
        initGridview();
        initListviewOrder();
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
    public void initListviewOrder() {
        SerachOrderListviewAdapter adapter = new SerachOrderListviewAdapter(getBaseContext());
        listviewOrderSerach.setAdapter(adapter);
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
                        Toast.makeText(SearchActivity.this, "列表内容获取失败", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(SearchActivity.this, "列表内容为空", Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.linearlayout_topmenu_order, R.id.linearlayout_topmenu_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout_topmenu_order:
                Intent intent = new Intent(this, BookingOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.linearlayout_topmenu_record:
                Intent intent1 = new Intent(this, TransactionRecordsActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
