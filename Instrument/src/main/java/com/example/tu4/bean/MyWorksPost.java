package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2016/11/1.
 */

public class MyWorksPost implements Serializable {
    private String code;
    private List<MyWorksInfoPost> datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MyWorksInfoPost> getDatas() {
        return datas;
    }

    public void setDatas(List<MyWorksInfoPost> datas) {
        this.datas = datas;
    }

    public MyWorksPost(String code, List<MyWorksInfoPost> datas) {

        this.code = code;
        this.datas = datas;
    }
}
