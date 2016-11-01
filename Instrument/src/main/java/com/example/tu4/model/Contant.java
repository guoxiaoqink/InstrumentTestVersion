package com.example.tu4.model;


public class Contant {

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

    public static final String APP_KEY = "20cffd9799a88e4";
    public static final String APP_SECRET = "14e418ad663f445e886f614ae0ea2f83";
    public static String tags = "tags";
    public static String description = "description";
    public static int shareType = 0; //是否公开 0公开分享 1私有(default) 公开类视频不需要AccessToken授权

    public static String accessToken;//accessToken 通过调用授权接口得到

//    public static String space = UUID.randomUUID().toString().replace("-",""); //存储目录 建议使用uid cid之类的信息,不要写死
    public static String space = null; //存储目录 建议使用uid cid之类的信息,不要写死
    public static final String domain="http://instrument.s.qupai.me";//当前TEST应用的域名。该地址每个应用都不同
}
