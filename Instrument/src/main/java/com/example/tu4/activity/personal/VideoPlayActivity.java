package com.example.tu4.activity.personal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by hs on
 * Descripyion: 播放视频界面
 * Version：1
 * Modify Person：gxq
 */
public class VideoPlayActivity extends AppCompatActivity {

    @BindView(R.id.vv_video_play)
    VideoView vvVideoPlay;
    private MediaController mMediaController;
    private Uri uri;
    String path2 = "http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai" +
            ".com/D046015255134077DDB3ACA0D7E68D45.flv";
    String path1 = Environment.getExternalStorageDirectory() + "/ok.mp4";


    @BindView(R.id.img_video_play_return)
    ImageView imgVideoPlayReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);

        play();
    }

    private void play() {

        uri = Uri.parse(path2);
        vvVideoPlay.setVideoURI(uri);
        // mVideoView.setVideoPath(path1);//设置播放地址
        mMediaController = new MediaController(this);//实例化控制器
        mMediaController.show(10000);//控制器显示5s后自动隐藏
        vvVideoPlay.setMediaController(mMediaController);//绑定控制器
        vvVideoPlay.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//设置播放画质 高画质
        vvVideoPlay.requestFocus();//取得焦点

    }

    @OnClick(R.id.img_video_play_return)
    public void onClick() {
        this.finish();
    }
}
