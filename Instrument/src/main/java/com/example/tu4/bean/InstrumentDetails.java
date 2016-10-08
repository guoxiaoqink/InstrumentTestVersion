package com.example.tu4.bean;

/**
 * Created by Adelais on 2016/9/22.
 */
/*
* 乐器的详细信息
* */
public class InstrumentDetails {
    private int imageId;        //乐器的头像
    private String textTitle;   //乐器名称
    private String textMoney;   //现在的价格
    private String textOleMoney;//乐器以前的价格

    public InstrumentDetails(int imageId, String textTitle, String textMoney, String textOleMoney) {
        this.imageId = imageId;
        this.textTitle = textTitle;
        this.textMoney = textMoney;
        this.textOleMoney = textOleMoney;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextMoney() {
        return textMoney;
    }

    public void setTextMoney(String textMoney) {
        this.textMoney = textMoney;
    }

    public String getTextOleMoney() {
        return textOleMoney;
    }

    public void setTextOleMoney(String textOleMoney) {
        this.textOleMoney = textOleMoney;
    }
}
