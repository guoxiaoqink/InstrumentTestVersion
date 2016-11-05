package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by MR.WEN on 2016/11/4.
 */

public class FeedbackInfo implements Serializable{
    private String Content;
    private String Date;
    private String Name;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "FeedbackInfo{" +
                "Content='" + Content + '\'' +
                ", Date='" + Date + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
