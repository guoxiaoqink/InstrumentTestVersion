package com.example.tu4.bean;

/**
 * Created by 苏春雨 on 2016/10/18.
 */

public class User {
    //第一种布局的字段
    private String username;
    //第二种布局的字段
    private String password;
    //第三种布局的字段
    private String id;

    public User(String username, String password, String id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getItem1_str() {
        return username;
    }

    public void setItem1_str(String item1_str) {
        this.password = item1_str;
    }

    public String getItem2_str() {
        return username;
    }

    public void setItem2_str(String item2_str) {
        this.password = item2_str;
    }

    public String getItem3_str() {
        return password;
    }

    public void setItem3_str(String item3_str) {
        this.id = item3_str;
    }

    @Override
    public String toString() {
        return "User{" +
                "item1_str='" + username + '\'' +
                ", item2_str='" + password + '\'' +
                '}';
    }
}