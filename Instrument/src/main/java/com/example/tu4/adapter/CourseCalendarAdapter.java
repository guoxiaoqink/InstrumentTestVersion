package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.bean.CourseCalendarEntity;

/**
 * Created by MR.WEN on 2016/11/9.
 */

public class CourseCalendarAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflate;
    private Context context;
    private CourseCalendarEntity calendarEntity;

    public CourseCalendarAdapter(Context context, CourseCalendarEntity calendarEntity) {
        this.mLayoutInflate = LayoutInflater.from(context);
        this.context = context;
        this.calendarEntity = calendarEntity;
    }
    @Override
    public int getCount() {
        return 1;
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
        ViewHolder_cc viewHolderCc;
        if (convertView == null){
            convertView = mLayoutInflate.inflate(R.layout.item_course_calendar,null);
            viewHolderCc = new ViewHolder_cc();
            viewHolderCc.coursetimeTv = (TextView)convertView.findViewById(R.id.tv_course_calendar_time);
            viewHolderCc.coursenameTv = (TextView)convertView.findViewById(R.id.tv_cc_name);
            viewHolderCc.coursenumTv = (TextView)convertView.findViewById(R.id.tv_cc_course_num);
            viewHolderCc.courseaddTv = (TextView)convertView.findViewById(R.id.tv_cc_add);
            convertView.setTag(viewHolderCc);
        }else {
            viewHolderCc = (ViewHolder_cc)convertView.getTag();
        }
        viewHolderCc.coursetimeTv.setText(calendarEntity.getTime());
        viewHolderCc.coursenameTv.setText(calendarEntity.getName());
        viewHolderCc.coursenumTv.setText("(课时"+calendarEntity.getNum()+")");
        viewHolderCc.courseaddTv.setText(calendarEntity.getLocation());

        return convertView;
    }
    final class ViewHolder_cc{
        TextView coursetimeTv;
        TextView coursenameTv;
        TextView coursenumTv;
        TextView courseaddTv;
    }
}
