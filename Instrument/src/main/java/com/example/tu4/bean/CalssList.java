package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MR.WEN on 2016/10/26.
 */

public class CalssList implements Serializable {
    private int maxiime;
    private List<ClassListDetails> list;

    public int getMaxiime() {
        return maxiime;
    }

    public void setMaxiime(int maxtime) {
        this.maxiime = maxtime;
    }

    public List<ClassListDetails> getList() {
        return list;
    }

    public void setList(List<ClassListDetails> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "CalssList{" +
                "maxiime=" + maxiime +
                ", list=" + list +
                '}';
    }
}
