package com.example.tu4.activity;

import static com.example.tu4.temporarydata.AplicationStatic.ONE;
import static com.example.tu4.temporarydata.AplicationStatic.THREE;
import static com.example.tu4.temporarydata.AplicationStatic.TWO;
import static com.example.tu4.temporarydata.AplicationStatic.ZERO;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tu4.R;
import com.example.tu4.fragment.InstrumentFragment;
import com.example.tu4.fragment.PersonalMessageFragment;
import com.example.tu4.fragment.SubjectFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    ViewPager viewPager;
    LinearLayout linearLayoutSubject;
    LinearLayout linearLayoutPersonMessage;
    LinearLayout linearLayoutInstrument;

    List<Fragment> dataListFragment = new ArrayList<Fragment>();

    ImageView imageViewSubject;
    TextView textViewSubject;
    ImageView imageViewPersonMessage;
    TextView textViewPersonMessage;
    ImageView imageViewInstrument;
    TextView textViewInstrument;

    List<ImageView> bottomBarImgList = new ArrayList<ImageView>();
    List<TextView> bottomBarTextList = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFindofByidAndData();
        initFragment();
        initViewpager();

        linearLayoutSubject.setOnClickListener(this);
        linearLayoutPersonMessage.setOnClickListener(this);
        linearLayoutInstrument.setOnClickListener(this);
        Toast.makeText(MainActivity.this, "测试成功yeah" +
                "", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "ves图标为什" +
                "", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "秦添加" +
                "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearlayoutSubject:
                viewPager.setCurrentItem(ZERO, false);
                bottomMenuBarChange(ZERO);
                break;
            case R.id.linearlayoutPerson:
                viewPager.setCurrentItem(ONE, false);
                bottomMenuBarChange(ONE);
                break;
            case R.id.linearlayoutInstrument:
                viewPager.setCurrentItem(TWO, false);
                bottomMenuBarChange(TWO);
                break;
        }
    }

    /*
    * 根据传入的数字判断哪个按钮改变样式
    * */
    public void bottomMenuBarChange(int bottomMenuNumber) {
        for (int i = 0; i < bottomBarImgList.size(); i++) {
            if (i == bottomMenuNumber) {
                bottomBarImgList.get(i).setImageResource(R.mipmap.bg_mainactivity_bottommenu);
                bottomBarTextList.get(i).setTextColor(this.getResources().getColor(R.color.s97c8cd));
            } else {
                bottomBarImgList.get(i).setImageResource(R.mipmap.bg_mainactivity_bottommenu_unclick);
                bottomBarTextList.get(i).setTextColor(this.getResources().getColor(R.color.s999999));
            }
        }
    }

    public void initFindofByidAndData() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        linearLayoutSubject = (LinearLayout) findViewById(R.id.linearlayoutSubject);
        linearLayoutPersonMessage = (LinearLayout) findViewById(R.id.linearlayoutPerson);
        linearLayoutInstrument = (LinearLayout) findViewById(R.id.linearlayoutInstrument);

        imageViewSubject = (ImageView) findViewById(R.id.image1);
        imageViewPersonMessage = (ImageView) findViewById(R.id.image2);
        imageViewInstrument = (ImageView) findViewById(R.id.image3);
        textViewSubject = (TextView) findViewById(R.id.text1);
        textViewPersonMessage = (TextView) findViewById(R.id.text2);
        textViewInstrument = (TextView) findViewById(R.id.text3);
        bottomBarImgList.clear();
        bottomBarTextList.clear();

        bottomBarImgList.add(imageViewSubject);
        bottomBarImgList.add(imageViewPersonMessage);
        bottomBarImgList.add(imageViewInstrument);
        bottomBarTextList.add(textViewSubject);
        bottomBarTextList.add(textViewPersonMessage);
        bottomBarTextList.add(textViewInstrument);
        /*
        向fragment那里传数据
        * */
            /*Bundle args = new Bundle();
            args.putString("title", String.valueOf(2));
            myFragment.setArguments(args);*/

    }

    /*
     * 初始化了三个fragment并添加到list中
      * */
    public void initFragment() {

        SubjectFragment subject_myFragment = new SubjectFragment();
        dataListFragment.add(subject_myFragment);

        PersonalMessageFragment myFragment = new PersonalMessageFragment();
        dataListFragment.add(myFragment);

        InstrumentFragment instrument_fragment = new InstrumentFragment();
        dataListFragment.add(instrument_fragment);

    }

    public void initViewpager() {

        FragmentPagerAdapter FragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return dataListFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return dataListFragment.get(arg0);
            }
        };
        // 绑定适配器
        viewPager.setAdapter(FragmentPagerAdapter);
        /*
        * 传入的参数是3，在显示之前，就先缓存好
        * */
        viewPager.setOffscreenPageLimit(THREE);
        // 设置滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case ZERO:
                        bottomMenuBarChange(ZERO);
                        break;
                    case ONE:
                        bottomMenuBarChange(ONE);
                        break;
                    case TWO:
                        bottomMenuBarChange(TWO);
                        break;
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


}




