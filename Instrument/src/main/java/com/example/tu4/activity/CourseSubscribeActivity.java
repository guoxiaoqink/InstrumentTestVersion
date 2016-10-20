package com.example.tu4.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.CourseInfoListviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tu4.model.AplicationStatic.account;

/**
 * Created by MR.WEN on 2016/10/13.
 * 课程订购页面
 */
public class CourseSubscribeActivity extends AppCompatActivity {
    @BindView(R.id.tv_account_details)
    TextView accountDetails;
    @BindView(R.id.et_account_name)
    EditText accountName;
    @BindView(R.id.et_account_tel)
    EditText accountTel;
    @BindView(R.id.tv_total_money)
    TextView totalMoney;
    private ListView course_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_course_subscribe);
        ButterKnife.bind(this);
        course_info = (ListView) findViewById(R.id.list_course_info);
        accountDetails.setText(account);
        initListviewCourseinfo();


    }

    //    初始化课程信息列表方法
    public void initListviewCourseinfo() {
        CourseInfoListviewAdapter courseInfoListviewAdapter = new CourseInfoListviewAdapter(
                CourseSubscribeActivity.this);
        course_info.setAdapter(courseInfoListviewAdapter);

    }

    //返回按钮点击事件
    public void course_order_return(View v) {
        this.finish();
    }

    //确认支付按钮点击事件
    public void ensure_order(View v) {
        if (accountTel.length() == 0 || accountName.length() == 0) {
            Toast.makeText(this, "姓名和手机号码不能为空", Toast.LENGTH_SHORT).show();
        } else if (accountTel.length() != 11) {
            Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent();
            intent.putExtra("account", account);
            intent.putExtra("TrueNmae", accountName.getText().toString().trim() + "  " + accountTel.getText().toString().trim());
            intent.putExtra("TotalMoney", totalMoney.getText().toString().trim());
            intent.setClass(CourseSubscribeActivity.this, OrderPaymentActivity.class);
            startActivity(intent);
        }
    }


}
