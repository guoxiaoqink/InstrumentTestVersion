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
import com.example.tu4.bean.InstrumentDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adelais on 2016/9/22.
 */
public class InstrumentGridviewAdapter extends BaseAdapter {
    private LayoutInflater minflater;
    private List<InstrumentDetails> mdata;

    public InstrumentGridviewAdapter(List<InstrumentDetails> commo, Context context) {
        super();
        mdata = new ArrayList<InstrumentDetails>();
        minflater = LayoutInflater.from(context);
        mdata = commo;
    }

    @Override
    public int getCount() {
        if (null != mdata) {
            return mdata.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
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
        viewHolder.textviewInstrumentname.setText(mdata.get(position).getTextTitle());
        viewHolder.textviewInstrumentMoney.setText(mdata.get(position).getTextMoney());
        viewHolder.textviewInstrumentOldmoney.setText(mdata.get(position).getTextOleMoney());
        viewHolder.textviewInstrumentOldmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.imageviewInstrument.setImageResource(mdata.get(position).getImageId());
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
