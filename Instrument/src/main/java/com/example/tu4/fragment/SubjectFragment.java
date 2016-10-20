package com.example.tu4.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.activity.DateActivity;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.activity.SubjectDetailActivity;
import com.example.tu4.activity.TransactionRecordsActivity;
import com.example.tu4.adapter.SubjectListviewAdapter;
import com.example.tu4.view.ImageCyclePlayView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tu4.model.AplicationStatic.cycleImageData;


public class SubjectFragment extends Fragment {
    @BindView(R.id.icpv_subject)
    ImageCyclePlayView icpvSubject;
    @BindView(R.id.listview_subject)
    ListView listviewSubject;
    @BindView(R.id.img_subject_date)
    ImageView imgSubjectDate;
    @BindView(R.id.img_subject_titlt_find)
    ImageView imgSubjectTitltFind;
    @BindView(R.id.iv_subject_titlt_wait)
    ImageView ivSubjectTitltWait;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        ButterKnife.bind(this, view);

        initImageCyclePlayView();
        initListviewSubjectDetail();
        return view;
    }

    public void initListviewSubjectDetail() {
        SubjectListviewAdapter adapter = new SubjectListviewAdapter(getContext());
        listviewSubject.setAdapter(adapter);
        listviewSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), SubjectDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initImageCyclePlayView() {
        icpvSubject.loadData(cycleImageData(), new ImageCyclePlayView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCyclePlayView.ImageInfo imageInfo) {
                //本地图片
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
                return imageView;
            }
        });
    }

    @OnClick({R.id.img_subject_date, R.id.img_subject_titlt_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_subject_date:
                Intent intent = new Intent();
                intent.setClass(getContext(), DateActivity.class);
                startActivity(intent);
                break;
            case R.id.img_subject_titlt_find:
                Intent intentToSearch = new Intent(getActivity(), SearchActivity.class);
                startActivity(intentToSearch);
                break;
        }
    }

    @OnClick(R.id.iv_subject_titlt_wait)
    public void onClick() {
        Intent intentTo = new Intent(getActivity(), TransactionRecordsActivity.class);
        startActivity(intentTo);
    }

}

