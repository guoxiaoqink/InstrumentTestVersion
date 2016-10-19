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
import android.widget.RelativeLayout;

import com.example.tu4.R;
import com.example.tu4.activity.ConnectUsActivity;
import com.example.tu4.activity.PersonalDataActivity;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.adapter.PersonMessageListViewAdapter;
import com.example.tu4.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalMessageFragment extends Fragment {
    //   ListView listView;
    @BindView(R.id.rela_person_data)
    RelativeLayout relaPersonData;
    @BindView(R.id.img_find_personmessage)
    ImageView imgFindPersonmessage;
    @BindView(R.id.person_image)
    CircleImageView personImage;
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

    public void initListviewPersonalMenu() {
        PersonMessageListViewAdapter adapter = new PersonMessageListViewAdapter(getContext());
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //               Toast.makeText(getContext(), "点击了傻子", Toast.LENGTH_SHORT).show();
                if (position == 5) {
                    Intent intentToconnectUs = new Intent(getContext(),
                            ConnectUsActivity.class);
                    startActivity(intentToconnectUs);
                }
              /*  Intent intentToLogistics = new Intent(getContext(),
                        LogisticsTrackingActivity.class);
                startActivity(intentToLogistics);*/
            }
        });

    }

    @OnClick({R.id.img_find_personmessage, R.id.rela_person_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_find_personmessage:
                Intent intentToSearch = new Intent(getActivity(), SearchActivity.class);
                startActivity(intentToSearch);
                break;
            case R.id.rela_person_data:
                Intent intentToPersonData = new Intent(getActivity(), PersonalDataActivity.class);
                startActivity(intentToPersonData);
                break;
        }
    }

   /* @OnClick(R.id.rela_person_data)
    public void onClick() {
        Intent intentToPersonData = new Intent(getActivity(), PersonalDataActivity.class);
        startActivity(intentToPersonData);
    }*/

  /*  @OnClick(R.id.img_find_personmessage)
    public void onClick() {
    }*/
}
