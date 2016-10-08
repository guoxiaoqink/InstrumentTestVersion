package com.example.tu4.adapter;

import static com.example.tu4.temporarydata.AplicationStatic.LISTVIEW_COUNT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tu4.R;

/**
 * Created by Adelais on 2016/9/22.
 */
public class InstrumentListViewAlbumAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;

    public InstrumentListViewAlbumAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return LISTVIEW_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.instrument_albun_listview_item, null);
        }
        return convertView;
    }

}


