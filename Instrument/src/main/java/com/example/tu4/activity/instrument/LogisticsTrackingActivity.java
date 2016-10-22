package com.example.tu4.activity.instrument;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.adapter.LogisticsTrackingListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogisticsTrackingActivity extends AppCompatActivity {

    @BindView(R.id.listview_logistics_tracking)
    ListView listviewLogisticsTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_tracking);
        ButterKnife.bind(this);
        initListView();
    }

    public void initListView() {
        LogisticsTrackingListViewAdapter logisticsTrackingListViewAdapter =
                new LogisticsTrackingListViewAdapter(getBaseContext());
        listviewLogisticsTracking.setAdapter(logisticsTrackingListViewAdapter);
    }
}
