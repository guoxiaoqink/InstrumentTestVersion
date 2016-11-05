package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MR.WEN on 2016/11/4.
 */

public class StudentFeedbackInfo implements Serializable {
    private List<FeedbackInfo> Topic;

    public List<FeedbackInfo> getTopic() {
        return Topic;
    }

    public void setTopic(List<FeedbackInfo> topic) {
        Topic = topic;
    }

    @Override
    public String toString() {
        return "StudentFeedbackInfo{" +
                "Topic=" + Topic +
                '}';
    }
}
