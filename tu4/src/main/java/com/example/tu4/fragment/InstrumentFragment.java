package com.example.tu4.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.tu4.R;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.adapter.InstrumentListViewAlbumAdapter;
import com.example.tu4.adapter.InstrumentListViewInstruDetialAdapter;
import com.example.tu4.adapter.InstrumentRecyclerAdapter;
import com.example.tu4.view.ImageCyclePlayView;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;
import com.example.tu4.view.ResolveConflictsScoolviewListview;

import static com.example.tu4.temporarydata.AplicationStatic.cycleImageData;
import static com.example.tu4.temporarydata.AplicationStatic.datalistInstrumentDetail;
import static com.example.tu4.temporarydata.AplicationStatic.instrumentClassify;

public class InstrumentFragment extends Fragment {

    @BindView(R.id.icpv_instrumentFragment)
    ImageCyclePlayView icpvInstrumentFragment;

    @BindView(R.id.recycleView_instrument_instrument)
    RecyclerView recycleViewInstrumentInstrument;

    @BindView(R.id.listview_instument_Album)
    ResolveConflictsScoolviewListview listviewInstumentAlbum;
    @BindView(R.id.listview2_instument_money)
    ResolveConflictsScoolviewListview listviewInstumentMoney;

    @BindView(R.id.gridview_instrument_instrument)
    ResolveConflictsScoolviewGridview gridviewInstrumentInstrument;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruments, container, false);
        ButterKnife.bind(this, view);

        initImageCyclePlayView();
        initRecyclerView();
        initGridview();
        initlistviewInstumentMoney();
        initlistviewInstumentAlbum();
        return view;
    }

    /*  @BindViews({ R.id.first_name, R.id.middle_name, R.id.last_name })
      List<EditText> nameViews;*/

    /*
    * 初始化轮播图
    * */
    public void initImageCyclePlayView() {
        icpvInstrumentFragment.loadData(cycleImageData(), new ImageCyclePlayView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCyclePlayView.ImageInfo imageInfo) {
                //本地图片
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
                return imageView;
            }
        });
    }

    private void initlistviewInstumentMoney() {
        InstrumentListViewInstruDetialAdapter list2 = new InstrumentListViewInstruDetialAdapter(getContext());
        listviewInstumentMoney.setAdapter(list2);
    }

    private void initlistviewInstumentAlbum() {
        InstrumentListViewAlbumAdapter list1 = new InstrumentListViewAlbumAdapter(getContext());
        listviewInstumentAlbum.setAdapter(list1);
    }

    private void initGridview() {
        InstrumentGridviewAdapter adapter = new InstrumentGridviewAdapter(datalistInstrumentDetail(), getContext());
        gridviewInstrumentInstrument.setAdapter(adapter);
        gridviewInstrumentInstrument.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            }
        });
    }
    /*
    * RecyclerView的一系列操作
    * */
    private void initRecyclerView() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleViewInstrumentInstrument.setLayoutManager(linearLayoutManager);
        //设置适配器
        InstrumentRecyclerAdapter RecyclerAdapter = new InstrumentRecyclerAdapter(getContext(), instrumentClassify());
        recycleViewInstrumentInstrument.setAdapter(RecyclerAdapter);
    }

}
