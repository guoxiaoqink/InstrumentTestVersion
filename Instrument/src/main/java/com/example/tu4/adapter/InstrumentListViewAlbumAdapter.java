package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tu4.R;
import com.example.tu4.utils.GetImageByUrl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adelais on 2016/9/22.
 */
public class InstrumentListViewAlbumAdapter extends BaseAdapter {

    private ArrayList<String> listData;
    private LayoutInflater mInflater = null;

    public InstrumentListViewAlbumAdapter(Context context, ArrayList<String>
            listData) {
        this.mInflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.instrument_albun_listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        String url = listData.get(position);
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(viewHolder.imageviewInstrumentListview1Item,url);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.imageview_instrument_listview1_item)
        ImageView imageviewInstrumentListview1Item;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


