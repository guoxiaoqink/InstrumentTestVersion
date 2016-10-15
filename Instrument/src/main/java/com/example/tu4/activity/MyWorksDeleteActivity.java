package com.example.tu4.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.adapter.MyWorksSelectGridviewAdapter;
import com.example.tu4.view.ResolveConflictsScoolviewGridview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWorksDeleteActivity extends AppCompatActivity {

    private ArrayList<Integer> myWorksPisture;
    MyWorksSelectGridviewAdapter myWorksSelectGridviewAdapter;


    @BindView(R.id.gvMyWorksSelect)
    ResolveConflictsScoolviewGridview gvMyWorksSelect;

    @BindView(R.id.btDelete)
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
                List<Integer> tempList = myWorksSelectGridviewAdapter.getNameList();
                if (tempList.size() > 0) {
                    StringBuffer sbBuffer = new StringBuffer();
                    sbBuffer.append("您已删除：" + "\n");
                    for (int i = 0; i < tempList.size(); i++) {
                        myWorksPisture.remove(tempList.get(i));
                        sbBuffer.append(tempList.get(i)).append("\n");
                    }
                    myWorksSelectGridviewAdapter.fresh();
                    Toast.makeText(MyWorksDeleteActivity.this, sbBuffer.toString(),
                            Toast.LENGTH_LONG).show();
                    new MyOpenWork().execute();
                }
            }
        });
    }

    void initDate() {
        myWorksPisture = new ArrayList<>();
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
        myWorksPisture.add(R.mipmap.a);
    }

    class MyOpenWork extends AsyncTask<String, String, Boolean> {
        ProgressDialog dialog;

        @Override
        protected Boolean doInBackground(String... arg0) {
            myWorksSelectGridviewAdapter = new MyWorksSelectGridviewAdapter(MyWorksDeleteActivity
                    .this, myWorksPisture);
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
        public void handleMessage(android.os.Message msg) {
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
