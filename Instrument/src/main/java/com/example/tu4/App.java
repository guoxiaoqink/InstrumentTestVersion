package com.example.tu4;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.rong.imkit.RongIM;
import okhttp3.OkHttpClient;

public class App extends Application {
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
//        String certPath = "F:\\MyFiles\\1834497859\\FileRecv\\server.crt";
//        String jksPath = "F:\\MyFiles\\1834497859\\FileRecv\\TestKeygen.jks";
        try {
           // InputStream in2 = new FileInputStream(jksPath);
           //getAssets().open("srca.cer")
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getAssets().open("server.crt"), getAssets().open("app.bks"), "123456");
//        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .addInterceptor(new LoggerInterceptor("TAG"))
                    //.cookieJar(cookieJar1)
                    .hostnameVerifier(new HostnameVerifier()
                    {
                        @Override
                        public boolean verify(String hostname, SSLSession session)
                        {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
            OkHttpUtils.initClient(okHttpClient);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();//应用程序退出时调用

    }
}

/**
 * Created by Bob on 15/8/18.
 * <p>
 * <p>
 * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
 * io.rong.push 为融云 push 进程名称，不可修改。
 * <p>
 * IMKit SDK调用第一步 初始化
 * <p>
 * 获得当前进程的名字
 *
 * @param context
 * @return 56146
 * rjlNearK6Qzu3MxMbMrQPLI6ZiT8q7s0UEaMPWY0lMwZFCr6nvpDtW4C3cjQ9tv4cIQMvwbkJb6xzmBRkFjv+Q==
 * 112@rongcloud.com
 * 123456
 * rongcloud112
 * <p>
 * 56147
 * ke0BCyqQnwWiagWoS1ckzrI6ZiT8q7s0UEaMPWY0lMwZFCr6nvpDtX2/KvQEWEDo6r3YdoOyCDqUgN53uY4SEA==
 * 113@rongcloud.com
 * 123456
 * rongcloud113
 * <p>
 * 56148
 * mYLERi6S6fkqdGjEIJrEubI6ZiT8q7s0UEaMPWY0lMwZFCr6nvpDtQIt9svl5Wir1X0Zf3Hy5T1QRLmZJrAbgQ==
 * 114@rongcloud.com
 * 123456
 * rongcloud114
 */
/*
public class App extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        */
/**
 *
 * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
 * io.rong.push 为融云 push 进程名称，不可修改。
 *//*

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            */
/**
 * IMKit SDK调用第一步 初始化
 *//*

            RongIM.init(this);

            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

                DemoContext.init(this);
            }
        }
    }
*/

/**
 * 获得当前进程的名字
 *
 * @param context
 * @return
 */


//下面三个为好友列表四个账号中的三个账号信息，分别为:用户userid，token，登录邮箱，密码，昵称


/**
 * 56146
 * rjlNearK6Qzu3MxMbMrQPLI6ZiT8q7s0UEaMPWY0lMwZFCr6nvpDtW4C3cjQ9tv4cIQMvwbkJb6xzmBRkFjv+Q==
 * 112@rongcloud.com
 * 123456
 * rongcloud112
 */

/**
 * 56147
 * ke0BCyqQnwWiagWoS1ckzrI6ZiT8q7s0UEaMPWY0lMwZFCr6nvpDtX2/KvQEWEDo6r3YdoOyCDqUgN53uY4SEA==
 * 113@rongcloud.com
 * 123456
 * rongcloud113
 */

/**
 * 56148
 * mYLERi6S6fkqdGjEIJrEubI6ZiT8q7s0UEaMPWY0lMwZFCr6nvpDtQIt9svl5Wir1X0Zf3Hy5T1QRLmZJrAbgQ==
 * 114@rongcloud.com
 * 123456
 * rongcloud114
 */
