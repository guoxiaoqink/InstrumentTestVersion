package com.example.tu4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tu4.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWorksSelectGridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Integer> myWorksPisture;

    public List<String> indexList = new ArrayList<String>();
    // 定义一个向量作为选中与否容器
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();

    public MyWorksSelectGridviewAdapter(Context context, ArrayList<Integer> myWorksPisture) {
        this.context = context;
        this.myWorksPisture = myWorksPisture;
        this.layoutInflater = LayoutInflater.from(context);
        for (int i = 0; i < myWorksPisture.size(); i++) {
            mImage_bs.add(false);
        }
    }

    @Override
    public int getCount() {

        return myWorksPisture.size();
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
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.imgMyWorksItem.setImageResource(myWorksPisture.get(position));

        if (mImage_bs.elementAt(position))
            viewHolder.imgMyWorksSelectItem.setImageResource(R.mipmap.ic_mywork_check);
        else {
            viewHolder.imgMyWorksSelectItem.setImageResource(R.mipmap.ic_mywork_nocheck);
        }

        return convertView;
    }

    public List<Integer> getNameList() {
        List<Integer> tempList = new ArrayList<Integer>();
        for (int i = 0; i < indexList.size(); i++) {
            int temp = Integer.parseInt(indexList.get(i));
            tempList.add(myWorksPisture.get(temp));
        }
        return tempList;
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
    public void fresh(){
        notifyDataSetChanged();
    }

    static class ViewHolder {

        @BindView(R.id.imgMyWorksItem)
        ImageView imgMyWorksItem;

        @BindView(R.id.imgMyWorksSelectItem)
        ImageView imgMyWorksSelectItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}