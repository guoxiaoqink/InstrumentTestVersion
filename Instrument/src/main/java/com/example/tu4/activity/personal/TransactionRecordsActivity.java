package com.example.tu4.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tu4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 秦孟飞 on 2016/10/20
 * Descripyion: 查看订单,交易记录
 * Version: 1
 * Modify Person : Moofei
 */
public class TransactionRecordsActivity extends AppCompatActivity {

    @BindView(R.id.iv_TR_left)
    ImageView ivTRLeft;
    private ListView mListView;
    private DataBaseForParent dataBaseForParent;
    private LayoutInflater inflater;
    private String[] time = new String[]{"订单时间 2016-05-05 14:00", "订单时间 2016-05-05 14:00"};
    private String[] mode = new String[]{"已付款", "已完成"};
    private String[] details = new String[]{"共1件商品（含运费0.0）实付¥5000.00", "共2件商品（含运费0.0）实付¥6000.00"};
    private String[] name = new String[]{"乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX", "乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX乐器XXX"};
    private String[] attribute = new String[]{"属性XXX", "属性XXX"};
    private String[] piece = new String[]{"¥5000.00", "¥1000.00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_records);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.lv_TR_parent);
        dataBaseForParent = new DataBaseForParent();
        mListView.setAdapter(dataBaseForParent);
    }

    @OnClick(R.id.iv_TR_left)
    public void onClick() {
        this.finish();
    }

    private class DataBaseForParent extends BaseAdapter {

        public DataBaseForParent() {
            super();
            inflater = LayoutInflater.from(TransactionRecordsActivity.this);
        }

        @Override
        public int getCount() {
            return time.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.transaction_parent_list_item, parent, false);
                ViewHodlerForParent viewHolder = new ViewHodlerForParent();

                viewHolder.time = (TextView) view
                        .findViewById(R.id.tv_TR_time);
                viewHolder.mode = (TextView) view
                        .findViewById(R.id.tv_TR_mode);
                viewHolder.child = (ListView) view
                        .findViewById(R.id.lv_TR_child);
                viewHolder.details = (TextView) view
                        .findViewById(R.id.tv_TR_information);
                view.setTag(viewHolder);
            }
            final ViewHodlerForParent viewHolder = (ViewHodlerForParent) view.getTag();
            viewHolder.time.setText(time[position]);
            viewHolder.mode.setText(mode[position]);
            viewHolder.details.setText(details[position]);
            viewHolder.child.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return name.length;
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = inflater.inflate(R.layout.transaction_child_list_item, parent, false);
                        ViewHodlerForChild viewHodlerForChild = new ViewHodlerForChild();
                        viewHodlerForChild.imageView = (ImageView) convertView.findViewById(R.id.iv_TD_child);
                        viewHodlerForChild.name = (TextView) convertView.findViewById(R.id.tv_TR_C_name);
                        viewHodlerForChild.attribute = (TextView) convertView.findViewById(R.id.tv_TR_C_attribute);
                        viewHodlerForChild.piece = (TextView) convertView.findViewById(R.id.tv_TR_C_piece);
                        convertView.setTag(viewHodlerForChild);
                    }
                    final ViewHodlerForChild viewHodlerForChild = (ViewHodlerForChild) convertView.getTag();
                    viewHodlerForChild.name.setText(name[position]);
                    viewHodlerForChild.attribute.setText(attribute[position]);
                    viewHodlerForChild.piece.setText(piece[position]);
                    return convertView;
                }
            });
            return view;
        }
    }

    private class ViewHodlerForParent {
        private TextView time;
        private TextView mode;
        private ListView child;
        private TextView details;
    }

    private class ViewHodlerForChild {
        private ImageView imageView;
        private TextView name;
        private TextView attribute;
        private TextView piece;
    }
}
