package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MR.WEN on 2016/11/1.
 */

public class SubjectDetails implements Serializable{
    private String Class_time;
    private int Class_id;
    private int Feedback_number;
    private String Class_name;
    private int Class_price;
    private String Class_remark;
    private String Class_level;
    private int Class_number;
    private String Class_location;
    private List<SubjectInfo> Teacher;


    public String getClass_time() {
        return Class_time;
    }

    public void setClass_time(String class_time) {
        Class_time = class_time;
    }

    public int getClass_id() {
        return Class_id;
    }

    public void setClass_id(int class_id) {
        Class_id = class_id;
    }

    public int getFeedback_number() {
        return Feedback_number;
    }

    public void setFeedback_number(int feedback_number) {
        Feedback_number = feedback_number;
    }

    public String getClass_name() {
        return Class_name;
    }

    public void setClass_name(String class_name) {
        Class_name = class_name;
    }

    public int getClass_price() {
        return Class_price;
    }

    public void setClass_price(int class_price) {
        Class_price = class_price;
    }

    public String getClass_remark() {
        return Class_remark;
    }

    public void setClass_remark(String class_remark) {
        Class_remark = class_remark;
    }

    public String getClass_level() {
        return Class_level;
    }

    public void setClass_level(String class_level) {
        Class_level = class_level;
    }

    public int getClass_number() {
        return Class_number;
    }

    public void setClass_number(int class_number) {
        Class_number = class_number;
    }

    public String getClass_location() {
        return Class_location;
    }

    public void setClass_location(String class_location) {
        Class_location = class_location;
    }

    public List<SubjectInfo> getTeacher() {
        return Teacher;
    }

    public void setTeacher(List<SubjectInfo> teacher) {
        Teacher = teacher;
    }

    @Override
    public String toString() {
        return "SubjectDetails{" +
                "Class_time='" + Class_time + '\'' +
                ", Class_id=" + Class_id +
                ", Feedback_number=" + Feedback_number +
                ", Class_name='" + Class_name + '\'' +
                ", Class_price=" + Class_price +
                ", Class_remark='" + Class_remark + '\'' +
                ", Class_level='" + Class_level + '\'' +
                ", Class_number=" + Class_number +
                ", Class_location='" + Class_location + '\'' +
                ", Teacher=" + Teacher +
                '}';
    }
}
