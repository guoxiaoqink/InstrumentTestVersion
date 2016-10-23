package com.example.tu4.bean;

/**
 * Created by MR.WEN on 2016/10/23.
 */

public class CourseDetails {
    private String imageUrl;
    private String title;
    private String leavel;
    private String teacher;
    private String position;
    private boolean aviliable;

    public CourseDetails(String imageUrl, String title, String leavel, String teacher, String position, boolean aviliable) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.leavel = leavel;
        this.teacher = teacher;
        this.position = position;
        this.aviliable = aviliable;
    }

    public CourseDetails() {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeavel() {
        return leavel;
    }

    public void setLeavel(String leavel) {
        this.leavel = leavel;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isAviliable() {
        return aviliable;
    }

    public void setAviliable(boolean aviliable) {
        this.aviliable = aviliable;
    }


}
