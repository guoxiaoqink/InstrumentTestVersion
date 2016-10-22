package com.example.tu4.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.activity.instrument.InstrumentClassifyActivity;
import com.example.tu4.model.AplicationStatic;

import java.util.List;

/**
 * Created by Adelais on 2016/9/22.
 */
public class InstrumentRecyclerAdapter extends
        RecyclerView.Adapter<InstrumentRecyclerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mInstrumentName;
    private Context context;

    public InstrumentRecyclerAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mInstrumentName = datas;
    }

    @Override
    public int getItemCount() {
        return mInstrumentName.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.instrument_recycleview_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mTxt = (TextView) view
                .findViewById(R.id.textView_instrument_recycleItem);

        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.mTxt.setText(mInstrumentName.get(i));
        viewHolder.mTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AplicationStatic.chooseRecycleView = i;
                v.getContext().startActivity(
                        new Intent(v.getContext(), InstrumentClassifyActivity.class));

            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTxt;

        public ViewHolder(View arg0) {
            super(arg0);
        }
    }

}
