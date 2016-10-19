package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.model.AplicationStatic;
import com.lling.photopicker.PhotoPickerActivity;
import com.lling.photopicker.utils.ImageLoader;
import com.lling.photopicker.utils.OtherUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeadPortraitActivity extends AppCompatActivity {
    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.img_head_portrait)//头像位置
            ImageView imgHeadPortrait;
    @BindView(R.id.btn_change_head_protrait)
    Button btnChangeHeadProtrait;
    private List<String> mResults;
    private int mColumnWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_portrait);
        ButterKnife.bind(this);
        int screenWidth = OtherUtils.getWidthInPx(getApplicationContext());
        mColumnWidth = (screenWidth - OtherUtils.dip2px(getApplicationContext(), 4)) / 3;

    }

    @OnClick({R.id.img_return, R.id.tv_ok, R.id.btn_change_head_protrait})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                this.finish();
                break;
            case R.id.tv_ok:
                break;

            case R.id.btn_change_head_protrait://点击更改头像

                Intent intent = new Intent(HeadPortraitActivity.this, PhotoPickerActivity.class);
                intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, true);
                intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, PhotoPickerActivity.MODE_SINGLE);
                intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, PhotoPickerActivity.DEFAULT_NUM);
                startActivityForResult(intent, 1);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                showResult(result);
            }
        }
    }

    private void showResult(ArrayList<String> paths) {
        if (mResults == null) {
            mResults = new ArrayList<>();
        }
        mResults.clear();
        mResults.addAll(paths);
        AplicationStatic.headPortrait = mResults.get(0);
        ImageLoader.getInstance().display(paths.get(0), imgHeadPortrait, mColumnWidth, mColumnWidth);

    }

}