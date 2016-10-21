package com.example.tu4.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.IssiuListviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private PopupWindow mPopupWindow;
    private ListView listView;
    private ImageView checked_image;
    private TextView class_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issiu_feedback);
        ButterKnife.bind(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        // 引入窗口配置文件
        View view = inflater.inflate(R.layout.chose_class_popupwindow, null);
        View view1 = inflater.inflate(R.layout.issiu_listview_item, null);
        tvComplaintType.setText("选择课时");
        imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_bottom);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        listView = (ListView) view.findViewById(R.id.list_issiu);
        class_time = (TextView) view1.findViewById(R.id.class_time);
        checked_image = (ImageView) view1.findViewById(R.id.check_image);
        listView.setAdapter(new IssiuListviewAdapter(IssiuFeedbackActivity.this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checked_image.setBackgroundResource(R.mipmap.payment_radiobutton_checked);
                if (position == 0) {
                    tvComplaintType.setText("课时1");
                } else if (position == 1) {
                    tvComplaintType.setText("课时2");
                } else
                    tvComplaintType.setText("课时3");
                mPopupWindow.dismiss();
                imgbtComplaintType.setImageResource(R.mipmap.ic_arrow_bottom);
            }
        });

    }


    @OnClick({R.id.img_somplaints_suggestion_return, R.id.tv_issiu, R.id.pop_re})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_somplaints_suggestion_return:
                this.finish();
                break;
            case R.id.tv_issiu:
                Intent in = new Intent();
                in.setClass(IssiuFeedbackActivity.this, Student_feedbackActivity.class);
                startActivity(in);
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pop_re:
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
