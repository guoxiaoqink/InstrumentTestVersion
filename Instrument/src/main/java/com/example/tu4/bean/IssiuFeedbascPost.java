package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/3.
 */

public class IssiuFeedbascPost implements Serializable {
    private String code;
    private int user_id;
    private int class_id;
    private String feedback;
    private int time_id;
    private String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        user_id = user_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getTime_id() {
        return time_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public IssiuFeedbascPost(String code, String date, int time_id, String feedback, int class_id, int user_id) {
        this.code = code;
        this.date = date;
        this.time_id = time_id;
        this.feedback = feedback;
        this.class_id = class_id;
        this.user_id = user_id;
    }
}
