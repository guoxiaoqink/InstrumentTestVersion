package com.example.tu4.bean;

import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<Orderslist> orderslist;

    public EnsureOrderPost(int user_id, String code, Double price, String method, String
            situation, String message, String date, int ordernum, int receiverAddressID,
                           ArrayList<Orderslist> orderslists) {
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

    public static class Orderslist{
        private int ins_id;//乐器的id
        private String attribute;//乐器的属性

        public Orderslist(int ins_id, String attribute) {
            this.ins_id = ins_id;
            this.attribute = attribute;
        }
    }
}
