package com.example.tu4.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.App;
import com.example.tu4.R;
import com.example.tu4.adapter.ConnectUsGridviewAdapter;
import com.example.tu4.model.AplicationStatic;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * Created by gxq on
 * Descripyion: 联系我们界面
 * Version：1
 * Modify Person：gxq
 */
public class ConnectUsActivity extends AppCompatActivity {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.tv_telephone_call)
    TextView tvTelephoneCall;
    @BindView(R.id.gv_service)
    GridView gvService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_us);
        ButterKnife.bind(this);
        initGridview();
    }


    public void initGridview() {
        ConnectUsGridviewAdapter connectUsGridviewAdapter = new ConnectUsGridviewAdapter(AplicationStatic.getCustomServeName(), getBaseContext());
        gvService.setAdapter(connectUsGridviewAdapter);
        gvService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    connect("PmJOt+9YfUwb3qfQAC/FHTgmoQcIdCxDa4bGqIzVbQQf6xwRu8h2Um5/mP8Qz6F0CeNlClwibT4=");
                }
            }
        });
    }


    @OnClick({R.id.img_return, R.id.tv_telephone_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                this.finish();
                break;
            case R.id.tv_telephone_call:
                break;
        }
    }

    /*
        *
         * 建立与融云服务器的连接
         *
         * @param token
         */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                    Log.d("LoginActivity", "--onTokenIncorrect");
                }


                @Override
                public void onSuccess(String userid) {

                    Log.d("LoginActivity", "--onSuccess" + userid);
                    CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                    CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();

/**
 * 启动客户服聊天界面。
 *
 * @param context           应用上下文。
 * @param customerServiceId 要与之聊天的客服 Id。
 * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
 * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
 */
                    RongIM.getInstance().startCustomerServiceChat(ConnectUsActivity.this, "KEFU147748161344978", "在线客服", csInfo);
                    //                 startActivity(new Intent(MainActivity.this, Main2Activity.class));

                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }
}
