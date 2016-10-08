package com.example.tu4.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tu4.R;
import com.example.tu4.activity.LogisticsTrackingActivity;
import com.example.tu4.adapter.PersonMessageListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalMessageFragment extends Fragment {
 //   ListView listView;
    @BindView(R.id.listview)
    ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personmessage, container, false);
        ButterKnife.bind(this, view);
        initListviewPersonalMenu();
        return view;
    }
    public void initListviewPersonalMenu(){
        PersonMessageListViewAdapter adapter = new PersonMessageListViewAdapter(getContext());
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 //               Toast.makeText(getContext(), "点击了傻子", Toast.LENGTH_SHORT).show();
                Intent intentToLogistics = new Intent(getContext(), LogisticsTrackingActivity.class);
                startActivity(intentToLogistics);
            }
        });

    }

}
