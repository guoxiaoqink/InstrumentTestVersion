package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/3.
 */

public class IssiuFeedbascPost implements Serializable {
    private String code;
    private int user_id;
    private int class_id;
    private String feedback;
    private int time_id;
    private String date;

    public IssiuFeedbascPost(String code, int user_id, int class_id, String feedback, int
            time_id, String date) {
        this.code = code;
        this.user_id = user_id;
        this.class_id = class_id;
        this.feedback = feedback;
        this.time_id = time_id;
        this.date = date;
    }
}
