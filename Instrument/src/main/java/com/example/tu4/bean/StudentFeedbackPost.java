package com.example.tu4.bean;

/**
 * Created by MR.WEN on 2016/11/3.
 */

public class StudentFeedbackPost {
    private int Time_id;
    private String code;
    private int Class_id;

    public StudentFeedbackPost(int Class_id, int Time_id, String code) {
        this.code = code;
        this.Time_id = Time_id;
        this.Class_id = Class_id;
    }

    @Override
    public String toString() {
        return "StudentFeedbackPost{" +
                "Time_id=" + Time_id +
                ", code='" + code + '\'' +
                ", Class_id=" + Class_id +
                '}';
    }

    public int getTime_id() {
        return Time_id;
    }

    public void setTime_id(int time_id) {
        Time_id = time_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getClass_id() {
        return Class_id;
    }

    public void setClass_id(int class_id) {
        Class_id = class_id;
    }
}

