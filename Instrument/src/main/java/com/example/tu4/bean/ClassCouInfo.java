package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by MR.WEN on 2016/11/4.
 */

public class ClassCouInfo implements Serializable {

    private String Type;
    private String Content;
    private String Num;
    private String Date;
    private String Time;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "ClassCouInfo{" +
                "Type='" + Type + '\'' +
                ", Content='" + Content + '\'' +
                ", Num='" + Num + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
