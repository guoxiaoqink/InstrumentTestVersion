package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/7.
 */

public class AllInstrumentPost implements Serializable {
    private String code;
    private String role;
    private int id;

    public AllInstrumentPost(String code, int id, String role) {
        this.code = code;
        this.id = id;
        this.role = role;
    }
}
