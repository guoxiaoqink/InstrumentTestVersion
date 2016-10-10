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

import com.example.tu4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.tu4.model.AplicationStatic.instrumentImg;

/**
 * Created by Adelais on 2016/9/22.
 */
public class InstrumentListViewInstruDetialAdapter extends BaseAdapter implements
        AdapterView.OnItemClickListener {
    Dialog dialogImage;
    Context mContext;
    InstrumentClassifyActivityGridviewAdapter mAdapter;
    List<HashMap<String, Object>> InstrumenImg = new ArrayList<HashMap<String, Object>>();
    View hehe;
    ImageView tr;
    private viewHolder viewHolder;
    private LayoutInflater mInflater = null;
    private LayoutInflater inflater = null;

    public InstrumentListViewInstruDetialAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
        InstrumenImg = instrumentImg();
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
        return InstrumenImg.size();
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
            convertView = mInflater.inflate(R.layout.instrument_instrudetail_listview_item, null);
            GridView gridView = (GridView) convertView.findViewById(
                    R.id.gridview_listview_instrDetail);
            SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, InstrumenImg,
                    R.layout.instrument_listviewinstrudetial_gridview_item, new String[]{"image"},
                    new int[]{
                            R.id.imageview});
            viewHolder = new viewHolder();
            viewHolder.gridView = gridView;
            viewHolder.adapter = simpleAdapter;
            convertView.setTag(viewHolder);
        }
        viewHolder = (InstrumentListViewInstruDetialAdapter.viewHolder) convertView.getTag();
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

    class viewHolder {
        GridView gridView;
        SimpleAdapter adapter;
    }


}


