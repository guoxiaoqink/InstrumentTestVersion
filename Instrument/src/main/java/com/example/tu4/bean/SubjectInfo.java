package com.example.tu4.bean;

import java.io.Serializable;

/**
 * Created by MR.WEN on 2016/11/1.
 */

public class SubjectInfo implements Serializable{
    private String Teacher_name;
    private int Student_number;
    private String Teacher_telephone;

    public String getTeacher_name() {
        return Teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        Teacher_name = teacher_name;
    }

    public int getStudent_number() {
        return Student_number;
    }

    public void setStudent_number(int student_number) {
        Student_number = student_number;
    }

    public String getTeacher_telephone() {
        return Teacher_telephone;
    }

    public void setTeacher_telephone(String teacher_telephone) {
        Teacher_telephone = teacher_telephone;
    }

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "Teacher_name='" + Teacher_name + '\'' +
                ", Student_number=" + Student_number +
                ", Teacher_telephone='" + Teacher_telephone + '\'' +
                '}';
    }
}
