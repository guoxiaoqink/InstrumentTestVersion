package com.example.tu4.activity.personal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.duanqu.qupai.bean.QupaiUploadTask;
import com.duanqu.qupai.engine.session.MovieExportOptions;
import com.duanqu.qupai.engine.session.ProjectOptions;
import com.duanqu.qupai.engine.session.ThumbnailExportOptions;
import com.duanqu.qupai.engine.session.UISettings;
import com.duanqu.qupai.engine.session.VideoSessionCreateInfo;
import com.duanqu.qupai.sdk.android.QupaiManager;
import com.duanqu.qupai.sdk.android.QupaiService;
import com.duanqu.qupai.upload.QupaiUploadListener;
import com.duanqu.qupai.upload.UploadService;
import com.duanqu.qupai.utils.Contant;
import com.example.tu4.R;
import com.example.tu4.adapter.MyWorksGridviewAdapter;
import com.example.tu4.utils.Auth;
import com.example.tu4.utils.RecordResult;
import com.example.tu4.view.TitleView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tu4.utils.ApplicationStaticConstants.APP_KEY;
import static com.example.tu4.utils.ApplicationStaticConstants.APP_SECRET;
import static com.example.tu4.utils.ApplicationStaticConstants.DEFAULT_BITRATE;
import static com.example.tu4.utils.ApplicationStaticConstants.JUMP_MAINACTIVITY;
import static com.example.tu4.utils.ApplicationStaticConstants.RECORDE_SHOW;
import static com.example.tu4.utils.ApplicationStaticConstants.WATER_MARK_PATH;
import static com.example.tu4.utils.ApplicationStaticConstants.accessToken;
import static com.example.tu4.utils.ApplicationStaticConstants.description;
import static com.example.tu4.utils.ApplicationStaticConstants.domain;
import static com.example.tu4.utils.ApplicationStaticConstants.shareType;
import static com.example.tu4.utils.ApplicationStaticConstants.space;
import static com.example.tu4.utils.ApplicationStaticConstants.tags;
/**
 * Created by hs on
 * Descripyion: 我的作品界面
 * Version：1
 * Modify Person：gxq
 */
public class MyWorksActivity extends AppCompatActivity {

