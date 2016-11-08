package com.example.tu4.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 乐器确认订单实体类
 * Created by Administrator on 2016/11/8.
 */

public class EnsureOrderPost implements Serializable {
    private int user_id;//用户的id
    private String code;//api的code
    private Double price;//订单的总价
    private String method;//支付方式
    private String situation;//订单的状态
    private String message;//⽤用户的留言
    private String date;//订单的时间
    private int ordernum;//订单id
    private int receiverAddressID;//编辑收货的ID
    private List<Orderslist> orderslist;

    public EnsureOrderPost(int user_id, String code, Double  price, String method, String
            situation, String message, String date, int ordernum, int receiverAddressID,
                           List<Orderslist> orderslists) {
        this.user_id = user_id;
        this.code = code;
        this.price = price;
        this.method = method;
        this.situation = situation;
        this.message = message;
        this.date = date;
        this.ordernum = ordernum;
        this.receiverAddressID = receiverAddressID;
        this.orderslist = orderslists;
    }

    @Override
    public String toString() {
        return "EnsureOrderPost{" +
                "user_id=" + user_id +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", method='" + method + '\'' +
                ", situation='" + situation + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", ordernum=" + ordernum +
                ", receiverAddressID=" + receiverAddressID +
                ", orderslist=" + orderslist +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public int getReceiverAddressID() {
        return receiverAddressID;
    }

    public void setReceiverAddressID(int receiverAddressID) {
        this.receiverAddressID = receiverAddressID;
    }

    public List<Orderslist> getOrderslist() {
        return orderslist;
    }

    public void setOrderslist(ArrayList<Orderslist> orderslist) {
        this.orderslist = orderslist;
    }

    public static class Orderslist{
        private int ins_id;//乐器的id
        private String attribute;//乐器的属性

        public Orderslist(int ins_id, String attribute) {
            this.ins_id = ins_id;
            this.attribute = attribute;
        }
    }
}
