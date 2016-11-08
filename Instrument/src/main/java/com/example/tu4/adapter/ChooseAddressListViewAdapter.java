package com.example.tu4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.activity.instrument.EditDressActivity;
import com.example.tu4.bean.AddressDetails;

import java.util.List;

/**
 * Created by MR.WEN on 2016/11/7.
 */

public class ChooseAddressListViewAdapter extends BaseAdapter {
    private Context context;
    private List<List<AddressDetails>> data;
    private LayoutInflater inflater;
    private int checkedPosition;

    public ChooseAddressListViewAdapter(Context context, List<List<AddressDetails>> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
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
        final int position1 = position;
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.select_dress_list_item, null);
            holder = new ViewHolder();
            holder.checkBox = (RadioButton) convertView.findViewById(R.id.cb_select_dress);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_SD_cnee);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_SD_cneephone);
            holder.tv_dress = (TextView) convertView.findViewById(R.id.tv_SD_cneedress);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rl2_S_D_list);
            holder.relativeLayout1 = (RelativeLayout) convertView.findViewById(R.id.rl1_S_D_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       final List<AddressDetails> addressDetailses = data.get(position);
        String name = addressDetailses.get(position).getRecipient();
        String tel = addressDetailses.get(position).getTelephone();
        String add = addressDetailses.get(position).getAddress();
        holder.tv_name.setText(name);
        holder.tv_phone.setText(tel);
        holder.tv_dress.setText("地点：" + add);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", addressDetailses.get(position1).getRecipient());
                intent.putExtra("phone", addressDetailses.get(position1).getTelephone());
                intent.putExtra("dress", addressDetailses.get(position1).getAddress());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(context, EditDressActivity.class);
                context.startActivity(intent);
            }
        });
        if (position==checkedPosition){
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }
        return convertView;
    }

    class ViewHolder {
        RadioButton checkBox;
        TextView tv_name;
        TextView tv_phone;
        TextView tv_dress;
        RelativeLayout relativeLayout;
        RelativeLayout relativeLayout1;

    }



    public void setcheck(int position){
        this.checkedPosition = position;
        notifyDataSetChanged();
    }
}