    private static final String TAG = "Upload";
    //    @BindView(R.id.img_my_works_return)
//    ImageView imgMyWorksReturn;
//    @BindView(R.id.img_my_works_delete)
//    ImageView imgMyWorksDelete;
    @BindView(R.id.gv_my_works)
    GridView gvMyWorks;
    ApplicationInfo ai;
    String uid;
    /**
     * 在Demo中录制完成后调用了清除草稿的功能，需要存文件的请开发者在删除之前执行move操作。
     */
    String videoFile;
    String[] thum;
    @BindView(R.id.my_work_title)
    TitleView myWorkTitle;
    //    private ArrayList<Map<String, String>> listData;
    private ArrayList<Map<String, String>> listData;
    //    private Map<String,String> mapData;
    private Map<String, String> mapData;
    private ArrayList<Integer> myWorksPisture;
    private String[] myWorksTime, myWorksDate;
    private int mVideoBitrate = DEFAULT_BITRATE;
    private String waterMarkPath = WATER_MARK_PATH;
    private ProgressBar progresstest = null;
    private Button btn_open_video = null;
    private String videoUrl = null;
    private String imageUrl = null;
    private ArrayList<String> deleteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_my_works);
        ButterKnife.bind(this);
        initDate();
        try {
            PackageManager pm = getPackageManager();
            ai = pm.getApplicationInfo("com.example.tu4", 0);
            uid = String.valueOf(ai.uid);
            space = uid;
           Log.w("应用的UID", "!!!!!!!!!!!!!!!" +space);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Auth.getInstance().initAuth(this, APP_KEY,
                APP_SECRET,space);

        MyWorksGridviewAdapter workAdapter = new MyWorksGridviewAdapter(this, listData);
        gvMyWorks.setAdapter(workAdapter);

//        imgMyWorksDelete.setOnClickListener(this);
//        imgMyWorksReturn.setOnClickListener(this);

        gvMyWorks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    recording(view);
                } else {
                    Intent intent = new Intent(MyWorksActivity.this, VideoPlayActivity.class);
                    startActivity(intent);
                }
            }
        });
        myWorkTitle.getImgLeft().setVisibility(View.VISIBLE);
        Resources res = getResources();
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        Drawable ic_delete = res.getDrawable(R.mipmap.my_works_delete);
        myWorkTitle.setImgLeft(ic_return);
        myWorkTitle.setImgRight2(ic_delete);
        myWorkTitle.getImgLeft().setVisibility(View.VISIBLE);
        myWorkTitle.getImgRight2().setVisibility(View.VISIBLE);
        myWorkTitle.setTitleText("我的作品");
        myWorkTitle.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWorksActivity.this.finish();
                JUMP_MAINACTIVITY = 2;
            }
        });
        myWorkTitle.setImgRight2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWorksActivity.this, MyWorksDeleteActivity.class);
                startActivity(intent);
            }
        });

    }



    /**
     * 获取数据
     */
    void initDate() {
//        listData = new ArrayList<>();
//
//        String url = "";
//        MyWorksInfoPost data = new MyWorksInfoPost("1","student",0);
//        List<MyWorksInfoPost> datas = new ArrayList<>();
//        datas.add(data);
//        OkHttpUtils
//                .postString()
//                .url(url)
//                .content(new Gson().toJson(new MyWorksPost("2005",datas)))
//                .build()
//                .execute(new GenericsCallback<MyWorks>(new JsonGenericsSerializator()) {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e("onError",e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onResponse(MyWorks response, int id) {
//                        List<MyWorksInfo> myWorksInfos = response.getList();
//                        for (int i = 0;i<myWorksInfos.size();i++){
//                            mapData = new HashMap<String, String>();
//                            mapData.put("imgUrl",myWorksInfos.get(i).getWork_icon());
//                            mapData.put("workUrl",myWorksInfos.get(i).getWork_url());
//                            mapData.put("workPostTime",myWorksInfos.get(i).getWork_post_time());
//                            mapData.put("workContentTime",myWorksInfos.get(i)
// .getWork_content_time());
//                            listData.add(mapData);
//                        }
//
//                    }
//
//                });


        listData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mapData = new HashMap<>();
            mapData.put("img", R.mipmap.a + "");
            mapData.put("time", i + "0s");
            mapData.put("date", "2016-05-06 " + "1" + i + ":00");
            listData.add(mapData);
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.img_my_works_delete:
//                Intent intent = new Intent(MyWorksActivity.this, MyWorksDeleteActivity.class);
//                intent.putExtra("listData", listData);
//                startActivity(intent);
//                break;
//            case R.id.img_my_works_return:
//                this.finish();
//                JUMP_MAINACTIVITY = 2;
//                break;
//        }
//
//    }

    /**
     * 拍摄视频
     *
     * @param view
     */
    private void recording(View view) {
        QupaiService qupaiService = QupaiManager.getQupaiService(view.getContext());
        if (qupaiService == null) {
            Toast.makeText(MyWorksActivity.this, "插件没有初始化，无法获取 QupaiService",
                    Toast.LENGTH_LONG).show();
            return;
        }

        //UI设置参数
        UISettings _UISettings = new UISettings() {

            @Override
            public boolean hasEditor() {
                return false;//是否需要编辑功能
            }

            @Override
            public boolean hasImporter() {
                return false;//是否需要导入功能
            }

            @Override
            public boolean hasGuide() {
                return false;//是否启动引导功能，建议用户第一次使用时设置为true
            }

            @Override
            public boolean hasSkinBeautifer() {
                return false;//是否显示美颜图标
            }
        };

        //压缩参数
        MovieExportOptions movie_options = new MovieExportOptions.Builder()
                .setVideoBitrate(mVideoBitrate)
                .configureMuxer("movflags", "+faststart")
                .build();

        //输出视频的参数
        ProjectOptions projectOptions = new ProjectOptions.Builder()
                //输出视频宽高目前只能设置1：1的宽高，建议设置480*480.
                .setVideoSize(480, 480)
                //帧率
                .setVideoFrameRate(30)
                //时长区间
                .setDurationRange(2, 8)
                .get();

        //缩略图参数,可设置取得缩略图的数量，默认10张
        ThumbnailExportOptions thumbnailExportOptions = new ThumbnailExportOptions.Builder()
                .setCount(1).get();

        VideoSessionCreateInfo info = new VideoSessionCreateInfo.Builder()
                //水印地址，如"assets://Qupai/watermark/qupai-logo.png"
                .setWaterMarkPath(waterMarkPath)
                //水印的位置
                .setWaterMarkPosition(1)
                //摄像头方向,可配置前置或后置摄像头
                .setCameraFacing(Camera.CameraInfo.CAMERA_FACING_BACK)
                //美颜百分比,设置之后内部会记住，多次设置无效
                .setBeautyProgress(80)
                //默认是否开启
                .setBeautySkinOn(false)
                .setMovieExportOptions(movie_options)
                .setThumbnailExportOptions(thumbnailExportOptions)
                .build();

        //初始化，建议在application里面做初始化，这里做是为了方便开发者认识参数的意义
        qupaiService.initRecord(info, projectOptions, _UISettings);

        /**
         * 建议上面的initRecord只在application里面调用一次。这里为了能够开发者直观看到改变所以可以调用多次
         */
        qupaiService.showRecordPage(MyWorksActivity.this, RECORDE_SHOW,
                false);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            RecordResult result = new RecordResult(data);
            //得到视频地址，和缩略图地址的数组，返回十张缩略图
            videoFile = result.getPath();
            thum = result.getThumbnail();
            result.getDuration();

            Log.w("视频路径", "视频路径:" + videoFile + "图片路径:" + thum[0]);

            startUpload();//可以在这里调用上传的方法

        } else {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MyWorksActivity.this, "RESULT_CANCELED", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 创建一个上传任务
     *
     * @param context
     * @param uuid        随机生成的UUID
     * @param _VideoFile  完整视频文件
     * @param _Thumbnail  缩略图
     * @param accessToken 通过调用鉴权得到token
     * @param space       开发者生成的Quid，必须要和token保持一致
     * @param share       是否公开 0公开分享 1私有(default) 公开类视频不需要AccessToken授权
     * @param tags        标签 多个标签用 "," 分隔符
     * @param description 视频描述
     * @return
     */
    private QupaiUploadTask createUploadTask(Context context, String uuid, File _VideoFile, File
            _Thumbnail, String accessToken,
                                             String space, int share, String tags, String
                                                     description) {
        UploadService uploadService = UploadService.getInstance();
        return uploadService.createTask(context, uuid, _VideoFile, _Thumbnail,
                accessToken, space, share, tags, description);
    }

    /**
     * 开始上传
     */
    private void startUpload() {
        // progresstest.setVisibility(View.VISIBLE);
        UploadService uploadService = UploadService.getInstance();
        uploadService.setQupaiUploadListener(new QupaiUploadListener() {
            @Override
            public void onUploadProgress(String uuid, long uploadedBytes, long totalBytes) {
                //int percentsProgress = (int) (uploadedBytes * 100 / totalBytes);
                Log.e(TAG, "uuid:" + uuid + "data:onUploadProgress");
                // progresstest.setProgress(percentsProgress);
            }

            @Override
            public void onUploadError(String uuid, int errorCode, String message) {
                Log.e(TAG, "uuid:" + uuid + "onUploadError" + errorCode + message);
            }

            @Override
            public void onUploadComplte(String uuid, int responseCode, String responseMessage) {
                //http://{DOMAIN}/v/{UUID}.mp4?token={ACCESS-TOKEN}
                // progresstest.setVisibility(View.GONE);
                // btn_open_video.setVisibility(View.VISIBLE);

                //这里返回的uuid是你创建上传任务时生成的uuid.开发者可以使用其他作为标识
                //videoUrl返回的是上传成功的视频地址,imageUrl是上传成功的图片地址

                videoUrl = domain + "/v/" + responseMessage + ".mp4" + "?token=" +
                        accessToken;
                imageUrl = domain + "/v/" + responseMessage + ".jpg" + "?token=" +
                        accessToken;

//                videoUrl = ContantTest.domain1 + "/v/" + responseMessage + ".mp4" + "?token=" +
//                        ContantTest.accessToken;
//                imageUrl = ContantTest.domain1 + "/v/" + responseMessage + ".jpg" + "?token=" +
//                        ContantTest.accessToken;


                Log.w("网络地址—视频", videoUrl);
                Log.w("网络地址—图片", imageUrl);

                Log.i("TAG", "data:onUploadComplte" + "uuid:" + uuid + domain + "/v/" +
                        responseMessage + ".jpg" + "?token=" + accessToken);
                Log.i("TAG", "data:onUploadComplte" + "uuid:" + uuid + domain + "/v/" +
                        responseMessage + ".mp4" + "?token=" + accessToken);
            }
        });
        String uuid = UUID.randomUUID().toString();
        Log.e("QupaiAuth", "accessToken" + accessToken + "space" + uid);
        startUpload(createUploadTask(this, uuid, new File(videoFile), new File(thum[0]),
                accessToken, Contant.space, shareType, tags, description));
    }

    /**
     * 开始上传
     *
     * @param data 上传任务的task
     */
    private void startUpload(QupaiUploadTask data) {
        try {
            UploadService uploadService = UploadService.getInstance();
            uploadService.startUpload(data);
        } catch (IllegalArgumentException exc) {
            Log.d("upload", "Missing some arguments. " + exc.getMessage());
        }
    }

}
