package com.example.tu4.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tu4.model.AplicationStatic.JUMP_MAINACTIVITY;

public class ComplaintsSuggestionsActivity extends AppCompatActivity implements View
        .OnClickListener {

    @BindView(R.id.imgbt_complaint_type)
    ImageButton imgbtComplaintType;

    RadioGroup rgComplaintType;
    @BindView(R.id.tv_complaint_type)

    TextView tvComplaintType;
    @BindView(R.id.bt_complaint_push)
    Button btComplaintPush;
    @BindView(R.id.img_somplaints_suggestion_return)
    ImageView imgSomplaintsSuggestionReturn;
    @BindView(R.id.re_popup)
    RelativeLayout rePopup;

    private View popupView;
    private static boolean isShowPopup = false;
    private PopupWindow mPopupWindow;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_suggestions);
        ButterKnife.bind(this);

        btComplaintPush.setOnClickListener(this);
        imgSomplaintsSuggestionReturn.setOnClickListener(this);

        tvComplaintType.setText("请选择意见类型");
        imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_bottom);

        popupView = LayoutInflater.from(this).inflate(R.layout.activity_complaint_popupwindow,
                null);
        rgComplaintType = (RadioGroup) popupView.findViewById(R.id.rg_complaint_type);

        rePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowPopup == false) {
                    showPopup(v);
                    imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_top);
                    isShowPopup = true;
                }else {
                    mPopupWindow.dismiss();
                    imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_bottom);
                    isShowPopup = false;
                }

            }
        });
        rgComplaintType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) popupView.findViewById(checkedId);
                String s = radioButton.getText().toString();
                tvComplaintType.setText(s);
                mPopupWindow.dismiss();
                imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_bottom);
            }
        });

    }

    /**
     * 显示投诉建议的类型
     *
     * @param v
     */
    void showPopup(View v) {

        mPopupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar
                .LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAsDropDown(v, 10, 60);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_complaint_push:
//                Intent intent = new Intent(ComplaintsSuggestionsActivity.this, MainActivity
// .class);
//                startActivity(intent);
                this.finish();
                JUMP_MAINACTIVITY = 2;
                break;
            case R.id.img_somplaints_suggestion_return:
//                Intent intent1 = new Intent(ComplaintsSuggestionsActivity.this, MainActivity
// .class);
//                startActivity(intent1);
                this.finish();
                JUMP_MAINACTIVITY = 2;
                break;
        }
    }
}