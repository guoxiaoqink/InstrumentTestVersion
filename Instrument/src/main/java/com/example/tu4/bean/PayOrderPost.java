package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by MR.WEN on 2016/11/7.
 */

public class PayOrderPost implements Serializable{
    private int User_id;
    private String Telephone;
    private String Name;
    private String Date;
    private int Class_id;
    private int Ordernum;
    private String Price;
    private int Method;

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getClass_id() {
        return Class_id;
    }

    public void setClass_id(int class_id) {
        Class_id = class_id;
    }

    public int getOrdernum() {
        return Ordernum;
    }

    public void setOrdernum(int ordernum) {
        Ordernum = ordernum;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String  price) {
        Price = price;
    }

    public int getMethod() {
        return Method;
    }

    public void setMethod(int method) {
        Method = method;
    }

    public PayOrderPost(int User_id,String Telephone,String Name,String Date,int Class_id,int Ordernum,String  Price,int Method) {
        this.User_id=User_id;
        this.Telephone=Telephone;
        this.Name=Name;
        this.Date=Date;
        this.Class_id=Class_id;
        this.Ordernum=Ordernum;
        this.Price=Price;
        this.Method=Method;
    }

    @Override
    public String toString() {
        return "PayOrderPost{" +
                "User_id='" + User_id + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", Name='" + Name + '\'' +
                ", Date='" + Date + '\'' +
                ", Class_id=" + Class_id +
                ", Ordernum=" + Ordernum +
                ", Price=" + Price +
                ", Method=" + Method +
                '}';
    }
}
