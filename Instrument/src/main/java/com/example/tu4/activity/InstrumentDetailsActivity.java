package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstrumentDetailsActivity extends AppCompatActivity {
    @BindView(R.id.img_return)
    ImageView imgReturn;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_details);
        ButterKnife.bind(this);

        button = (Button) findViewById(R.id.btn_buy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstrumentDetailsActivity.this, OrderDetailsMainActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.img_return)
    public void onClick() {
        this.finish();
    }
}
