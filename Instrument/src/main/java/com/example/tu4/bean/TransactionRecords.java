package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 交易记录
 * Created by Administrator on 2016/11/8.
 */
public class TransactionRecords implements Serializable {
    private List<OrderList> order_list;

    public List<OrderList> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderList> order_list) {
        this.order_list = order_list;
    }

    public static class OrderList {
        private String date;
        private Double price;
        private List<TransList> list;
        private String situation;
        private int freigh;//运费

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public List<TransList> getList() {
            return list;
        }

        public void setList(List<TransList> list) {
            this.list = list;
        }

        public String getSituation() {
            return situation;
        }

        public void setSituation(String situation) {
            this.situation = situation;
        }

        public int getFreigh() {
            return freigh;
        }

        public void setFreigh(int freigh) {
            this.freigh = freigh;
        }
    }
    public static class TransList{
        private String pic_url;
        private int now_price;
        private String name;
        private String type;

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getNow_price() {
            return now_price;
        }

        public void setNow_price(int now_price) {
            this.now_price = now_price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
