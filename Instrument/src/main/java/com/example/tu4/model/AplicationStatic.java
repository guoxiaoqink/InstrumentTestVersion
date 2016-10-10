package com.example.tu4.model;

import android.util.JsonReader;

import com.example.tu4.R;
import com.example.tu4.bean.InstrumentDetails;
import com.example.tu4.view.ImageCyclePlayView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adelais on 2016/9/26.
 */
/*
* 这个类里边放的都是 static的数据  在整个app中使用
* */
public class AplicationStatic {
    public final static int STUDENT_NUMBER = 10;
    public final static int MAX_STUDENT_NUMBER = 8;
    public final static int MAX_STUDENT_NUMBER_BACK = 7;
    public final static int LISTVIEW_COUNT = 7;
    public final static int LISTVIEW_COUNT_THREE = 3;
    public final static int ZERO = 0;
    public final static int ONE = 1;
    public final static int TWO = 2;
    public final static int THREE = 3;
    public static int chooseRecycleView = 0;

    public static List<InstrumentDetails> getInstrumentDetailfromJson() {
        List<InstrumentDetails> clist = null;
        if (clist == null) {
            try {
                clist = new Gson().fromJson(String.valueOf(new JsonReader(
                                new FileReader("simulationJsonData/InstrumentDetails.json"))),
                        new TypeToken<List<InstrumentDetails>>() {
                        }.getType());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return clist;
    }

    /*
    * 乐器详细信息list
    * */
    public final static List<InstrumentDetails> datalistInstrumentDetail() {
        ArrayList<InstrumentDetails> dataList = new ArrayList<InstrumentDetails>();
        for (int a = 0; a < 10; a++) {
            InstrumentDetails commod = new InstrumentDetails(R.mipmap.instrument_instrument,
                    "钢琴好便宜", "￥ 15.00", "30.00");
            dataList.add(commod);
        }
        return dataList;
    }

    public final static List<String> instrumentClassify() {
        List<String> datalist = new ArrayList<String>();
        datalist.add("全部");
        for (int i = 0; i < 10; i++) {
            datalist.add("分类" + i);
        }
        return datalist;
    }

    /*
    * 轮播图的初始化资源
    * */
    public final static List<ImageCyclePlayView.ImageInfo> cycleImageData() {
        List<ImageCyclePlayView.ImageInfo> list = new ArrayList<ImageCyclePlayView.ImageInfo>();
        //res图片资源
        list.add(new ImageCyclePlayView.ImageInfo(R.mipmap.happyboy, "111111111111", ""));
        list.add(new ImageCyclePlayView.ImageInfo(R.mipmap.happyboy2, "222222222222222", ""));
        list.add(new ImageCyclePlayView.ImageInfo(R.mipmap.happyboy, "3333333333333", ""));
        list.add(new ImageCyclePlayView.ImageInfo(R.mipmap.happyboy2, "111111111111", ""));
        return list;
    }

    /*
    *
    * */
    public final static List<HashMap<String, Object>> instrumentImg() {
        ArrayList<HashMap<String, Object>> datalist = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < LISTVIEW_COUNT; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.mipmap.ic_launcher);
            datalist.add(map);
        }
        return datalist;
    }

}
