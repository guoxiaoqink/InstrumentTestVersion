package com.example.tu4.activity.personal;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.bean.SuggestionsPost;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.JUMP_MAINACTIVITY;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;
import static com.example.tu4.utils.IUrl.baseUrl;

/**
 * Created by hs on
 * Descripyion:投诉建议界面
 * Version：1
 * Modify Person：gxq
 */
public class ComplaintsSuggestionsActivity extends AppCompatActivity implements View
        .OnClickListener {

    private static boolean isShowPopup = false;
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
    @BindView(R.id.ed_complaint_context)
    EditText edComplaintContext;
    private View popupView;
    private PopupWindow mPopupWindow;
    private RadioButton radioButton;
    private int type;//投诉类型：1-投诉 2-操作建议 3-其他

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
                } else {
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
                //投诉类型：1-投诉 2-操作建议 3-其他
                if (s.equals("投诉")) {
                    type = 1;
                } else if (s.equals("操作建议")) {
                    type = 2;
                } else if (s.equals("其他")) {
                    type = 3;
                }
                Log.w("type", type + "fffffffffffffffffffff");
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
                postDate();
                this.finish();
                JUMP_MAINACTIVITY = 2;
                break;
            case R.id.img_somplaints_suggestion_return:
                this.finish();
                JUMP_MAINACTIVITY = 2;
                break;
        }
    }

    /**
     * 提交投诉内容
     */
    private void postDate() {
        String content = edComplaintContext.getText().toString();
        String url = baseUrl + "/music-stju-test/api_complaint";
        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(new SuggestionsPost(type, content, String.valueOf(UserId),
                        "1002")))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("onError", "提交失败");
                        Toast.makeText(ComplaintsSuggestionsActivity.this, "提交失败", Toast.LENGTH_SHORT)
                                .show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.w("onResponse",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("Result");
                            Log.w("result",result);
                            if (result.equals("OK")){
                                Toast.makeText(ComplaintsSuggestionsActivity.this, "提交成功", Toast.LENGTH_SHORT)
                                        .show();
                            }else {
                                Toast.makeText(ComplaintsSuggestionsActivity.this, "提交失败，请重试", Toast.LENGTH_SHORT)
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }
}