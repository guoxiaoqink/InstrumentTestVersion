package com.example.tu4.activity.instrument;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.tu4.model.AplicationStatic.UserId;
import static com.example.tu4.model.IUrl.baseUrl;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 选择收货地址
 * Version: 1
 * Modify Person : Moofei
 */
public class SelectDressActivity extends AppCompatActivity {


    @BindView(R.id.imgbtn_select_left)
    ImageView imgbtnSelectLeft;
    @BindView(R.id.btn_add_dress)
    Button btnAddDress;
    ListAdapter adapter;
    //    @BindView(R.id.title)
//    TitleView title;
    private ListView listView;
    private Map<Integer, Boolean> isSelected;
    private List beSelectedData = new ArrayList();
    private List name = null;
    private List phone = null;
    private List dress = null;
    private Button mbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serect_dress);
        ButterKnife.bind(this);
        listView = (ListView) this.findViewById(R.id.lv_select_dress);
        name = new ArrayList();
        name.add("收件人0");
        name.add("收件人1");
        phone = new ArrayList();
        phone.add("13412456858");
        phone.add("13412456858");
        dress = new ArrayList();
        dress.add("地点：XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        dress.add("地点：XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        //   getOrder();
        initList();
        //黄油刀监听器已创建，版本问题无法使用暂时使用普通
        mbutton = (Button) findViewById(R.id.btn_add_dress);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SelectDressActivity.this, EditDressActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void initList() {

        if (name == null || name.size() == 0)
            return;
        if (isSelected != null)
            isSelected = null;
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < name.size(); i++) {
            isSelected.put(i, false);
        }
        // 清除已经选择的项
        if (beSelectedData.size() > 0) {
            beSelectedData.clear();
        }
        adapter = new ListAdapter(this, name);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i("map", name.get(position).toString());
            }
        });

    }

    @OnClick({R.id.imgbtn_select_left, R.id.btn_add_dress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_select_left:
                this.finish();
                break;
            case R.id.btn_add_dress:
                break;
        }
    }

    private void getOrder() {
        String url = baseUrl + "";
        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new OrderDetails(UserId, "1008")))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("SUCCESS", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray1 = jsonObject.getJSONArray("Content");
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                JSONObject order1 = jsonArray1.getJSONObject(i);
                                String Recipient = order1.getString("Recipient");
                                String Telephone = order1.getString("Telephone");
                                String Address = order1.getString("Address");
                                name.add(Recipient);
                                phone.add(Telephone);
                                dress.add(Address);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    class ListAdapter extends BaseAdapter {

        private Context context;

        private List cs;

        private LayoutInflater inflater;

        public ListAdapter(Context context, List data) {
            this.context = context;
            this.cs = data;
            initLayoutInflater();
        }

        void initLayoutInflater() {
            inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return cs.size();
        }

        public Object getItem(int position) {
            return cs.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position1, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            View view = null;
            final int position = position1;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.select_dress_list_item,
                        null);
                holder = new ViewHolder();
                holder.checkBox = (CheckBox) convertView
                        .findViewById(R.id.cb_select_dress);
                holder.tv_name = (TextView) convertView
                        .findViewById(R.id.tv_SD_cnee);
                holder.tv_phone = (TextView) convertView
                        .findViewById(R.id.tv_SD_cneephone);
                holder.tv_dress = (TextView) convertView
                        .findViewById(R.id.tv_SD_cneedress);
                holder.relativeLayout = (RelativeLayout) convertView
                        .findViewById(R.id.rl2_S_D_list);
                convertView.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // 当前点击的CB
                    boolean cu = !isSelected.get(position);
                    // 先将所有的置为FALSE
                    for (Integer p : isSelected.keySet()) {
                        isSelected.put(p, false);
                    }
                    // 再将当前选择CB的实际状态
                    isSelected.put(position, cu);
                    ListAdapter.this.notifyDataSetChanged();
                    beSelectedData.clear();
                    if (cu) beSelectedData.add(cs.get(position));
                }
            });

            holder.tv_name.setText(name.get(position).toString());
            holder.tv_phone.setText(phone.get(position).toString());
            holder.tv_dress.setText(dress.get(position).toString());
            holder.checkBox.setChecked(isSelected.get(position));
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SelectDressActivity.this, "选择了收件人" + position, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent();

                    // Bundle bundle = new Bundle();
                    in.putExtra("name", name.get(position).toString());
                    in.putExtra("phone", phone.get(position).toString());
                    in.putExtra("dress", dress.get(position).toString());
                    in.setClass(SelectDressActivity.this, EditDressActivity.class);
                    startActivity(in);

                }
            });
            return convertView;
        }
    }

    class ViewHolder {

        CheckBox checkBox;

        TextView tv_name;

        TextView tv_phone;

        TextView tv_dress;

        RelativeLayout relativeLayout;

    }

    private class OrderDetails {
        private String code;
        private int userId;

        public OrderDetails(int userId, String code) {
            this.code = code;
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "InsDetails{" +
                    "User_id='" + userId + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

}
