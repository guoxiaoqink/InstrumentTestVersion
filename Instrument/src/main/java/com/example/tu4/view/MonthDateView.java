package com.example.tu4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class MonthDateView extends View {
    private static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 6;
    private Paint mPaint;
    private int mDayColor = Color.parseColor("#000000");
    private int mSelectDayColor = Color.parseColor("#ffffff");
    private int mSelectBGColor = Color.parseColor("#33000000");
    private int mCurrentColor = Color.parseColor("#1a000000");
    private int mCurrentEndColor = Color.parseColor("#cccccc");
    private int mCurrYear, mCurrMonth, mCurrDay;
    private int mSelYear, mSelMonth, mSelDay;
    private int mColumnSize, mRowSize;
    private DisplayMetrics mDisplayMetrics;
    private int mDaySize = 18;
    private TextView tv_date, tv_week;
    private int weekRow;
    private int[][] daysString;
    private int mCircleRadius = 6;
    private DateClick dateClick;
    private int mCircleColor = Color.parseColor("#97c8cd");
    private List<Integer> daysHasThingList;
    private int downX = 0, downY = 0;

    public MonthDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDisplayMetrics = getResources().getDisplayMetrics();
        Calendar calendar = Calendar.getInstance();
        mPaint = new Paint();
        mCurrYear = calendar.get(Calendar.YEAR);
        mCurrMonth = calendar.get(Calendar.MONTH);
        mCurrDay = calendar.get(Calendar.DATE);
        setSelectYearMonth(mCurrYear, mCurrMonth, mCurrDay);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();
        daysString = new int[6][7];
        mPaint.setTextSize(mDaySize * mDisplayMetrics.scaledDensity);
        String dayString;
        int mMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth);//本月天数
        //上个月天数
        int nMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth - 1);

        int weekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth);

        int nweekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth + 1);

        Log.d("DateView", "DateView:" + mSelMonth + "月1号周" + weekNumber);

        for (int day = 0; day < weekNumber; day++) {

        }
        for (int day = 0; day < mMonthDays; day++) {
            dayString = (day + 1) + "";
            int column = (day + weekNumber - 1) % 7;
            int row = (day + weekNumber - 1) / 7;
            daysString[row][column] = day + 1;
            //daysString[0][0]=25;
            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString)) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            if (dayString.equals(mSelDay + "")) {
                //绘制背景色矩形
                float startRecX = mColumnSize * column;
                float startRecY = mRowSize * row;
                float endRecX = startRecX + mColumnSize;
                float endRecY = startRecY + mRowSize;
                mPaint.setColor(mSelectBGColor);
                canvas.drawRect(startRecX + 35, startRecY, endRecX - 30, endRecY, mPaint);
                //记录第几行，即第几周
                weekRow = row + 1;
            }
            //绘制事务下划线标志
            drawCircle(row, column, day + 1, canvas);
            if (dayString.equals(mSelDay + "")) {
                mPaint.setColor(mSelectDayColor);
            } else if (dayString.equals(mCurrDay + "") && mCurrDay != mSelDay && mCurrMonth == mSelMonth) {
                //正常月，选中其他日期，则今日为
                mPaint.setColor(mCurrentColor);
                float startRecX = mColumnSize * column;
                float startRecY = mRowSize * row;
                float endRecX = startRecX + mColumnSize;
                float endRecY = startRecY + mRowSize;
                canvas.drawRect(startRecX + 35, startRecY, endRecX - 30, endRecY, mPaint);
            } else {
                mPaint.setColor(mDayColor);
            }
            canvas.drawText(dayString, startX, startY, mPaint);
            //添加不是本月的日期 前
            if (row == 0) {
                mPaint.setColor(mCurrentEndColor);
                switch (weekNumber - 1) {
                    case 0:
                        break;
                    case 1:
                        for (int a1 = 0, b1 = 0; a1 >= 0; a1--, b1++) {
                            String daystr1 = (nMonthDays - b1) + " ";
                            int startx1 = (int) (mColumnSize * a1 + (mColumnSize - mPaint.measureText(daystr1)) / 2);
                            int starty1 = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
                            canvas.drawText(daystr1, startx1, starty1, mPaint);
                        }
                        break;
                    case 2:
                        for (int a2 = 1, b2 = 0; a2 >= 0; a2--, b2++) {
                            String daystr2 = (nMonthDays - b2) + " ";
                            int startx2 = (int) (mColumnSize * a2 + (mColumnSize - mPaint.measureText(daystr2)) / 2);
                            int starty2 = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
                            canvas.drawText(daystr2, startx2, starty2, mPaint);
                        }
                        break;
                    case 3:
                        for (int a3 = 2, b3 = 0; a3 >= 0; a3--, b3++) {
                            String daystr3 = (nMonthDays - b3) + " ";
                            int startx3 = (int) (mColumnSize * a3 + (mColumnSize - mPaint.measureText(daystr3)) / 2);
                            int starty3 = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
                            canvas.drawText(daystr3, startx3, starty3, mPaint);
                        }
                        break;
                    case 4:
                        for (int a4 = 3, b4 = 0; a4 >= 0; a4--, b4++) {
                            String daystr4 = (nMonthDays - b4) + " ";
                            int startx4 = (int) (mColumnSize * a4 + (mColumnSize - mPaint.measureText(daystr4)) / 2);
                            int starty4 = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
                            canvas.drawText(daystr4, startx4, starty4, mPaint);
                        }
                        break;
                    case 5:
                        for (int a5 = 4, b5 = 0; a5 >= 0; a5--, b5++) {
                            String daystr5 = (nMonthDays - b5) + " ";
                            int startx5 = (int) (mColumnSize * a5 + (mColumnSize - mPaint.measureText(daystr5)) / 2);
                            int starty5 = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
                            canvas.drawText(daystr5, startx5, starty5, mPaint);
                        }
                        break;
                    case 6:
                        for (int i = 5, j = 0; i >= 0; i--, j++) {
                            String daystr = (nMonthDays - j) + " ";
                            int startx = (int) (mColumnSize * i + (mColumnSize - mPaint.measureText(daystr)) / 2);
                            int starty = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
                            canvas.drawText(daystr, startx, starty, mPaint);
                        }
                        break;
                }
            }

            //添加不是本月日期 后

            int xday = (mMonthDays + weekNumber - 1) % 7;
            int yday = (mMonthDays + weekNumber - 1) / 7;
            int rowend;
            if (xday == 0) {
                break;
            } else {
                rowend = yday;
                int columnEnd = nweekNumber - 1;
                mPaint.setColor(mCurrentEndColor);
                for (int j = 1; columnEnd < 7; columnEnd++, j++) {
                    String daystrend = (j) + " ";
                    int startxend = (int) (mColumnSize * columnEnd + (mColumnSize - mPaint.measureText(daystrend)) / 2);
                    int startyend = (int) (mRowSize * rowend + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
                    canvas.drawText(daystrend, startxend, startyend, mPaint);
                }
            }

            if (tv_date != null) {
                switch (mSelMonth + 1) {
                    case 1:
                        tv_date.setText("January" + " " + mSelYear);
                        break;
                    case 2:
                        tv_date.setText("February " + " " + mSelYear);
                        break;
                    case 3:
                        tv_date.setText("March" + " " + mSelYear);
                        break;
                    case 4:
                        tv_date.setText("April" + " " + mSelYear);
                        break;
                    case 5:
                        tv_date.setText("May" + " " + mSelYear);
                        break;
                    case 6:
                        tv_date.setText("June" + " " + mSelYear);
                        break;
                    case 7:
                        tv_date.setText("July" + " " + mSelYear);
                        break;
                    case 8:
                        tv_date.setText("August" + " " + mSelYear);
                        break;
                    case 9:
                        tv_date.setText("September" + " " + mSelYear);
                        break;
                    case 10:
                        tv_date.setText("October" + " " + mSelYear);
                        break;
                    case 11:
                        tv_date.setText("November" + " " + mSelYear);
                        break;
                    case 12:
                        tv_date.setText("December" + " " + mSelYear);
                        break;

                }
            }

            if (tv_week != null) {
                tv_week.setText("第" + weekRow + "周");
            }
        }
    }

    //绘制事物下划线方法
    private void drawCircle(int row, int column, int day, Canvas canvas) {
        if (daysHasThingList != null && daysHasThingList.size() > 0) {
            if (!daysHasThingList.contains(day)) return;
            Paint nPaint = new Paint();
            nPaint.setColor(mCircleColor);
            nPaint.setStrokeWidth((float) 2);
            mPaint.setColor(mCircleColor);
//			float circleX = (float) (mColumnSize * column +	mColumnSize*0.8);
            float circley = (float) (mRowSize * row + mRowSize * 0.9);
            float circleX = (float) (mColumnSize * column + mColumnSize * 0.3);
            float circleStopX = (float) (mColumnSize * column + mColumnSize * 0.7);
//			float circley = (float) (mRowSize * row);
            canvas.drawLine(circleX, circley, circleStopX, circley, nPaint);
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventCode = event.getAction();
        switch (eventCode) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();
                int upY = (int) event.getY();
                if (Math.abs(upX - downX) < 10 && Math.abs(upY - downY) < 10) {//点击事件
                    performClick();
                    doClickAction((upX + downX) / 2, (upY + downY) / 2);
                }
                break;
        }
        return true;
    }

    /**
     * 初始化列宽行高
     */
    private void initSize() {
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight() / NUM_ROWS;
    }

    /**
     * 设置年月
     *
     * @param year
     * @param month
     */
    private void setSelectYearMonth(int year, int month, int day) {
        mSelYear = year;
        mSelMonth = month;
        mSelDay = day;
    }

    /**
     * 执行点击事件
     *
     * @param x
     * @param y
     */
    private void doClickAction(int x, int y) {
        int row = y / mRowSize;
        int column = x / mColumnSize;
        setSelectYearMonth(mSelYear, mSelMonth, daysString[row][column]);
        invalidate();
        //执行activity发送过来的点击处理事件
        if (dateClick != null) {
            dateClick.onClickOnDate();
        }
    }

    /**
     * 左点击，日历向后翻页
     */
    public void onLeftClick() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if (month == 0) {//若果是1月份，则变成12月份
            year = mSelYear - 1;
            month = 11;
        } else if (DateUtils.getMonthDays(year, month) == day) {
            //如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month - 1;
            day = DateUtils.getMonthDays(year, month);
        } else {
            month = month - 1;
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }

    /**
     * 右点击，日历向前翻页
     */
    public void onRightClick() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if (month == 11) {//若果是12月份，则变成1月份
            year = mSelYear + 1;
            month = 0;
        } else if (DateUtils.getMonthDays(year, month) == day) {
            //如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month + 1;
            day = DateUtils.getMonthDays(year, month);
        } else {
            month = month + 1;
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }

    /**
     * 获取选择的年份
     *
     * @return
     */
    public int getmSelYear() {
        return mSelYear;
    }

    /**
     * 获取选择的月份
     *
     * @return
     */
    public int getmSelMonth() {
        return mSelMonth;
    }

    /**
     * 获取选择的日期
     */
    public int getmSelDay() {
        return this.mSelDay;
    }

    /**
     * 普通日期的字体颜色，默认黑色
     *
     * @param mDayColor
     */
    public void setmDayColor(int mDayColor) {
        this.mDayColor = mDayColor;
    }

    /**
     * 选择日期的颜色，默认为白色
     *
     * @param mSelectDayColor
     */
    public void setmSelectDayColor(int mSelectDayColor) {
        this.mSelectDayColor = mSelectDayColor;
    }

    /**
     * 选中日期的背景颜色，默认蓝色
     *
     * @param mSelectBGColor
     */
    public void setmSelectBGColor(int mSelectBGColor) {
        this.mSelectBGColor = mSelectBGColor;
    }

    /**
     * 当前日期不是选中的颜色，默认红色
     *
     * @param mCurrentColor
     */
    public void setmCurrentColor(int mCurrentColor) {
        this.mCurrentColor = mCurrentColor;
    }

    /**
     * 日期的大小，默认18sp
     *
     * @param mDaySize
     */
    public void setmDaySize(int mDaySize) {
        this.mDaySize = mDaySize;
    }

    /**
     * 设置显示当前日期的控件
     *
     * @param tv_date 显示日期
     * @param tv_week 显示周
     */
    public void setTextView(TextView tv_date, TextView tv_week) {
        this.tv_date = tv_date;
        this.tv_week = tv_week;
        invalidate();
    }

    /**
     * 设置事务天数
     *
     * @param daysHasThingList
     */
    public void setDaysHasThingList(List<Integer> daysHasThingList) {
        this.daysHasThingList = daysHasThingList;
    }

    /***
     * 设置圆圈的半径，默认为6
     *
     * @param mCircleRadius
     */
    public void setmCircleRadius(int mCircleRadius) {
        this.mCircleRadius = mCircleRadius;
    }

    /**
     * 设置圆圈的半径
     *
     * @param mCircleColor
     */
    public void setmCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
    }

    /**
     * 设置日期点击事件
     *
     * @param dateClick
     */
    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }

    /**
     * 跳转至今天
     */
    public void setTodayToView() {
        setSelectYearMonth(mCurrYear, mCurrMonth, mCurrDay);
        invalidate();
    }

    /**
     * 设置日期的点击回调事件
     *
     * @author shiwei.deng
     */
    public interface DateClick {
        public void onClickOnDate();
    }
}
