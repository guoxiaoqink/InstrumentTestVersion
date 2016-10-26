package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MR.WEN on 2016/10/25.
 */

public class ImageCircleView implements Serializable {
    private List<ImageViewInfo> top;

    public List<ImageViewInfo> getTop() {
        return top;
    }

    public void setTop(List<ImageViewInfo> top) {
        this.top = top;
    }


    @Override
    public String toString() {
        return "ImageCircleView{" +
                "top=" + top +
                '}';
    }
}
