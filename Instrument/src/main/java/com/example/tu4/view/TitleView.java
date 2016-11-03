package com.example.tu4.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;

/**
 * Created by MR.WEN on 2016/11/2.
 */

public class TitleView extends FrameLayout {
    //    @BindView(R.id.img_left)
//    ImageView imgLeft;
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.img_right_1)
//    ImageView imgRight1;
//    @BindView(R.id.img_right_2)
//    ImageView imgRight2;
    private ImageView imgLeft;
    private ImageView imgRight1;
    private ImageView imgRight2;
    private TextView tvTitle;

    public TitleView(Context context) {
        super(context);

    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this);
        imgLeft = (ImageView) findViewById(R.id.img_left);
        imgRight1 = (ImageView) findViewById(R.id.img_right_1);
        imgRight2 = (ImageView) findViewById(R.id.img_right_2);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    public void setTitleText(String text) {
        tvTitle.setText(text);
    }

    public void setTvTitle(String text) {
        tvTitle.setText(text);
    }

    public void setImgLeftOnClickListener(OnClickListener leftOnClickListener) {
        imgLeft.setOnClickListener(leftOnClickListener);
    }

    public void setImgRight1OnClickListener(OnClickListener leftOnClickListener) {
        imgRight1.setOnClickListener(leftOnClickListener);
    }

    public void setImgRight2OnClickListener(OnClickListener leftOnClickListener) {
        imgRight2.setOnClickListener(leftOnClickListener);
    }

    public ImageView getImgLeft() {
        return imgLeft;
    }

    public void setImgLeft(Drawable img) {
        imgLeft.setImageDrawable(img);
    }

    public ImageView getImgRight2() {
        return imgRight2;
    }

    public void setImgRight2(Drawable img) {
        imgRight2.setImageDrawable(img);
    }

    public ImageView getImgRight1() {
        return imgRight1;
    }

    public void setImgRight1(Drawable img) {
        imgRight1.setImageDrawable(img);
    }
}
