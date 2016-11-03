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
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.activity.SearchActivity;
import com.example.tu4.activity.personal.BookingOrderActivity;
import com.example.tu4.activity.personal.ComplaintsSuggestionsActivity;
import com.example.tu4.activity.personal.ConnectUsActivity;
import com.example.tu4.activity.personal.HelpCenterActivity;
import com.example.tu4.activity.personal.MyLeaveWordsActivity;
import com.example.tu4.activity.personal.MyWorksActivity;
import com.example.tu4.activity.personal.PersonalDataActivity;
import com.example.tu4.activity.personal.TransactionRecordsActivity;
import com.example.tu4.adapter.PersonMessageListViewAdapter;
import com.example.tu4.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tu4.utils.ApplicationStaticConstants.UserName;
import static com.example.tu4.utils.ApplicationStaticConstants.UserTel;

/**
 * Created by gxq
 * Descripyion: ”我“界面
 * Version：1
 * Modify Person：gxq
 */
public class PersonalMessageFragment extends Fragment {
    //   ListView listView;
    @BindView(R.id.rela_person_data)
    RelativeLayout relaPersonData;
    @BindView(R.id.img_find_personmessage)
    ImageView imgFindPersonmessage;

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.civ_person_image)
    CircleImageView civPersonImage;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_telnumber)
    TextView tvTelnumber;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personmessage, container, false);
        ButterKnife.bind(this, view);
        initListviewPersonalMenu();
        initViewData();
        return view;
    }

    private void initViewData() {
        //   civPersonImage.setImageResource();头像还没有，没有传过来的值
        tvUsername.setText(UserName);
        tvTelnumber.setText(UserTel);
    }

    public void initListviewPersonalMenu() {
        PersonMessageListViewAdapter adapter = new PersonMessageListViewAdapter(getContext());
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //               Toast.makeText(getContext(), "点击了傻子", Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Intent intentHelpCenter = new Intent(getContext(),
                            BookingOrderActivity.class);
                    startActivity(intentHelpCenter);
                }
                if (position == 1) {
                    Intent intentMyLeaveWords = new Intent(getContext(),
                            TransactionRecordsActivity.class);
                    startActivity(intentMyLeaveWords);
                }
                if (position == 2) {
                    Intent intentMyLeaveWords = new Intent(getContext(),
                            MyLeaveWordsActivity.class);
                    startActivity(intentMyLeaveWords);
                }
                if (position == 3) {
                    Intent intentMyWorks = new Intent(getContext(),
                            MyWorksActivity.class);
                    startActivity(intentMyWorks);
                }
                if (position == 4) {
                    Intent intentComplaintsSuggestion = new Intent(getContext(),
                            ComplaintsSuggestionsActivity.class);
                    startActivity(intentComplaintsSuggestion);
                }
                if (position == 5) {
                    Intent intentToconnectUs = new Intent(getContext(),
                            ConnectUsActivity.class);
                    startActivity(intentToconnectUs);
                }
                if (position == 6) {
                    Intent intentHelpCenter = new Intent(getContext(),
                            HelpCenterActivity.class);
                    startActivity(intentHelpCenter);
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

}
