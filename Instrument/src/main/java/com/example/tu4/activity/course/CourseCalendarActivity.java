package com.example.tu4.activity.course;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.adapter.CourseCalendarAdapter;
import com.example.tu4.bean.CalendarPost;
import com.example.tu4.bean.CourseCalendarEntity;
import com.example.tu4.utils.EventDecorator;
import com.example.tu4.utils.MySelectorDecorator;
import com.example.tu4.utils.OneDayDecorator;
import com.example.tu4.view.ResolveConflictsScoolviewListview;
import com.example.tu4.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.example.tu4.utils.ApplicationStaticConstants.COURSE_CALENDAR_URL;

public class CourseCalendarActivity extends AppCompatActivity implements OnDateSelectedListener {
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    public static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    @BindView(R.id.course_calendar_title)
    TitleView courseCalendarTitle;
    private CourseCalendarEntity reponse;
    private Boolean isRequst = false;
    String[] deta = new String[3];
    String strs;
    @BindView(R.id.mv_calendar)
    MaterialCalendarView mvCalendar;
    @BindView(R.id.cmlv_course_msg)
    ResolveConflictsScoolviewListview cmlvCourseMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        setContentView(R.layout.activity_course_calendar);
        ButterKnife.bind(this);
        getDataFromUrl();
        initTitle();
        mvCalendar.setOnDateChangedListener(this);
        mvCalendar.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        Calendar instance = Calendar.getInstance();
        mvCalendar.setSelectedDate(instance.getTime());
        mvCalendar.addDecorators(
                new MySelectorDecorator(this),
                //new HighlightWeekendsDecorator(),
                oneDayDecorator
        );
        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
    }
    public void initTitle(){
        Resources res = getResources();
        Drawable ic_return = res.getDrawable(R.mipmap.left_arrow_white);
        Drawable ic_find = res.getDrawable(R.mipmap.lookup);
        courseCalendarTitle.setTitleText("课程日历");
        courseCalendarTitle.getImgLeft().setVisibility(View.VISIBLE);
        courseCalendarTitle.getImgRight2().setVisibility(View.VISIBLE);
        courseCalendarTitle.setImgLeft(ic_return);
        courseCalendarTitle.setImgRight2(ic_find);
        courseCalendarTitle.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseCalendarActivity.this.finish();
            }
        });
        courseCalendarTitle.setImgRight2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseCalendarActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        //If you change a decorate, you need to invalidate decorators
        oneDayDecorator.setDate(date.getDate());
        widget.invalidateDecorators();
        strs = "";
        strs = deta[0] + "年" + deta[1] + "月" + deta[2] + "日";
        Log.e("strs", strs);
        if (getSelectedDatesString().equals(strs)) {
            cmlvCourseMsg.setVisibility(View.VISIBLE);
            CourseCalendarAdapter adapter = new CourseCalendarAdapter(this, reponse);
            cmlvCourseMsg.setAdapter(adapter);
        } else {
            cmlvCourseMsg.setVisibility(View.GONE);
        }
        Log.e("日期", getSelectedDatesString());
    }

    private String getSelectedDatesString() {
        CalendarDay date = mvCalendar.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    /**
     * Simulate an API call to show how to add decorators
     */
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            if (isRequst) {
                calendar.set(Integer.valueOf(deta[0]), Integer.valueOf(deta[1]) - 1, Integer.valueOf(deta[2]));
                isRequst = false;
            }

//            calendar.add(Calendar.MONTH, -2);
            ArrayList<CalendarDay> dates = new ArrayList<>();
            CalendarDay day = CalendarDay.from(calendar);
            dates.add(day);
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }
            mvCalendar.addDecorator(new EventDecorator(Color.rgb(151, 200, 205), calendarDays));
        }
    }

    public void getDataFromUrl() {
        OkHttpUtils
                .postString()
                .url(COURSE_CALENDAR_URL)//
                .content(new Gson().toJson(new CalendarPost(1, "1004")))
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(CourseCalendarActivity.this, "内容获取失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("success", response);
                        System.out.println(response);
                        Gson gson = new Gson();
                        reponse = gson.fromJson(response, new TypeToken<CourseCalendarEntity>() {
                        }.getType());
                        deta = reponse.getDate().split("-");
                        isRequst = true;
                    }
                });
    }
}
