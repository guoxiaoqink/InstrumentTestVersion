package com.example.tu4.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.tu4.R;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adelais on 2016/9/22.
 */
public class InstrumentListViewInstruDetialAdapter extends BaseAdapter implements
        AdapterView.OnItemClickListener {
    Dialog dialogImage;
    Context mContext;
    ArrayList<Map<String, String>> listDataIns;
    private LayoutInflater mInflater = null;

    public InstrumentListViewInstruDetialAdapter(Context context, ArrayList<Map<String, String>>
            listDataIns) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
        this.listDataIns = listDataIns;
        inintDialog();
    }

    public void inintDialog() {
        dialogImage = new Dialog(mContext);
        dialogImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogImage.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogImage.setContentView(R.layout.instrument_listviewinstrudetial_gridviewbigdrable);
    }


    @Override
    public int getCount() {
        return listDataIns.size();
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

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.instrument_instrudetail_listview_item, null);
            GridView gridView = (GridView) convertView.findViewById(
                    R.id.gridview_listview_instrDetail);
            SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, listDataIns,
                    R.layout.instrument_listviewinstrudetial_gridview_item, new String[]{"image"},
                    new int[]{
                            R.id.imageview});
            viewHolder = new ViewHolder(convertView);
            viewHolder.gridView = gridView;
            viewHolder.adapter = simpleAdapter;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvInsName.setText(listDataIns.get(position).get("name"));
        viewHolder.tvInsContext.setText(listDataIns.get(position).get("des"));
        viewHolder.tvInsPrice.setText(listDataIns.get(position).get("now_price"));
        viewHolder.gridView.setAdapter(viewHolder.adapter);
        viewHolder.gridView.setOnItemClickListener(this);

        return convertView;
    }

    /*
    * 讲gridview中选项的图，换到dialoge中
    * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageView imgGriview = (ImageView) view.findViewById(R.id.imageview);
        ImageView imgDialog = (ImageView) dialogImage.findViewById(R.id.imageview);
        imgDialog.setImageDrawable(imgGriview.getDrawable());
        dialogImage.show();
    }

    static class ViewHolder {
        @BindView(R.id.tv_ins_name)
        TextView tvInsName;
        @BindView(R.id.tv_ins_price)
        TextView tvInsPrice;
        @BindView(R.id.tv_ins_context)
        TextView tvInsContext;
        GridView gridView;
        SimpleAdapter adapter;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


