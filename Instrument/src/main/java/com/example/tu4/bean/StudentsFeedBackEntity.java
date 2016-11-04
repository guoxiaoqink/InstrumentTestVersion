package com.example.tu4.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MR.WEN on 2016/11/4.
 */

public class StudentsFeedBackEntity implements Serializable {
    private List<StudentsFeedBackListEntity> Topic;

    public StudentsFeedBackEntity() {
        setTopic(new ArrayList<StudentsFeedBackListEntity>());
    }


    public List<StudentsFeedBackListEntity> getTopic() {
        return Topic;
    }

    public void setTopic(List<StudentsFeedBackListEntity> topic) {
        Topic = topic;
    }

    public static class StudentsFeedBackListEntity {
        private int Topic_id;
        private String Content;
        private String Date;
        private String Name;

        public int getTopic_id() {
            return Topic_id;
        }

        public void setTopic_id(int topic_id) {
            Topic_id = topic_id;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }

}
