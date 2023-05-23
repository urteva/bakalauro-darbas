package com.example.adsinjava.model;

public class Schedule {
    public String getScheduleDetails() {
        return scheduleDetails;
    }

    public void setScheduleDetails(String scheduleDetails) {
        this.scheduleDetails = scheduleDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String scheduleDetails ="";
    private String id = "";
    private String scheduleType ="";
    private String date="";

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time="";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId="";

    public Schedule() {}

    public Schedule(String date, String scheduleType, String scheduleDetails, String time, String userId){
        this.date = date;
        this.scheduleType = scheduleType;
        this.scheduleDetails = scheduleDetails;
        this.time = time;
        this.userId = userId;

    }

}
