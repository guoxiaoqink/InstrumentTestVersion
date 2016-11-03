package com.example.tu4.bean;

/**
 * Created by MR.WEN on 2016/11/3.
 */

public class StudentFeedbackPost {
    private int Time;
    private String code;

    public StudentFeedbackPost(int Time, String code) {
        this.code = code;
        this.Time = Time;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StudentFeedbackPost{" +
                "Time=" + Time +
                ", code='" + code + '\'' +
                '}';
    }
}
