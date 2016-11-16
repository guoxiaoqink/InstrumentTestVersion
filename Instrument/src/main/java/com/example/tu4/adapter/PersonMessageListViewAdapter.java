package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;

/**
 * Created by Adelais on 2016/9/27.
 */

public class PersonMessageListViewAdapter extends BaseAdapter {

    int[] ImgArray = {R.mipmap.date_person_message, R.mipmap.notebook_person_message, R.mipmap.remind_person_message, R.mipmap.photo_person_message,
            R.mipmap.pen_person_message, R.mipmap.message_personmessage, R.mipmap.help,R.mipmap.help};

    String[] stringArray;
    viewHolder viewHolder;
    private LayoutInflater mInflater = null;

    public PersonMessageListViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        stringArray = context.getResources().getStringArray(
                R.array.personmessage_listview_textarray);
        //       ImgArray = context.getResources().getIntArray(R.array.person_listview_img);
    }

    @Override
    public int getCount() {
        return stringArray.length;
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
            convertView = mInflater.inflate(R.layout.personmessage_listview_item, null);
            ImageView image1 = (ImageView) convertView.findViewById(R.id.image_listItem);
            TextView textView = (TextView) convertView.findViewById(R.id.text_listItem);
            ImageView person_point = (ImageView) convertView.findViewById(R.id.iv_person_point);
            viewHolder = new viewHolder();
            viewHolder.ImageView = image1;
            viewHolder.textView = textView;
            viewHolder.redPoint = person_point;
            convertView.setTag(viewHolder);
        }
        viewHolder = (PersonMessageListViewAdapter.viewHolder) convertView.getTag();
        if (position == 2) {
            viewHolder.redPoint.setVisibility(View.VISIBLE);
        }
        if (position == 0) {
            viewHolder.redPoint.setVisibility(View.INVISIBLE);
        }
        viewHolder.ImageView.setImageResource(ImgArray[position]);
        viewHolder.textView.setText(stringArray[position]);
        return convertView;
    }

    class viewHolder {
        ImageView ImageView;
        TextView textView;
        ImageView redPoint;
    }
}

