package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by MR.WEN on 2016/11/9.
 */

public class CalendarPost implements Serializable {
    private int User_id;
    private String code;

    public CalendarPost(int user_id, String code) {
        this.User_id = user_id;
        this.code = code;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CalendarPost{" +
                "User_id=" + User_id +
                ", code='" + code + '\'' +
                '}';
    }
}
