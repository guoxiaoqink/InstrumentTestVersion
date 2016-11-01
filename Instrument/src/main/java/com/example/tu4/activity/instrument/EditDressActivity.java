package com.example.tu4.activity.instrument;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tu4.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.model.AplicationStatic.UserId;
import static com.example.tu4.model.IUrl.baseUrl;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 编辑收货地址
 * Version: 1
 * Modify Person : Moofei
 */
public class EditDressActivity extends AppCompatActivity {

    @BindView(R.id.imgbtn_edit_left)
    ImageView imgbtnEditLeft;
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
    private int mDayColor = Color.parseColor("#000000");
    private String[] data = {userid, "student", etEditName.getText().toString(), etEditName.getText().toString(), etEditDress.getText().toString()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dress);
        ButterKnife.bind(this);
        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String phone = in.getStringExtra("phone");
        String dress = in.getStringExtra("dress");
        EditText nameEditText = (EditText) findViewById(R.id.et_edit_name);
        EditText phoneEditText = (EditText) findViewById(R.id.et_edit_phone);
        EditText dressEditText = (EditText) findViewById(R.id.et_edit_dress);
        nameEditText.setText(name);
        nameEditText.setTextColor(mDayColor);
        phoneEditText.setText(phone);
        phoneEditText.setTextColor(mDayColor);
        dressEditText.setText(dress);
        dressEditText.setTextColor(mDayColor);


    }

    @OnClick({R.id.imgbtn_edit_left, R.id.rl_edit_del, R.id.btn_save_dress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_edit_left:
                this.finish();
                break;
            case R.id.rl_edit_del:
                Toast.makeText(EditDressActivity.this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save_dress:
                Toast.makeText(EditDressActivity.this, "保存", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getAddDress() {
        String url = baseUrl + "";
        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new putDress("2008", data)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }

    private class putDress {
        private String code;
        private int User_id;
        private String role;
        private String consigneeName;
        private String phoneNumber;
        private String consigneeAddress;

        public putDress(String code, String[] data) {
            this.code = code;
            this.User_id = Integer.parseInt(data[0]);
            this.role = data[1];
            this.consigneeName = data[2];
            this.phoneNumber = data[3];
            this.consigneeAddress = data[4];
        }

        @Override
        public String toString() {
            return "putDress{" +
                    "code='" + code + '\'' +
                    "data[{" + "User_id='" + User_id + '\'' +
                    "role='" + role + '\'' +
                    "consigneeName='" + consigneeName + '\'' +
                    "phoneNumber='" + phoneNumber + '\'' +
                    "consigneeAddress='" + consigneeAddress + '\'' +
                    '}' + ']' + '}';
        }
    }
}
