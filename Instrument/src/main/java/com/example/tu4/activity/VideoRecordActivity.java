package com.example.tu4.activity;

import android.content.pm.ActivityInfo;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoRecordActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    @BindView(R.id.img_video_recorde_return)
    ImageView imgVideoRecordeReturn;
    private ImageButton videoButton;
    private TextView videoTime;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private MediaRecorder mediarecorder;
    private boolean isRecorder = false;
    //    private MHandler handler;
    private final static int THREAD_DELAYED = 100;
    private final static int UPDATE_TIME_RECORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();


    }

    private void init() {
        videoButton = (ImageButton) findViewById(R.id.imgbt_video_button);
        videoButton.setOnClickListener(new TestVideoListener());
        surfaceView = (SurfaceView) this.findViewById(R.id.sv_video_record);
        SurfaceHolder holder = surfaceView.getHolder();// 取得holder
        holder.addCallback(this); // holder加入回调接口
        // setType必须设置，要不出错.
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @OnClick(R.id.img_video_recorde_return)
    public void onClick() {
//        Intent intent = new Intent(VideoRecordActivity.this, MyWorksActivity.class);
//        startActivity(intent);
        this.finish();
    }

    class TestVideoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (isRecorder == false) {
                StarRecorder();
//                new UpdateThread().run();
                isRecorder = true;
            } else {
                StopRecorder();
                isRecorder = false;
            }

        }
    }

    void StarRecorder() {

        mediarecorder = new MediaRecorder();// 创建mediarecorder对象
        // 设置录制视频源为Camera(相机)
        mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //mediarecorder.setOrientationHint(90);//视频旋转90度
        // 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4
        mediarecorder
                .setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 设置录制的视频编码h263 h264
        mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        // 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
        mediarecorder.setVideoSize(176, 144);
        // 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错
        mediarecorder.setVideoFrameRate(20);
        mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());
        // 设置视频文件输出的路径
        mediarecorder.setOutputFile("/sdcard/love.3gp");
        try {
            // 准备录制
            mediarecorder.prepare();
            // 开始录制
            mediarecorder.start();
        } catch (IllegalStateException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void StopRecorder() {
        if (mediarecorder != null) {
            // 停止录制
            mediarecorder.stop();
            // 释放资源
            mediarecorder.release();
            mediarecorder = null;
        }

    }

    /**
     * 动态显示录制时间
     *
     * @param holder
     */
//    private class MHandler extends Handler {
//
//        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
//
//        @Override
//        public void handleMessage(Message msg) {
//            try {
//                switch (msg.what) {
//
//                    case UPDATE_TIME_RECORD:
//                        long currentTimeMillis = System.currentTimeMillis();
//                        String time = format.format(currentTimeMillis - 0);
//                        videoTime.setText(time);
//                        break;
//
////                    case UPDATE_TIME_PLAY:
////                        UpdateTimeing playThread = new UpdateTimeing();
////                        playThread.start();
////                        break;
////
////                    case UPDATE_PB_PLAY:
////                        int position = mMediaPlayer.getCurrentPosition();
////                        record_playPB.setProgress(position);
////                        record_total.setText(format.format(mMediaPlayer.getDuration()));
////                        record_current.setText(format.format(position) + "/");
////                        break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private class UpdateThread extends Thread {
//
//        @Override
//        public void run() {
//            while (isRecorder == true) {
//                try {
//                    new Thread().sleep(THREAD_DELAYED);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                handler.sendEmptyMessage(UPDATE_TIME_RECORD);
//            }
//        }
//    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder = holder;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        surfaceHolder = holder;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceView = null;
        surfaceHolder = null;
        mediarecorder = null;

    }
}
