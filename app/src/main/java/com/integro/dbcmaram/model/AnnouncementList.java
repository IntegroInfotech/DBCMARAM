package com.integro.dbcmaram.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AnnouncementList  {

    private String success;

    private String message;

    @SerializedName("dbcmaram_announcement")
    private ArrayList<Announcement> announcementArrayList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Announcement> getAnnouncementArrayList() {
        return announcementArrayList;
    }

    public void setAnnouncementArrayList(ArrayList<Announcement> announcementArrayList) {
        this.announcementArrayList = announcementArrayList;
    }
}
