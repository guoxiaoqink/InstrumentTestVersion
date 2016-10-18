package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoPlayActivity extends AppCompatActivity {

    @BindView(R.id.img_video_play_return)
    ImageView imgVideoPlayReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_video_play_return)
    public void onClick() {
        Intent intent = new Intent(VideoPlayActivity.this, MyWorksActivity.class);
        startActivity(intent);
    }
}
