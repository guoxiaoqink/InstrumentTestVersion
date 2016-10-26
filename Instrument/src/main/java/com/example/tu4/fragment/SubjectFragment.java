package com.example.tu4.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.course.SubjectDetailActivity;
import com.example.tu4.adapter.SubjectListviewAdapter;
import com.example.tu4.autoplayingviewpager.AdvertisementActivity;
import com.example.tu4.autoplayingviewpager.AutoPlayInfo;
import com.example.tu4.autoplayingviewpager.AutoPlayingViewPager;
import com.example.tu4.bean.ImageCircleView;
import com.example.tu4.bean.ImageViewInfo;
import com.example.tu4.bean.SlideView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.example.tu4.model.AplicationStatic.UserId;
import static com.example.tu4.model.IUrl.baseUrl;

/**
 * Created by WQJ on 2016/10/24
 * Descripyion: 课程页面，完成轮播图
 * Version: 3
 * Modify Person :wqj
 */
public class SubjectFragment extends Fragment {

    //    @BindView(R.id.icpv_subject)
//    ImageCyclePlayView icpvSubject;
    @BindView(R.id.listview_subject)
    ListView listviewSubject;
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
            }
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        ButterKnife.bind(this, view);
        initListviewSubjectDetail();
        getImageByUrl();
        return view;
    }

    public void initListviewSubjectDetail() {
        SubjectListviewAdapter adapter = new SubjectListviewAdapter(getContext());
        listviewSubject.setAdapter(adapter);
        listviewSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), SubjectDetailActivity.class);
                startActivity(intent);
            }
        });
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

    @Override
    public void onPause() {
        autoPlayViewpager.stopPlaying();
        super.onPause();
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

}

