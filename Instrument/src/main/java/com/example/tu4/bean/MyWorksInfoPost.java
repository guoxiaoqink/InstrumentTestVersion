package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MyWorksInfoPost implements Serializable{
    private String User_id;
    private String role;
    private int maxtime;

    public MyWorksInfoPost(String user_id, String role, int maxtime) {
        User_id = user_id;
        this.role = role;
        this.maxtime = maxtime;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(int maxtime) {
        this.maxtime = maxtime;
    }
}
