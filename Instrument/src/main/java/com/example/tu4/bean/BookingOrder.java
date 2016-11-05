package com.example.tu4.bean;

import java.io.Serializable;

/**
 * 预约订单实体类
 * Created by hs on 2016/11/5.
 */

public class BookingOrder implements Serializable {
    private int page;//用户订单的总数
    private String class_name;//课程名
    private int class_time;//总课时
    private String teacher_name;//老师名
    private String instrument_image;//课程图片
    private String cost;//课程的价格
    private String real_Cost;//实际支付价格
    private int haven_do;//已完成的课时
    private String order_time;//支付时间

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getHaven_do() {
        return haven_do;
    }

    public void setHaven_do(int haven_do) {
        this.haven_do = haven_do;
    }

    public String getReal_Cost() {
        return real_Cost;
    }

    public void setReal_Cost(String real_Cost) {
        this.real_Cost = real_Cost;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInstrument_image() {
        return instrument_image;
    }

    public void setInstrument_image(String instrument_image) {
        this.instrument_image = instrument_image;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public int getClass_time() {
        return class_time;
    }

    public void setClass_time(int class_time) {
        this.class_time = class_time;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
