package com.example.tu4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.adapter.SerachOrderListviewAdapter;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.linearlayout_topmenu_order)
    LinearLayout linearlayoutTopmenuOrder;

    @BindView(R.id.img_topmenu_record)
    ImageView imgTopmenuRecord;
    @BindView(R.id.textview_topmenu_record)
    TextView textviewTopmenuRecord;
    @BindView(R.id.linearlayout_topmenu_record)
    LinearLayout linearlayoutTopmenuRecord;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

//        initListviewSubject();
        initGridview();
        initListviewOrder();
    }

    /*
    *
    * */
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
}
