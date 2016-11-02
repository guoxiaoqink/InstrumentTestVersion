package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWorksSelectGridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Map<String,Object>> listData;

    public List<String> indexList = new ArrayList<String>();
    // 定义一个向量作为选中与否容器
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();

    public MyWorksSelectGridviewAdapter(Context context, ArrayList<Map<String,Object>> listData) {
        this.context = context;
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
        for (int i = 0; i < listData.size(); i++) {
            mImage_bs.add(false);
        }
    }

    @Override
    public int getCount() {

        return listData.size();
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_my_works_gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imgMyWorksItem.setImageResource((Integer) listData.get(position).get("img"));
        viewHolder.tvMyWorksTime.setText((String)listData.get(position).get("time"));
        viewHolder.tvMyWorksDate.setText((String)listData.get(position).get("date"));

        if (mImage_bs.elementAt(position))
            viewHolder.imgMyWorksSelectItem.setImageResource(R.mipmap.ic_mywork_check);
        else {
            viewHolder.imgMyWorksSelectItem.setImageResource(R.mipmap.ic_mywork_nocheck);
        }

        return convertView;
    }

    /**
     * 得到选中的item的内容
     * @return
     */
    public ArrayList<Map> getNameList() {
        ArrayList<Map> daleteList = null;
        Map<String, Object> deleteDate = new HashMap<>();
        for (int i = 0; i < indexList.size(); i++) {
            daleteList = new ArrayList<>();
            int temp = Integer.parseInt(indexList.get(i));
            deleteDate.put("picture", listData.get(temp).get("img"));
            deleteDate.put("date",listData.get(temp).get("date"));
            deleteDate.put("time",listData.get(temp).get("time"));
            daleteList.add(deleteDate);
        }
        return daleteList;
    }

    // 修改选中的状态
    public void changeState(int position) {
        if (indexList.contains(position + "")) {
            indexList.remove(position + "");
        } else {
            indexList.add(position + "");
        }
        // 多选时
        mImage_bs.setElementAt(!mImage_bs.elementAt(position), position); // 直接
        notifyDataSetChanged(); // 通知适配器进行更新
    }

    public void fresh() {
        notifyDataSetChanged();
    }

    static class ViewHolder {

        @BindView(R.id.imgMyWorksItem)
        ImageView imgMyWorksItem;

        @BindView(R.id.imgMyWorksSelectItem)
        ImageView imgMyWorksSelectItem;

        @BindView(R.id.tv_my_works_time)
        TextView tvMyWorksTime;
        @BindView(R.id.tv_my_works_date)
        TextView tvMyWorksDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}