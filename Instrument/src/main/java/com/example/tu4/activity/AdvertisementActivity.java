package com.example.tu4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tu4.R;

import java.util.regex.Pattern;

/**
 * 项目名称 : Broker<br>
 * 创建人 : dgs<br>
 * 创建时间 : 2016-4-15上午11:41:44<br>
 * 版本 :	[v1.0]<br>
 * 类描述 : 广告链接展示<br>
 */
public class AdvertisementActivity extends Activity {

    /**
     * 正则表达式:验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w ./?%&=]*)?";

    private WebView advertisementWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        initViews();
    }

    private void initViews() {
        String linkPath = getIntent().getStringExtra("linkPath");
        advertisementWeb = (WebView) findViewById(R.id.advertisement_web);
        WebSettings settings = advertisementWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        if (Pattern.matches(REGEX_URL, linkPath)) {
            advertisementWeb.loadUrl(linkPath);
        } else {
            advertisementWeb.loadUrl("http://m.baidu.com");
        }
        advertisementWeb.setWebViewClient(new mWebViewClient());
        //advertisementWeb.setWebChromeClient(new WebChromeClient());
    }

    /**
     * WebView监听
     */
    class mWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
