package com.example.tu4.bean;

/**
 * Created by MR.WEN on 2016/10/26.
 */

public class Classshowpost {
    private int id;
    private String role;
    private String code;
    private int maxtime;

    public Classshowpost(int id, String role, String code, int maxtime) {
        this.id = id;
        this.role = role;
        this.code = code;
        this.maxtime = maxtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(int maxtime) {
        this.maxtime = maxtime;
    }

    @Override
    public String toString() {
        return "Classshowpost{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", code='" + code + '\'' +
                ", maxtime='" + maxtime + '\'' +
                '}';
    }
}
