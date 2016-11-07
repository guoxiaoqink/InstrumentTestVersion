package com.example.tu4.utils;

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
public class ApplicationStaticConstants {

    public final static int STUDENT_NUMBER = 10;
    public final static int MAX_STUDENT_NUMBER = 8;
    public final static int MAX_STUDENT_NUMBER_BACK = 7;
    public final static int LISTVIEW_COUNT = 7;
    public final static int LISTVIEW_COUNT_THREE = 3;
    public final static int ZERO = 0;
    public final static int ONE = 1;
    public final static int TWO = 2;
    public final static int THREE = 3;
    //url
    public final static String CONSTANTS_URL = "http://128.199.137.227:8080";
    public final static String LOGIN_URL = CONSTANTS_URL + "/login/api_login";
    public final static String REGISTER_URL = CONSTANTS_URL + "/regist/getdata";
    public final static String CALENDAR_URL = CONSTANTS_URL + "/music-stju-test/api_calendar";
    public final static String INS_DETAILS_URL = CONSTANTS_URL + "/music/api_insdetail";
    public final static String STUDENT_FEED_BACK_URL = CONSTANTS_URL + "/music-stju-test/api_feedback";
    public final static String SUBJECT_DETAIL_URL = CONSTANTS_URL + "/music-stju-test/api_classdetail";
    public final static String COMP_SUGGESTION_URL = CONSTANTS_URL + "/music-stju-test/api_complaint";
    public final static String HELP_CENTER_URL = CONSTANTS_URL + "/music-stju-test/api_helpcenter";
    public final static String ORDER_PAYMENT_URL = CONSTANTS_URL + "/music-stju-test/api_payorder";
    public final static String IMAGE_BY_URL = CONSTANTS_URL + "/regist/ss";
    public final static String SUBJECT_FRAGMENT_URL = CONSTANTS_URL + "/regist/sc";
    public final static String BOOKING_ORDER_URL = CONSTANTS_URL + "/myapi/ReservationOrder/api_showReservationOrder";
    public final static String EDITDRESS_URL = CONSTANTS_URL + "/myapi/receiverAddress/api_edictAddress";
    //趣拍
    public static final String APP_KEY = "20cffd9799a88e4";
    public static final String APP_SECRET = "14e418ad663f445e886f614ae0ea2f83";
    public static final String domain = "http://instrument.s.qupai.me";//当前TEST应用的域名。该地址每个应用都不同
    public static int JUMP_MAINACTIVITY = 1;
    public static int chooseRecycleView = 0;
    public static String account = "";
    public static String headPortrait = "";
    //用户登录成功的返回值
    public static String UserName = "";   //用户名
    public static int UserId = -1;        //用户id
    public static String LoginResult = "";//返回成功是否
    public static String Introduction = "";//自我介绍
    public static String Location = "";//地址
    public static String Other = "";//其他
    public static String UserTel = "";//电话号码
    /**
     * 默认时长
     */
    public static  float DEFAULT_DURATION_LIMIT = 8;
    /**
     * 默认最小时长
     */
    public static  float DEFAULT_MIN_DURATION_LIMIT = 2;
    /**
     * 默认码率
     */
    public static  int DEFAULT_BITRATE =2000 * 1000;
    /**
     * 默认Video保存路径，请开发者自行指定
     */
    public static  String VIDEOPATH = FileUtils.newOutgoingFilePath();
    /**
     * 默认缩略图保存路径，请开发者自行指定
     */
    public static  String THUMBPATH =  VIDEOPATH + ".jpg";
    /**
     * 水印本地路径，文件必须为rgba格式的PNG图片
     */
    public static  String WATER_MARK_PATH ="assets://Qupai/watermark/qupai-logo.png";
    public static String tags = "tags";
    public static String description = "description";
    public static int shareType = 0; //是否公开 0公开分享 1私有(default) 公开类视频不需要AccessToken授权
    public static String accessToken;//accessToken 通过调用授权接口得到
    //    public static String space = UUID.randomUUID().toString().replace("-",""); //存储目录 建议使用uid cid之类的信息,不要写死
    public static String space = null; //存储目录 建议使用uid cid之类的信息,不要写死
    //开发者自定义RequestCode,避免重复
    public static int RECORDE_SHOW = 10001;

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

    public final static List<String> getCustomServeName() {
        List<String> datalist = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            datalist.add("客服" + i + 1);
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
