package com.example.tu4.utils;

import android.content.Context;
import android.util.Log;

import com.duanqu.qupai.auth.AuthService;
import com.duanqu.qupai.auth.QupaiAuthListener;

import static com.example.tu4.utils.ApplicationStaticConstants.accessToken;

public class Auth {

    private static final String AUTHTAG = "QupaiAuth";
    private static Auth instance;

    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    /**
     * 鉴权 建议只调用一次,在demo里面为了测试调用了多次 得到accessToken，通常一个用户对应一个token
     * @param context
     * @param appKey    appkey
     * @param appsecret appsecret
     * @param space     space
     */
    public void initAuth(Context context , String appKey, String appsecret, String space){
        Log.e("Live","accessToken" + accessToken);
        Log.e("Live","space" + space);

        AuthService service = AuthService.getInstance();
        service.setQupaiAuthListener(new QupaiAuthListener() {
            @Override
            public void onAuthError(int errorCode, String message) {
                Log.e(AUTHTAG, "ErrorCode" + errorCode + "message" + message);
            }

            @Override
            public void onAuthComplte(int responseCode, String responseMessage) {
                Log.e(AUTHTAG, "onAuthComplte" + responseCode + "message" + responseMessage);
                accessToken = responseMessage;
                Log.w(AUTHTAG,"成功成功成功成功成功成功成功成功成功成功成功成功成功成功成功"+accessToken);
            }
        });
        service.startAuth(context,appKey, appsecret, space);
    }

}
