package com.example.tu4.activity.instrument;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.view.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.EDITDRESS_URL;
import static com.example.tu4.utils.ApplicationStaticConstants.UserId;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 编辑收货地址
 * Version: 1
 * Modify Person : Moofei
 */
public class EditDressActivity extends AppCompatActivity {

    @BindView(R.id.rl_edit_del)
    RelativeLayout rlEditDel;
    @BindView(R.id.btn_save_dress)
    Button btnSaveDress;
    @BindView(R.id.et_edit_name)
    EditText etEditName;
    @BindView(R.id.et_edit_phone)
    EditText etEditPhone;
    @BindView(R.id.et_edit_dress)
    EditText etEditDress;
    String userid = "" + UserId;
    @BindView(R.id.rl_E_D)
    TitleView rlED;
    private int mDayColor = Color.parseColor("#000000");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_edit_dress);
        ButterKnife.bind(this);
        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String phone = in.getStringExtra("phone");
        String dress = in.getStringExtra("dress");
        etEditName.setText(name);
        etEditName.setTextColor(mDayColor);
        etEditPhone.setText(phone);
        etEditPhone.setTextColor(mDayColor);
        etEditDress.setText(dress);
        etEditDress.setTextColor(mDayColor);
        Resources res = getResources();
        String title = "编辑地址".toString();
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        rlED.setImgLeft(ic_return);
        rlED.getImgLeft().setVisibility(View.VISIBLE);
        rlED.setTitleText(title);
        rlED.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDressActivity.this.finish();
            }
        });

    }

    @OnClick({R.id.rl_edit_del, R.id.btn_save_dress})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rl_edit_del:
                Toast.makeText(EditDressActivity.this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save_dress:

                getAddDress();
                break;
        }
    }

    private void getAddDress() {
        OkHttpUtils.postString()
                .url(EDITDRESS_URL)
                .content(new Gson().toJson(new putDress("2008", UserId, etEditName.getText().toString(), etEditPhone.getText().toString(), etEditDress.getText().toString())))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(EditDressActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        Log.d("success", response);
                    }
                });
    }

    private class putDress {
        private String code;
        private int user_id;
        private String receiver;
        private String address;
        private String tel;

        public putDress(String code, int user_id, String receiver, String tel, String address) {
            this.code = code;
            this.user_id = user_id;
            this.receiver = receiver;
            this.tel = tel;
            this.address = address;
        }

        @Override
        public String toString() {
            return "putDress{" +
                    "code='" + code + '\'' +
                    "user_id='" + user_id + '\'' +
                    "receiver='" + receiver + '\'' +
                    "tel='" + tel + '\'' +
                    "address='" + address + '\'' +
                    '}';
        }
    }
}
