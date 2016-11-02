package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MyWorks implements Serializable{
    private int page;
    private int maxtime;
    private List<MyWorksInfo> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(int maxtime) {
        this.maxtime = maxtime;
    }

    public List<MyWorksInfo> getList() {
        return list;
    }

    public void setList(List<MyWorksInfo> list) {
        this.list = list;
    }
}
