package com.example.tu4.activity.personal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.MyWorksSelectGridviewAdapter;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by hs on
 * Descripyion: 删除作品界面
 * Version：1
 * Modify Person：gxq
 */
public class MyWorksDeleteActivity extends AppCompatActivity {

    private ArrayList<Map<String, String>> listData;
    private Map<String, String> mapData;

    @BindView(R.id.img_my_works_delete_return)
    ImageView imgMyWorksDeleteReturn;
    private ArrayList<Integer> myWorksPisture;
    private ArrayList<String> myWorksTime, myWorksDate;
    ArrayList<String> deleteList;
    MyWorksSelectGridviewAdapter myWorksSelectGridviewAdapter;


    @BindView(R.id.gv_my_works_select)
    ResolveConflictsScoolviewGridview gvMyWorksSelect;

    @BindView(R.id.bt_delete)
    Button btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_works_delete);
        ButterKnife.bind(this);
        initDate();

        new MyOpenWork().execute();

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteList = myWorksSelectGridviewAdapter.getNameList();
                Log.w("deleteList",deleteList.toString());
                if (deleteList.size() > 0) {
                    StringBuffer sbBuffer = new StringBuffer();
                    sbBuffer.append("您已删除： ");
                    for (int i = 0; i < deleteList.size(); i++) {
                       for (int j = 0;j<listData.size();j++){
                           if (listData.get(j).get("date") == deleteList.get(i)){
                               listData.remove(j);
                           }
                       }
                    }
                    sbBuffer.append(deleteList.size() + " 个");
                    myWorksSelectGridviewAdapter.fresh();
                    Toast.makeText(MyWorksDeleteActivity.this, sbBuffer.toString(),
                            Toast.LENGTH_LONG).show();
                    new MyOpenWork().execute();
                }
            }
        });
    }

    void initDate() {

        listData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mapData = new HashMap<>();
            mapData.put("img", R.mipmap.a + "");
            mapData.put("time", i + "0s");
            mapData.put("date", "2016-05-06 " + "1" + i + ":00");
            listData.add(mapData);
        }
    }

    @OnClick(R.id.img_my_works_delete_return)
    public void onClick() {
        Intent intent = new Intent(MyWorksDeleteActivity.this, MyWorksActivity.class);
        intent.putStringArrayListExtra("deleteList", deleteList);
        startActivity(intent);
        finish();
    }



    class MyOpenWork extends AsyncTask<String, String, Boolean> {
        ProgressDialog dialog;

        @Override
        protected Boolean doInBackground(String... arg0) {
            myWorksSelectGridviewAdapter = new MyWorksSelectGridviewAdapter(MyWorksDeleteActivity
                    .this, listData);
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MyWorksDeleteActivity.this);
            dialog.setMessage("初始化图片,请稍候...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {

            super.onPostExecute(result);
            dialog.dismiss();
            myHandler.sendEmptyMessage(0);
        }

    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            gvMyWorksSelect.setAdapter(myWorksSelectGridviewAdapter);
            // 设置点击监听
            gvMyWorksSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {

                    myWorksSelectGridviewAdapter.changeState(position);
                }
            });
        }
    };

}
