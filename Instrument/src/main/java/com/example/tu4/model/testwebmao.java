package com.example.tu4.model;

/**
 * Created by Adelais on 2016/10/8.
 */
public class testwebmao {
    int status_code;
    String message;

    public testwebmao(int status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "testwebmao{" +
                "status_code=" + status_code +
                ", message='" + message + '\'' +
                '}';
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
