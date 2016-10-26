package com.example.tu4.bean;

/**
 * Created by MR.WEN on 2016/10/23.
 */

public class SlideView {
    private int id;
    private String code;

    public SlideView(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "SlideView{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
