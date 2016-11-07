package com.example.tu4.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.utils.GetImageByUrl;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adelais on 2016/9/22.
 */
public class InstrumentGridviewAdapter extends BaseAdapter {
    private LayoutInflater minflater;
    private ArrayList<Map<String,String>> listData;

    public InstrumentGridviewAdapter(ArrayList<Map<String,String>> listData, Context context) {
        this.listData = listData;
        this.minflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        if (null != listData) {
            return listData.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.instrument_gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textviewInstrumentname.setText(listData.get(position).get("name"));
        viewHolder.textviewInstrumentMoney.setText(listData.get(position).get("now_price"));
        viewHolder.textviewInstrumentOldmoney.setText(listData.get(position).get("pre_price"));
        viewHolder.textviewInstrumentOldmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        String imgUrl = listData.get(position).get("pic_url");
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(viewHolder.imageviewInstrument, imgUrl);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.imageview_instrument)
        ImageView imageviewInstrument;
        @BindView(R.id.textview_instrumentname)
        TextView textviewInstrumentname;
        @BindView(R.id.textview_instrument_money)
        TextView textviewInstrumentMoney;
        @BindView(R.id.textview_instrument_oldmoney)
        TextView textviewInstrumentOldmoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
