package com.example.tu4.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.AdvertisementActivity;
import com.example.tu4.activity.instrument.InstrumentDetailsActivity;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.adapter.InstrumentListViewAlbumAdapter;
import com.example.tu4.adapter.InstrumentListViewInstruDetialAdapter;
import com.example.tu4.adapter.InstrumentRecyclerAdapter;
import com.example.tu4.bean.AutoPlayInfo;
import com.example.tu4.bean.ImageCircleView;
import com.example.tu4.bean.ImageViewInfo;
import com.example.tu4.bean.SlideView;
import com.example.tu4.view.AutoPlayingViewPager;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;
import com.example.tu4.view.ResolveConflictsScoolviewListview;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.model.AplicationStatic.UserId;
import static com.example.tu4.model.AplicationStatic.datalistInstrumentDetail;
import static com.example.tu4.model.AplicationStatic.instrumentClassify;
import static com.example.tu4.model.IUrl.baseUrl;

/**
 * Created by gxq
 * Descripyion: 主界面的乐器界面
 * Version：1
 * Modify Person：gxq
 */
public class InstrumentFragment extends Fragment {

//    @BindView(R.id.icpv_instrumentFragment)
//    ImageCyclePlayView icpvInstrumentFragment;

    @BindView(R.id.recycleView_instrument_instrument)
    RecyclerView recycleViewInstrumentInstrument;

    @BindView(R.id.listview_instument_Album)
    ResolveConflictsScoolviewListview listviewInstumentAlbum;
    @BindView(R.id.listview2_instument_money)
    ResolveConflictsScoolviewListview listviewInstumentMoney;

    @BindView(R.id.gridview_instrument_instrument)
    ResolveConflictsScoolviewGridview gridviewInstrumentInstrument;
    @BindView(R.id.imageview_instrument_show)
    ImageView imageviewInstrumentShow;
    @BindView(R.id.auto_play_viewpager)
    AutoPlayingViewPager autoPlayViewpager;
    private List<String> ImageCricleViewList_image = new ArrayList<>();
    private List<String> ImageCricleViewList_name = new ArrayList<>();

    private List<AutoPlayInfo> mAutoPlayInfoList;
    private AutoPlayingViewPager.OnPageItemClickListener onPageItemClickListener = new AutoPlayingViewPager.OnPageItemClickListener() {

        @Override
        public void onPageItemClick(int position, String adLink) {
            // 直接返回链接,使用WebView加载
            if (!TextUtils.isEmpty(adLink)) {
                //链接存在时才进行下一步操作,当然，这只是简单判断,这个字符串不是正确链接,则需要加上正则表达式判断。
                Intent intent = new Intent(getContext(),
                        AdvertisementActivity.class);
                intent.putExtra("linkPath", adLink);
                startActivity(intent);
                Toast.makeText(getContext(), "ceshi", Toast.LENGTH_SHORT).show();
            }
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruments, container, false);
        ButterKnife.bind(this, view);

//        initImageCyclePlayView();
        initRecyclerView();
        initGridview();
        initlistviewInstumentMoney();
        initlistviewInstumentAlbum();
        return view;
    }

    /**
     * 乐器介绍lstview
     */
    private void initlistviewInstumentMoney() {
        InstrumentListViewInstruDetialAdapter list2 = new InstrumentListViewInstruDetialAdapter(
                getContext());
        listviewInstumentMoney.setAdapter(list2);
    }

    /**
     * 专辑栏的listview
     */
    private void initlistviewInstumentAlbum() {
        InstrumentListViewAlbumAdapter list1 = new InstrumentListViewAlbumAdapter(getContext());
        listviewInstumentAlbum.setAdapter(list1);
    }

