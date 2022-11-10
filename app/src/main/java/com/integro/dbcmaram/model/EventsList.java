package com.integro.dbcmaram.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsList {

    private String success;

    private String message;

    @SerializedName("dbcmaram_events")
    private ArrayList<Events> dbcmaram_events;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ArrayList<Events> getDbcmaram_events() {
        return dbcmaram_events;
    }

    public void setDbcmaram_events(ArrayList<Events> dbcmaram_events) {
        this.dbcmaram_events = dbcmaram_events;
    }
}
