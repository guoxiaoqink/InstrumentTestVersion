package com.example.tu4.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import okhttp3.Call;


public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    public static int UserId;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        edtTel.setOnFocusChangeListener(this);
        edtPassword.setOnFocusChangeListener(this);
        edtVerification.setOnFocusChangeListener(this);

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
            String url = "http://138.68.11.223:8080/regist/getdata";
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
                            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                UserId = jsonObject.getInt("id");
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
            if (judgeTelPassword()) {
//                account = edtTel.getText().toString().trim();
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
                String Url = "http://138.68.11.223:8080/login/api_login";
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("username", "11144477700");
//                params.put("password", "000000");
                OkHttpUtils
                        .postString()//
                        .url(Url)//
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
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String result = jsonObject.getString("Result");
                                    UserId = jsonObject.getInt("User_id");
                                    Log.d("Result", result);
                                    System.out.print(result);
                                    if (result.equals("false")) {
                                        Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent();
                                        intent.setClass(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        //saveDate();//
                                        finish();
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
}
