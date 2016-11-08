package com.example.tu4.activity.instrument;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.tu4.R;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.bean.InstrumentDetails;
import com.example.tu4.utils.ApplicationStaticConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.tu4.utils.ApplicationStaticConstants.listDataIns;
import static com.example.tu4.utils.ApplicationStaticConstants.listDataclasscify;

/**
 * Created by gxq on
 * Descripyion: 乐器分类界面
 * Version：1
 * Modify Person：gxq
 */
public class InstrumentClassifyActivity extends AppCompatActivity {

    GridView mGridview;
    TabLayout mTabLayout;
    RecyclerView mRecyclerView;
    MaterialRefreshLayout mMaterlayout;
    InstrumentGridviewAdapter mGridviewAdapter;

    List<String> mTablayoutClassify;
    List<InstrumentDetails> mGridviewInstrumentDetail = new ArrayList<InstrumentDetails>();
    List<List<InstrumentDetails>> listInstrument = new ArrayList<List<InstrumentDetails>>();
    Boolean isLoadMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_inatrument_classify);

        mGridview = (GridView) findViewById(R.id.gridview_instrument_classifyActivity);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_instrument_classify);
        mMaterlayout = (MaterialRefreshLayout) findViewById(R.id.materialrefresh_classfific);
        // mRecyclerView = (RecyclerView) findViewById(R.id.recycleView_instrument_classify);
        initAllData();//初始化所有数据
        initGridview();//初始化gridview
        initTabLayout();
        initMaterialRefreshLayout();//刷新
        // initRecycle();
        // initRecycle();
    }

    /*
    *初始化刷新的数据
    * */
    public void initMaterialRefreshLayout() {
        mMaterlayout.setLoadMore(isLoadMore);
        mMaterlayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                /**
                 * 刷新的方法，我这里演示的是下拉刷新，因为没有数据，我这里也就只是toast一下
                 * 如果想要实现你们自己要的结果，就会在定义一个方法，获取最新数据，或者是在次
                 * 在这里调用之前获取数据的方法，以达到刷新数据的功能
                 * @param materialRefreshLayout
                 */
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(InstrumentClassifyActivity.this,
                                getResources().getString(R.string.Refresh),
                                Toast.LENGTH_SHORT).show();
                        /**
                         * 刷新完成后调用此方法，要不然刷新效果不消失
                         */
                        mMaterlayout.finishRefresh();
                    }
                }, 3000);
            }

            /**
             * 上拉加载更多的方法，在这里我只是简单的模拟了加载四条数据
             * 真正用的时候，就会去定义方法，获取数据，一般都是分页，在数据端获取的时候
             * 把页数去增加一，然后在去服务端去获取数据
             * @param materialRefreshLayout
             */

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //               super.onRefreshLoadMore(materialRefreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMore = false;
                       /* for (int a = 0; a < 3; a++) {
                            InstrumentDetails commod = new InstrumentDetails(R.mipmap
                            .instrument_instrument, "刷新新加的数据", "￥ 15.00", "30.00");
                            mInstrumentDetail.add(commod);
                        }*/
                        //通知刷新
                        mGridviewAdapter.notifyDataSetChanged();
                        //mRecyclerView.scrollToPosition(mAdapter.getLists().size());
                        /**
                         * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
                         */
                        mMaterlayout.finishRefreshLoadMore();
                    }
                }, 3000);
            }
        });
    }

    /*
    * 初始化tablayout
    * */
    public void initTabLayout() {

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < listDataclasscify.size(); i++) {
            //添加tab
            mTabLayout.addTab(mTabLayout.newTab().setText(listDataclasscify.get(i)));
        }

        mTabLayout.getTabAt(ApplicationStaticConstants.chooseRecycleView).select();

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(InstrumentClassifyActivity.this,
                        tab.getPosition() + ":" + tab.getText(), Toast.LENGTH_SHORT).show();
                changeGridviewDate(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    /*
    * 初始化gridview  的数据以及 适配器
    * */
    public void initGridview() {
        mGridviewInstrumentDetail = new ArrayList<InstrumentDetails>();
        mGridviewInstrumentDetail.clear();
        mGridviewAdapter = new InstrumentGridviewAdapter(listDataIns, this);
        mGridview.setAdapter(mGridviewAdapter);
        changeGridviewDate(ApplicationStaticConstants.chooseRecycleView);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    /*
    * 存放此页面中所有的数据,换成jason数据从这里边换到这里边
    * */
    public void initAllData() {
        /*
        * tablayout的数据
        * */
        mTablayoutClassify = new ArrayList<String>();
        mTablayoutClassify.add(getResources().getString(R.string.all));
        for (int i = 0; i < 10; i++) {
            mTablayoutClassify.add(getResources().getString(R.string.classify) + i);
        }
        for (int i = 0; i < mTablayoutClassify.size(); i++) {
            ArrayList<InstrumentDetails> arrayList = new ArrayList<InstrumentDetails>();
            for (int j = 0; j < new Random().nextInt(10); j++) {
                InstrumentDetails commod = new InstrumentDetails(R.mipmap.instrument_instrument,
                        "第" + i + "个页面", "", "");
                arrayList.add(commod);
            }
            listInstrument.add(arrayList);
        }
    }

    public void changeGridviewDate(int classifyNumber) {
        mGridviewInstrumentDetail.clear();
        mGridviewInstrumentDetail.addAll(listInstrument.get(classifyNumber));
        mGridviewAdapter.notifyDataSetChanged();
    }

}
