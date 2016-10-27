package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.bean.ClassListDetails;
import com.example.tu4.utils.GetImageByUrl;

import java.util.List;

/**
 * Created by Adelais on 2016/10/3.
 */

public class SubjectListviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    // 要显示的数据的集合
    private List<List<ClassListDetails>> data;
    // 接受上下文
    private Context context;
    private ViewHolder viewHolder;


    public SubjectListviewAdapter(Context context, List<List<ClassListDetails>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.subject_listview_item, null);
            viewHolder.imv_class = (ImageView) convertView.findViewById(R.id.img_subject_image);
            viewHolder.tv_class_name = (TextView) convertView.findViewById(R.id.tv_suject_text1);
            viewHolder.tv_class_leavel = (TextView) convertView.findViewById(R.id.tv_suject_text2);
            viewHolder.tv_class_teacher = (TextView) convertView.findViewById(R.id.tv_suject_text3);
            viewHolder.tv_class_position = (TextView) convertView.findViewById(R.id.tv_suject_text4);
            viewHolder.tv_class_avaliable = (Button) convertView.findViewById(R.id.btn_subject_listviewitem_button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        List<ClassListDetails> list = data.get(position);
        String url = list.get(position).getClass_pic_url().toString();
        String name = list.get(position).getClass_name().toString();
        String leavel = list.get(position).getLevel().toString();
        String teacher = list.get(position).getTeacher_name().toString();
        String position1 = list.get(position).getLocal().toString();
        String avaliable = list.get(position).getAvailable().toString();
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(viewHolder.imv_class, url);
        viewHolder.tv_class_name.setText(name);
        if (avaliable.equals("false")) {
            viewHolder.tv_class_avaliable.setText("未开通");
        } else {
            viewHolder.tv_class_avaliable.setText("已开通");
        }
        viewHolder.tv_class_position.setText(position1);
        viewHolder.tv_class_teacher.setText("老师：" + teacher);
        viewHolder.tv_class_leavel.setText("难度等级：" + leavel);
        return convertView;
    }

    class ViewHolder {
        public ImageView imv_class;
        public TextView tv_class_name;
        public TextView tv_class_leavel;
        public TextView tv_class_teacher;
        public TextView tv_class_position;
        public Button tv_class_avaliable;
    }
}