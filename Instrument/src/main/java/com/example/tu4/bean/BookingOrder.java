package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 预约订单实体类
 * Created by hs on 2016/11/5.
 */

public class BookingOrder implements Serializable {
    private List<BookingOrderinfo> list;

    public List<BookingOrderinfo> getList() {
        return list;
    }

    public void setList(List<BookingOrderinfo> list) {
        this.list = list;
    }

    public static class BookingOrderinfo{

        private int situation;//上课状态
        private String class_name;//课程名
        private int class_time;//总课时
        private String teacher_name;//老师名
        private int class_price;//课程的价格
        private String class_pic_url;//课程图片下载地址
        private String date;//下单时间

        public int getSituation() {
            return situation;
        }

        public void setSituation(int situation) {
            this.situation = situation;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public int getClass_time() {
            return class_time;
        }

        public void setClass_time(int class_time) {
            this.class_time = class_time;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public int getClass_price() {
            return class_price;
        }

        public void setClass_price(int class_price) {
            this.class_price = class_price;
        }

        public String getClass_pic_url() {
            return class_pic_url;
        }

        public void setClass_pic_url(String class_pic_url) {
            this.class_pic_url = class_pic_url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

}



