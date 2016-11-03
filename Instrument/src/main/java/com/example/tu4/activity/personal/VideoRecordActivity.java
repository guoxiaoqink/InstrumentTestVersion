package com.example.tu4.activity.personal;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
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
import com.example.tu4.R;
import com.example.tu4.utils.Contant;
import com.example.tu4.utils.RecordResult;
import com.example.tu4.utils.RequestCode;

import java.io.File;
import java.util.UUID;

import butterknife.ButterKnife;

import static com.lling.photopicker.Application.getContext;

public class VideoRecordActivity extends AppCompatActivity {

    private static final String TAG = "Upload";
    /**
     * 在Demo中录制完成后调用了清除草稿的功能，需要存文件的请开发者在删除之前执行move操作。
     */
    String videoFile;
    String[] thum;
    private int mVideoBitrate = Contant.DEFAULT_BITRATE;
    private String waterMarkPath = Contant.WATER_MARK_PATH;
    private ProgressBar progresstest = null;
    private Button btn_open_video = null;
    private String videoUrl = null;
    private String imageUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        ButterKnife.bind(this);

        recording();
    }

    private void recording() {
        QupaiService qupaiService = QupaiManager.getQupaiService(getContext());
        if (qupaiService == null) {
            Toast.makeText(VideoRecordActivity.this, "插件没有初始化，无法获取 QupaiService",
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
        qupaiService.showRecordPage(VideoRecordActivity.this, RequestCode.RECORDE_SHOW,
                false);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            RecordResult result = new RecordResult(data);
            //得到视频地址，和缩略图地址的数组，返回十张缩略图
            videoFile = result.getPath();
            thum = result.getThumbnail();
            result.getDuration();

            Log.w("视频路径","视频路径:" + videoFile + "图片路径:" + thum[0]);

            startUpload();//可以在这里调用上传的方法

        } else {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(VideoRecordActivity.this, "RESULT_CANCELED", Toast.LENGTH_LONG).show();
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

                videoUrl = Contant.domain + "/v/" + responseMessage + ".mp4" + "?token=" +
                        Contant.accessToken;
                imageUrl = Contant.domain + "/v/" + responseMessage + ".jpg" + "?token=" +
                        Contant.accessToken;

//                videoUrl = ContantTest.domain1 + "/v/" + responseMessage + ".mp4" + "?token=" +
//                        ContantTest.accessToken;
//                imageUrl = ContantTest.domain1 + "/v/" + responseMessage + ".jpg" + "?token=" +
//                        ContantTest.accessToken;


                Log.w("网络地址—视频",videoUrl);
                Log.w("网络地址—图片",imageUrl);

                Log.i("TAG", "data:onUploadComplte" + "uuid:" + uuid + Contant.domain + "/v/" +
                        responseMessage + ".jpg" + "?token=" + Contant.accessToken);
                Log.i("TAG", "data:onUploadComplte" + "uuid:" + uuid + Contant.domain + "/v/" +
                        responseMessage + ".mp4" + "?token=" + Contant.accessToken);
            }
        });
        String uuid = UUID.randomUUID().toString();
        Log.e("QupaiAuth", "accessToken" + Contant.accessToken + "space" + Contant.space);
        startUpload(createUploadTask(this, uuid, new File(videoFile), new File(thum[0]),
                Contant.accessToken, Contant.space, Contant.shareType, Contant.tags, Contant
                        .description));
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
