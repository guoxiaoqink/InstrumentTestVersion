package com.example.tu4.bean;

/**
 * Created by MR.WEN on 2016/10/27.
 */

public class classdetailspost {
    private String Class_id;
    private String code;

    public classdetailspost(String Class_id, String code) {
        this.Class_id = Class_id;
        this.code = code;
    }

    public String getClass_id() {
        return Class_id;
    }

    public void setClass_id(String class_id) {
        Class_id = class_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "classdetailspost{" +
                "Class_id='" + Class_id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
