package com.example.tu4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tu4.R;
import com.example.tu4.bean.User;

import java.util.List;

/**
 * Created by 苏春雨 on 2016/10/18.
 */

public class BookingOrderAdapter extends BaseAdapter {

    //定义常用的参数
    private Context ctx;
    private int resourceId;
    private List<User> users;
    private LayoutInflater inflater;
    //为三种布局定义一个标识
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private final int TYPE3 = 2;

    public BookingOrderAdapter(Context context, List<User> objects) {
        this.ctx = context;
        this.users = objects;
        //别忘了初始化inflater
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法必须重写，它返回了有几种不同的布局
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    // 每个convertView都会调用此方法，获得当前应该加载的布局样式
    @Override
    public int getItemViewType(int position) {
        //获取当前布局的数据
        User u = users.get(position);
        //哪个字段不为空就说明这是哪个布局
        //比如第一个布局只有item1_str这个字段，那么就判断这个字段是不是为空，
        //如果不为空就表明这是第一个布局的数据
        //根据字段是不是为空，判断当前应该加载的布局
        Log.i("LHD", u.toString());
        Log.i("LHD", "第一个返回值" + u.getItem1_str());
        Log.i("LHD", "第二个返回值" + u.getItem2_str());
        Log.i("LHD", "第三个返回值" + u.getItem3_str());
        if (u.getItem1_str() != null) {
            return TYPE1;
        } else if (u.getItem2_str() != null) {
            return TYPE2;
        } else {//如果前两个字段都为空，那就一定是加载第三个布局啦。
            return TYPE3;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化每个holder
        ViewHolder1 holder1 = null;
     //   ViewHolder2 holder2 = null;
      //  ViewHolder3 holder3 = null;

        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case TYPE1:
                    convertView = inflater.inflate(R.layout.booking_order_item, null, false);
                    holder1 = new ViewHolder1();
                    holder1.item1_tv = (TextView) convertView.findViewById(R.id.lo_tv_order_time);
                    holder1.item1_im = (ImageView) convertView.findViewById(R.id.lo_image);
                    holder1.item1_tv = (TextView) convertView.findViewById(R.id.lo_tv_isclass);
                    holder1.item1_tv = (TextView) convertView.findViewById(R.id.lo_text1);


                    convertView.setTag(holder1);
                    break;
               /// case TYPE2:
                    //convertView = inflater.inflate(R.layout.itemlayout1, null, false);
                   // holder2 = new ViewHolder2();
                   // holder2.item2_tv = (TextView) convertView.findViewById(R.id.tv_instrument_name_1);
                  //  convertView.setTag(holder2);
                   // break;
               // case TYPE3:
                  //  convertView = inflater.inflate(R.layout.itemlayout1, null, false);
                 //   holder3 = new ViewHolder3();
                    //holder2.item2_tv = (TextView) convertView.findViewById(R.id.tv_instrument_name_1);
                 //   convertView.setTag(holder3);
                 //   break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE1:
                    holder1 = (ViewHolder1) convertView.getTag();

                    break;
          //      case TYPE2:
          //          holder2 = (ViewHolder2) convertView.getTag();
           //         break;
          //      case TYPE3:
         //           holder3 = (ViewHolder3) convertView.getTag();
         //           break;
            }
        }
        //为布局设置数据
        switch (type) {
            case TYPE1:
                holder1.item1_tv.setText(users.get(position).getItem1_str());
                break;
         //   case TYPE2:
            //    holder2.item2_tv.setText(users.get(position).getItem2_str());
         //       break;
        //    case TYPE3:
           //     holder3.item3_btn.setText(users.get(position).getItem3_str());
          //      break;
        }

        return convertView;
    }

    //为每种布局定义自己的ViewHolder
    public class ViewHolder1 {
        TextView item1_tv;
        ImageView item1_im;
        TextView item1_tv2;
    }

  //  public class ViewHolder2 {
        TextView item2_tv;
  //  }

  //  public class ViewHolder3 {
  //      Button item3_btn;
  //  }

}