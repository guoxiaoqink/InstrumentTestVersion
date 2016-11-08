package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SystemInformation implements Serializable{
    private List<SysInfor> list;

    public SystemInformation(List<SysInfor> list) {
        this.list = list;
    }

    public List<SysInfor> getList() {
        return list;
    }

    public void setList(List<SysInfor> list) {
        this.list = list;
    }

    public class SysInfor{

        private String title;
        private String content;
        private String date;

        public SysInfor(String title, String content, String date) {
            this.title = title;
            this.content = content;
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}

