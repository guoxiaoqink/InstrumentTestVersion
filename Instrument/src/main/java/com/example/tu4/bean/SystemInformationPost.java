package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SystemInformationPost implements Serializable {
    private int user_id;
    private String code;
    private String role;

    public SystemInformationPost(int user_id, String code, String role) {
        this.user_id = user_id;
        this.code = code;
        this.role = role;
    }
}
