package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/10.
 */
public class BookingOrderPost implements Serializable {
    private int user_id;
    private String code;

    public BookingOrderPost(int user_id, String code) {
        this.user_id = user_id;
        this.code = code;
    }
}