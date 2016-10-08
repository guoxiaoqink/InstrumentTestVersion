package com.example.tu4.view;

/**
 * Created by Adelais on 2016/9/22.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ResolveConflictsScoolviewListview extends ListView {

    public ResolveConflictsScoolviewListview(Context context) {
        super(context);
    }

    public ResolveConflictsScoolviewListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResolveConflictsScoolviewListview(Context context, AttributeSet attrs,
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
   /* private float mCurX,mCurY,mDownX,mDownY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mCurX = ev.getX();
        mCurY = ev.getY();

        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            //记录按下时候的坐标
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
            mDownX = ev.getX();
            mDownY = ev.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(ev.getAction() == MotionEvent.ACTION_UP){
            //在up时判断是否按下和松手的坐标为一个点
            //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
            if(mDownX==mCurX && mDownY==mCurY){
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }*/

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
