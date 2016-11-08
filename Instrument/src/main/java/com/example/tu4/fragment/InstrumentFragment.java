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
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.activity.instrument.InstrumentDetailsActivity;
import com.example.tu4.adapter.InstrumentGridviewAdapter;
import com.example.tu4.adapter.InstrumentListViewAlbumAdapter;
import com.example.tu4.adapter.InstrumentListViewInstruDetialAdapter;
import com.example.tu4.adapter.InstrumentRecyclerAdapter;
import com.example.tu4.bean.AlbumGet;
import com.example.tu4.bean.AllInstrumentGet;
import com.example.tu4.bean.AllInstrumentPost;
import com.example.tu4.bean.AutoPlayInfo;
import com.example.tu4.bean.ImageCircleView;
import com.example.tu4.bean.ImageViewInfo;
import com.example.tu4.bean.SlideView;
import com.example.tu4.okhttp.JsonGenericsSerializator;
import com.example.tu4.view.AutoPlayingViewPager;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;
import com.example.tu4.view.ResolveConflictsScoolviewListview;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.IMAGE_BY_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.INSTRUMENT_ALBUM;
import static com.example.tu4.utils.ApplicationStaticConstants.INSTRUMENT_ALL_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;
import static com.example.tu4.utils.ApplicationStaticConstants.listDataIns;
import static com.example.tu4.utils.ApplicationStaticConstants.listDataclasscify;

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
    private ArrayList<Map<String,String>> listData;
    private Map<String,String> mapData;

    private List<AutoPlayInfo> mAutoPlayInfoList;
    private AutoPlayingViewPager.OnPageItemClickListener onPageItemClickListener = new
            AutoPlayingViewPager.OnPageItemClickListener() {

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
        getImageByUrl();
        getAlbumByUrl();
        getAllInstrumentByUrl();
//        initRecyclerView();
//        initGridview();
        initlistviewInstumentMoney();
        initlistviewInstumentAlbum();
        return view;
    }

    /**
     * 获取所有乐器的信息
     */
    private void getAllInstrumentByUrl() {
        OkHttpUtils
                .postString()
                .url(INSTRUMENT_ALL_URL)
                .content(new Gson().toJson(new AllInstrumentPost("2005", 1, "student")))
                .build()
                .execute(new GenericsCallback<AllInstrumentGet>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("失败", "获取失败");
                        Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(AllInstrumentGet response, int id) {
                        listData = new ArrayList<Map<String, String>>();
                        ArrayList<String> typeList = response.getTypeList();
                        listDataclasscify = typeList;
                        Log.w("这里是静态的type",listDataclasscify.toString());
                        initRecyclerView(typeList);//填充分类栏
                        ArrayList<AllInstrumentGet.InsArr> insArr = response.getInsArr();
                        for (int i = 0;i<insArr.size();i++){
                            mapData = new HashMap<String, String>();
                            mapData.put("name",insArr.get(i).getName());
                            mapData.put("pre_price",insArr.get(i).getPre_price()+"");
                            mapData.put("now_price",insArr.get(i).getNow_price()+"");
                            mapData.put("pic_url",insArr.get(i).getPic_url());
                            listData.add(mapData);
                        }
                        listDataIns = listData;
                        Log.w("这里是静态的list",listDataIns.toString());
                        initGridview(listData);//填充乐器信息
                        Log.w("成功", typeList.toString());
                    }

                });

    }

    /**
     * 获得专辑图片
     */
    private void getAlbumByUrl() {
        Log.w("getAlbumByUrl", "看这里看这里看这里看这里看这里看这里");
        OkHttpUtils
                .postString()
                .url(INSTRUMENT_ALBUM)
                .content(new Gson().toJson(new albumPost("2000")))
                .build()
                .execute(new GenericsCallback<AlbumGet>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.w("onError", "数据请求失败");
                        Toast.makeText(getContext(), "数据请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(AlbumGet respone, int id) {
                        ArrayList<String> albumImg = new ArrayList<String>();
                        ArrayList<AlbumGet.AlbumPicEntity> albums = respone.getAlbums();
                        for (int i = 0; i < albums.size(); i++) {
                            String imgurl = albums.get(i).getAlbum_url();
                            albumImg.add(imgurl);
                        }
                        Log.w("啊啊啊啊啊啊啊啊啊啊啊啊", albumImg.toString());
                    }

                });


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
    private void initGridview(ArrayList<Map<String,String>> listData) {
        InstrumentGridviewAdapter adapter = new InstrumentGridviewAdapter(listData,getContext());
        gridviewInstrumentInstrument.setAdapter(adapter);
        gridviewInstrumentInstrument.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intentToInstrument = new Intent(getActivity(), InstrumentDetailsActivity
                        .class);
                startActivity(intentToInstrument);

            }
        });
    }

    /*
    * RecyclerView的一系列操作。分类栏
    * */
    private void initRecyclerView(ArrayList<String> typeList) {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleViewInstrumentInstrument.setLayoutManager(linearLayoutManager);
        //设置适配器
        InstrumentRecyclerAdapter RecyclerAdapter = new InstrumentRecyclerAdapter(getContext(),
                typeList);
        recycleViewInstrumentInstrument.setAdapter(RecyclerAdapter);
    }

    @OnClick(R.id.imageview_instrument_show)
    public void onClick() {
        Intent intentToSearch = new Intent(getActivity(), SearchActivity.class);
        startActivity(intentToSearch);
    }

    public void getImageByUrl() {
        OkHttpUtils
                .postString()
                .url(IMAGE_BY_URL)//
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
//                        Log.d("success", response);
                        try {
                            Gson gson = new Gson();
                            JSONObject jsonObject = new JSONObject(response);
                            ImageCircleView imageCircleView = gson.fromJson(response,
                                    ImageCircleView.class);
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
        //没有数据时不执行startPlaying,避免执行几次导致轮播混乱1
        if (mAutoPlayInfoList != null && !mAutoPlayInfoList.isEmpty()) {
            autoPlayViewpager.startPlaying();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
//        autoPlayViewpager.stopPlaying();
        super.onPause();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            //模拟网络请求获取数据
            try {
                Thread.sleep(4000);//模拟休眠4秒
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

    private class albumPost implements Serializable {
        private String code;

        public albumPost(String code) {
            this.code = code;
        }
    }

}
