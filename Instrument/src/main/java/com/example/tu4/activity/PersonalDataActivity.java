package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDataActivity extends AppCompatActivity {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.civ_personPortrail)
    CircleImageView civPersonPortrail;
    @BindView(R.id.rela_headpro)
    RelativeLayout relaHeadpro;
    @BindView(R.id.tv_telnumber)
    TextView tvTelnumber;
    @BindView(R.id.tv_personlocate_locate)
    TextView tvPersonlocateLocate;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.tv_selfcriticism)
    TextView tvSelfcriticism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_return, R.id.rela_headpro})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                break;
            case R.id.rela_headpro:
                Intent intentToHeadProtrait = new Intent(PersonalDataActivity.this, HeadPortraitActivity.class);
                startActivity(intentToHeadProtrait);
                break;
        }
    }

    public void changeHeadPortrait() {

    }
}
