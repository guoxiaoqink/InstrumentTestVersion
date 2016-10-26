package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by MR.WEN on 2016/10/26.
 */

public class ClassListDetails implements Serializable {
    private String class_name;
    private String level;
    private String teacher_name;
    private String local;
    private String class_pic_url;
    private String available;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getClass_pic_url() {
        return class_pic_url;
    }

    public void setClass_pic_url(String class_pic_url) {
        this.class_pic_url = class_pic_url;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ClassListDetails{" +
                "class_name='" + class_name + '\'' +
                ", level='" + level + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", local='" + local + '\'' +
                ", class_pic_url='" + class_pic_url + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}