    /*
    * 乐器展示的gridview
    * */
    private void initGridview() {
        InstrumentGridviewAdapter adapter = new InstrumentGridviewAdapter(
                datalistInstrumentDetail(), getContext());
        gridviewInstrumentInstrument.setAdapter(adapter);
        gridviewInstrumentInstrument.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intentToInstrument = new Intent(getActivity(), InstrumentDetailsActivity.class);
                startActivity(intentToInstrument);

            }
        });
    }

    /*
    * RecyclerView的一系列操作。分类栏
    * */
    private void initRecyclerView() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleViewInstrumentInstrument.setLayoutManager(linearLayoutManager);
        //设置适配器
        InstrumentRecyclerAdapter RecyclerAdapter = new InstrumentRecyclerAdapter(getContext(),
                instrumentClassify());
        recycleViewInstrumentInstrument.setAdapter(RecyclerAdapter);
    }

    @OnClick(R.id.imageview_instrument_show)
    public void onClick() {
        /*Intent intentToSearch = new Intent(getActivity(), OrderDetailsMainActivity.class);
        startActivity(intentToSearch);*/
    }

    public void getImageByUrl() {
        String url = baseUrl + "/regist/ss";
        OkHttpUtils
                .postString()
                .url(url)//
                .content(new Gson().toJson(new SlideView(UserId, "9527")))
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "轮播图图片获取失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("success", response);
                        try {
                            Gson gson = new Gson();
                            JSONObject jsonObject = new JSONObject(response);
                            ImageCircleView imageCircleView = gson.fromJson(response, ImageCircleView.class);
                            List<ImageViewInfo> imageViewInfos = new ArrayList<ImageViewInfo>();
                            imageViewInfos = imageCircleView.getTop();
                            if (imageViewInfos == null) {
                                System.out.println("轮播图为空");
                                return;
                            }

                            for (int i = 0; i < imageViewInfos.size(); i++) {
                                ImageCricleViewList_image.add(imageViewInfos.get(i).getTop_image());
                                ImageCricleViewList_name.add(imageViewInfos.get(i).getClass_name());
                            }
                            //使用异步加载模拟网络请求
                            MyAsyncTask myAsyncTask = new MyAsyncTask();
                            myAsyncTask.execute();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }

    /**
     * 将数据转换为AutoPlayInfo形式
     */
    private List<AutoPlayInfo> changeAutoPlayInfoList() {
        List<AutoPlayInfo> autoPlayInfoList = new ArrayList<AutoPlayInfo>();
        for (int i = 0; i < ImageCricleViewList_image.size(); i++) {
            AutoPlayInfo autoPlayInfo = new AutoPlayInfo();
            autoPlayInfo.setImageUrl(ImageCricleViewList_image.get(i));
            autoPlayInfo.setAdLinks("");//无数据时不跳转
            autoPlayInfo.setTitle(ImageCricleViewList_name.get(i));
            autoPlayInfoList.add(autoPlayInfo);
        }
        return autoPlayInfoList;
    }

    @Override
    public void onResume() {
        //没有数据时不执行startPlaying,避免执行几次导致轮播混乱
        if (mAutoPlayInfoList != null && !mAutoPlayInfoList.isEmpty()) {
            autoPlayViewpager.startPlaying();
        }
        super.onResume();
    }

//    @Override
//    public void onPause() {
//        autoPlayViewpager.stopPlaying();
//        super.onPause();
//    }

    public void getIns() {
        String url = baseUrl + "www.baidu.com";
        String code = "2017";
        int maxtime = 638;
        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new Ins(code, maxtime)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("Error", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("success", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("top");
                            JSONObject top1 = array.getJSONObject(0);
                            String instrument_main_image = top1.getString("instrument_main_image");
                            //.........
                            JSONArray array1 = jsonObject.getJSONArray("list");
                            JSONObject list1 = array1.getJSONObject(0);
                            String instrument_image = list1.getString("instrument_image");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            //模拟网络请求获取数据
            try {
                Thread.sleep(2000);//模拟休眠2秒
                mAutoPlayInfoList = changeAutoPlayInfoList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //数据加载后更新UI
            autoPlayViewpager.initialize(mAutoPlayInfoList).build();
            autoPlayViewpager.setOnPageItemClickListener(onPageItemClickListener);
            autoPlayViewpager.startPlaying();
        }
    }

    private class Ins {
        private String code;
        private int maxtime;

        public Ins(String code, int maxtime) {
            this.code = code;
            this.maxtime = maxtime;
        }

        @Override
        public String toString() {
            return "Ins{" +
                    "username='" + code + '\'' +
                    ", password='" + maxtime + '\'' +
                    '}';
        }
    }

}
