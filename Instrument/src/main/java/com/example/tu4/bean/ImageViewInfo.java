package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by MR.WEN on 2016/10/25.
 */

public class ImageViewInfo implements Serializable {
    private String top_image;
    private String class_name;

    public String getTop_image() {
        return top_image;
    }

    public void setTop_image(String top_image) {
        this.top_image = top_image;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return "ImageViewInfo{" +
                "top_image='" + top_image + '\'' +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
