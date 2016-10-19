package com.example.tu4.activity;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditDressActivity extends AppCompatActivity {

    @BindView(R.id.imgbtn_edit_left)
    ImageView imgbtnEditLeft;
    @BindView(R.id.rl_edit_del)
    RelativeLayout rlEditDel;
    @BindView(R.id.btn_save_dress)
    Button btnSaveDress;

    private int mDayColor = Color.parseColor("#000000");

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
}
