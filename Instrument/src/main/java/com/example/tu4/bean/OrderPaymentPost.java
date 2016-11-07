package com.example.tu4.bean;

/**
 * Created by MR.WEN on 2016/11/7.
 */

public class OrderPaymentPost {
    String code;
    public OrderPaymentPost(String code){
        this.code=code;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
