package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/2.
 */

public class SuggestionsPost implements Serializable{
    private int Type;
    private String Content;
    private String User_id;
    private String code;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public SuggestionsPost(int type, String content, String user_id, String code) {
        Type = type;
        Content = content;
        User_id = user_id;
        this.code = code;
    }
}
