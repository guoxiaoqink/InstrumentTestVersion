package com.example.tu4.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import okhttp3.Call;

import static com.example.tu4.model.AplicationStatic.Introduction;
import static com.example.tu4.model.AplicationStatic.Location;
import static com.example.tu4.model.AplicationStatic.LoginResult;
import static com.example.tu4.model.AplicationStatic.Other;
import static com.example.tu4.model.AplicationStatic.UserId;
import static com.example.tu4.model.AplicationStatic.UserName;
import static com.example.tu4.model.AplicationStatic.UserTel;
import static com.example.tu4.model.AplicationStatic.account;
import static com.example.tu4.model.IUrl.baseUrl;

/**
 * Created by gxq on
 * Descripyion: 登陆，注册，界面
 * Version：1
 * Modify Person：gxq
 */
public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    /*
    * 判断是登录还是注册，默认的是注册，就是注册时true，登录时false
    * */
    Boolean judgeLoginOrRegister = true;
    Boolean judgePasswordShow = true;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.edtTel)
    EditText edtTel;
    @BindView(R.id.textview_line_Tel)
    TextView textviewLineTel;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShowPassword)
    ImageView imgShowPassword;
    @BindView(R.id.textview_line_password)
    TextView textviewLinePassword;
    @BindView(R.id.edtVerification)
    EditText edtVerification;
    @BindView(R.id.btnGetVertification)
    Button btnGetVertification;
    @BindView(R.id.RalativelayoutVertifycation)
    RelativeLayout RalativelayoutVertifycation;
    @BindView(R.id.textview_line_vertification)
    TextView textviewLineVertification;
    @BindView(R.id.btnLoginOrRegister)
    Button btnLoginOrRegister;
    private TimeCount time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        edtTel.setOnFocusChangeListener(this);
        edtPassword.setOnFocusChangeListener(this);
        edtVerification.setOnFocusChangeListener(this);
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        // SMS初始化
        BmobSMS.initialize(LoginActivity.this, "db03e03fc5d422ec028e9adfbb8f68e6");

    }

    @OnClick({R.id.btnLogin, R.id.btnRegister, R.id.imgShowPassword, R.id.btnGetVertification,
            R.id.btnLoginOrRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                switchLoginOrRegister(R.drawable.shape, R.drawable.shape2, View.INVISIBLE,
                        R.string.login, false);
                break;
            case R.id.btnRegister:
                switchLoginOrRegister(R.drawable.shape2, R.drawable.shape, View.VISIBLE,
                        R.string.register, true);
                break;
            case R.id.imgShowPassword:
                switchPasswordShowOrHide();
                break;
            case R.id.btnGetVertification:
                // Toast.makeText(LoginActivity.this, "获取验证码", Toast.LENGTH_LONG).show();
                // 将按钮设置为不可用状态
                btnGetVertification.setEnabled(false);
                //启动倒计时
                time.start();
                BmobSMS.requestSMSCode(LoginActivity.this, edtTel.getText().toString(), "天才", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        if (ex == null) {//验证码发送成功
                            Log.e("bmob", "短信id：" + smsId);//用于查询本次短信发送详情
                        }
                    }
                });

                break;
            case R.id.btnLoginOrRegister:
                judgeLoginOrRegister();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edtTel:
                controlLineShowWhite(textviewLineTel, hasFocus);
                break;
            case R.id.edtPassword:
                controlLineShowWhite(textviewLinePassword, hasFocus);
                break;
            case R.id.edtVerification:
                controlLineShowWhite(textviewLineVertification, hasFocus);
                break;
        }
    }

    /*
     * 判断密码和电话号码,是不是符合要求
     * */
    public Boolean judgeTelPassword() {
        Boolean vertification = true;
        String telLenth = edtTel.getText().toString();
        if (!(telLenth.length() == 11)) {
            Toast.makeText(LoginActivity.this, R.string.telWrong, Toast.LENGTH_SHORT).show();
            vertification = false;
        }
        String passwordLenth = edtPassword.getText().toString();
        if (!(passwordLenth.length() >= 6 && passwordLenth.length() <= 12)) {
            Toast.makeText(LoginActivity.this, R.string.passwordWrong, Toast.LENGTH_SHORT).show();
            vertification = false;
        }
        return vertification;
    }

    /*
       * 判断验证码是不是符合规定，如果是返回的结果是true，那就是这个符合要求的。
       * */
    public Boolean judgeVertification() {
        Boolean judge = true;
        String vertificationLenth = edtVerification.getText().toString();
        if (vertificationLenth.length() == 0) {
            Toast.makeText(LoginActivity.this, R.string.verificationNull,
                    Toast.LENGTH_SHORT).show();
            judge = false;
        }
        return judge;
    }


    public void switchPasswordShowOrHide() {
        if (judgePasswordShow) {
            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        judgePasswordShow = !judgePasswordShow;
    }

    public void switchLoginOrRegister(int loginbg, int registerbg, int visible, int text,
                                      Boolean bool) {
        btnLogin.setBackgroundResource(loginbg);
        btnRegister.setBackgroundResource(registerbg);
        RalativelayoutVertifycation.setVisibility(visible);
        textviewLineVertification.setVisibility(visible);
        btnLoginOrRegister.setText(text);
        judgeLoginOrRegister = bool;
    }

    public void judgeLoginOrRegister() {

        if (judgeLoginOrRegister) {//代表当前的是注册页面
            String number = edtVerification.getText().toString();
            if (!TextUtils.isEmpty(number)) {
                //通过verifySmsCode方式可验证该短信验证码
                BmobSMS.verifySmsCode(LoginActivity.this, edtTel.getText().toString(), number, new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException ex) {
                        if (ex == null) {//短信验证码已验证成功
                            Log.e("bmob", "验证通过");
                            String url = baseUrl + "/regist/getdata";
                            OkHttpUtils
                                    .postString()
                                    .url(url)//
                                    .content(new Gson().toJson(new User(edtTel.getText().toString(), edtPassword.getText().toString())))
                                    .tag(this)//
                                    .build()//
                                    .connTimeOut(20000)
                                    .readTimeOut(20000)
                                    .writeTimeOut(20000)
                                    .execute(new StringCallback()      //这个是没有处理json
                                    {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.d("onError:", e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.d("success", response);

                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                if (jsonObject.getInt("id") == -1) {
                                                    Toast.makeText(LoginActivity.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                                                    edtVerification.setText("");
                                                } else {
                                                    UserId = jsonObject.getInt("id");
                                                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                }
                                                Log.d("id", String.valueOf(UserId));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                    });

                            if (judgeTelPassword() && judgeVertification()) {
                                Toast.makeText(LoginActivity.this,
                                        getResources().getString(R.string.registerSuccess),
                                        Toast.LENGTH_SHORT).show();
              /*  Intent intentToSearchactivity = new Intent(LoginActivity.this,
                        SearchActivity.class);
                startActivity(intentToSearchactivity);*/
                            }

                        } else {
                            Log.e("bmob", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                            Toast.makeText(LoginActivity.this, "验证码错误！", Toast.LENGTH_SHORT).show();
                            edtVerification.setText("");
                        }
                    }
                });
            }

        } else {
            if (judgeTelPassword()) {

                String url = baseUrl + "/login/api_login";
                OkHttpUtils
                        .postString()//
                        .url(url)//
                        .content(new Gson().toJson(new User(edtTel.getText().toString(), edtPassword.getText().toString())))
                        .build()//
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.d("onError:", e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                Log.d("onResponse:", response);
                                System.out.print(id);
                                System.out.print(response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String result = jsonObject.getString("Result");
                                    int type = jsonObject.getInt("Type");
                                    System.out.print(result);
                                    Log.d("type:", "type  " + type);
                                    if (type == 2) {
                                        Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                                    } else if (type == 3) {
                                        UserId = jsonObject.getInt("User_id");
                                        UserName = jsonObject.getString("Username");
                                        account = UserName;
                                        Introduction = jsonObject.getString("Introduction");
                                        LoginResult = jsonObject.getString("Result");
                                        Location = jsonObject.getString("Location");
                                        Other = jsonObject.getString("Other");
                                        UserTel = edtTel.getText().toString();
                                        Intent intent = new Intent();
                                        intent.setClass(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        //saveDate();//
                                        finish();
                                    } else if (type == 1) {
                                        Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }
    }

    /**
     * 保存用户数据，判断是否登录过
     */
    private void saveDate() {

        SharedPreferences mySharedPreferences = getSharedPreferences("test",
                Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = mySharedPreferences.edit();

        editor.putString("name", edtTel.getText().toString());
        editor.putString("password", edtPassword.getText().toString());

        editor.commit();
    }


    public void controlLineShowWhite(TextView line, Boolean fous) {
        if (fous) {
            line.setBackgroundResource(R.color.sffffff);
        } else {

            line.setBackgroundResource(R.color.s40sffffff);
        }
    }

    private class User {
        public String username;
        public String password;

        public User() {
            // TODO Auto-generated constructor stub
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            btnGetVertification.setText("获取验证码");
            btnGetVertification.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            btnGetVertification.setClickable(false);
            btnGetVertification.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
