package com.example.tu4.activity.course.feedback;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.IssiuListviewAdapter;
import com.example.tu4.bean.IssiuFeedbascPost;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.FEEDBACK;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;

/**
 * Created by WQJ on 2016/10/21
 * Descripyion: 这个是发布反馈页面
 * Version: 1
 * Modify Person : wqj
 */

public class IssiuFeedbackActivity extends AppCompatActivity {


    private static boolean isShowPopup = false;
    @BindView(R.id.tv_complaint_type)
    TextView tvComplaintType;
    @BindView(R.id.imgbt_complaint_type)
    ImageButton imgbtComplaintType;
    @BindView(R.id.img_somplaints_suggestion_return)
    ImageView imgSomplaintsSuggestionReturn;
    @BindView(R.id.tv_issiu)
    TextView tvIssiu;
    @BindView(R.id.pop_re)
    RelativeLayout popRe;
    @BindView(R.id.ed_complaint_context)
    EditText edComplaintContext;
    private PopupWindow mPopupWindow;
    private ListView listView;
    private ImageView checked_image;
    private TextView class_time;
    private int classTimeType;//记录选择的是那个课时

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_issiu_feedback);
        ButterKnife.bind(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        // 引入窗口配置文件
        View view = inflater.inflate(R.layout.chose_class_popupwindow, null);
        View view1 = inflater.inflate(R.layout.issiu_listview_item, null);
        tvComplaintType.setText("选择课时");
        imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_bottom);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        listView = (ListView) view.findViewById(R.id.list_issiu);
        class_time = (TextView) view1.findViewById(R.id.class_time);
        checked_image = (ImageView) view1.findViewById(R.id.check_image);
        listView.setAdapter(new IssiuListviewAdapter(IssiuFeedbackActivity.this));//配置适配器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {//popupwindow中listview点击事件
                checked_image.setBackgroundResource(R.mipmap.payment_radiobutton_checked);
                if (position == 0) {
                    tvComplaintType.setText("课时1");
                    classTimeType = 1;
                } else if (position == 1) {
                    tvComplaintType.setText("课时2");
                    classTimeType = 2;
                } else {
                    tvComplaintType.setText("课时3");
                    classTimeType = 3;
                }
                mPopupWindow.dismiss();
                imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_bottom);
            }
        });
    }

    /**
     * 获取数据
     */

    void getData() {
        //获取当前系统时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String date = sDateFormat.format(new java.util.Date());
        Log.w("时间", date);
        String context = edComplaintContext.getText().toString();
        Log.w("context", context);
//        String url =  "http://128.199.137.227:8080/myapi/feedback/api_feedBack";
        OkHttpUtils
                .postString()
                .url(FEEDBACK)
                .content(new Gson().toJson(new IssiuFeedbascPost("2061", "20161108", classTimeType,context, 1   , UserId)))
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("onError", e.getMessage());
                        Toast.makeText(IssiuFeedbackActivity.this, "发布失败", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.w("onResponse", response);
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            String result = jsonobject.getString("result");
                            Log.w("result", result);
                            if (result.equals("ok")) {
                                Toast.makeText(IssiuFeedbackActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    @OnClick({R.id.img_somplaints_suggestion_return, R.id.tv_issiu, R.id.pop_re})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_somplaints_suggestion_return://返回按钮
                this.finish();
                break;
            case R.id.tv_issiu://发布按钮
                getData();
                Intent in = new Intent();
                in.setClass(IssiuFeedbackActivity.this, Student_feedbackActivity.class);
                startActivity(in);
//                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pop_re://popupwindow布局框点击事件
                imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_top);
                if (mPopupWindow.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mPopupWindow.dismiss();
                } else {
                    // 显示窗口
                    mPopupWindow.showAsDropDown(view);
                }
                break;
        }
    }
}
