package com.example.tu4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Adelais on 2016/9/22.
 */
/*
* 解决与外部scollview的冲突
* */
public class ResolveConflictsScoolviewGridview extends GridView {

    public ResolveConflictsScoolviewGridview(Context context) {
        super(context);
    }

    public ResolveConflictsScoolviewGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResolveConflictsScoolviewGridview(Context context, AttributeSet attrs,
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ResolveConflictsScoolviewGridview(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
