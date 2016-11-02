package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MyWorksInfo implements Serializable{
    private String work_icon;
    private String work_post_time;
    private String work_content_time;
    private String work_url;

    public String getWork_icon() {
        return work_icon;
    }

    public void setWork_icon(String work_icon) {
        this.work_icon = work_icon;
    }

    public String getWork_post_time() {
        return work_post_time;
    }

    public void setWork_post_time(String work_post_time) {
        this.work_post_time = work_post_time;
    }

    public String getWork_content_time() {
        return work_content_time;
    }

    public void setWork_content_time(String work_content_time) {
        this.work_content_time = work_content_time;
    }

    public String getWork_url() {
        return work_url;
    }

    public void setWork_url(String work_url) {
        this.work_url = work_url;
    }
}
