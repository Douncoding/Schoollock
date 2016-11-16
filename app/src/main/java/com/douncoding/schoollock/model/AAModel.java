package com.douncoding.schoollock.model;

/**
 * Attendance-Absence Model (출결모델)
 */
public class AAModel {
    String date;
    String state;


    public AAModel(String date, String state) {
        this.date = date;
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
