package com.example.tu4.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tu4.R;

import static com.example.tu4.utils.ApplicationStaticConstants.JUMP_MAINACTIVITY;

/**
 * Created by hs
 * Descripyion: 刚进入软件的界面，软件介绍页面
 * Version：1
 * Modify Person：gxq
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        JUMP_MAINACTIVITY = 1;
        new MyAsyncTask().execute();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... arg0) {
            int result = 0;

            SharedPreferences sharedPreferences = getSharedPreferences("test",
                    Activity.MODE_PRIVATE);
            String name = sharedPreferences.getString("name", "0");
            String password = sharedPreferences.getString("password", "0");
            if (name == "0" || password == "0") {
                result = 0;
            } else {
                result = 1;
            }
            try {
                new Thread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 0) {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }
}
