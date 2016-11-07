package com.example.tu4.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/7.
 */

public class AllInstrumentGet implements Serializable {
    private ArrayList<String> typeList;
    private ArrayList<InsArr> insArr;

    public ArrayList<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(ArrayList<String> typeList) {
        this.typeList = typeList;
    }

    public ArrayList<InsArr> getInsArr() {
        return insArr;
    }

    public void setInsArr(ArrayList<InsArr> insArr) {
        this.insArr = insArr;
    }

    public static class InsArr{
        private String name;//乐器的名字
        private int pre_price;//原价
        private int now_price;//现价
        private String type;//分类
        private String pic_url;//乐器图片
        private int id;//
        private String des;//乐器描述

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getNow_price() {
            return now_price;
        }

        public void setNow_price(int now_price) {
            this.now_price = now_price;
        }

        public int getPre_price() {
            return pre_price;
        }

        public void setPre_price(int pre_price) {
            this.pre_price = pre_price;
        }
    }
}
