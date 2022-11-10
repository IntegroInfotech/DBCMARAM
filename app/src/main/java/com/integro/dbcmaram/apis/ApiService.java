package com.integro.dbcmaram.apis;

import com.integro.dbcmaram.model.AnnouncementList;
import com.integro.dbcmaram.model.EventsList;
import com.integro.dbcmaram.model.NewsList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/dbcmaram_app/dbcmaram_news.php")
    Call<NewsList> getNewsList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("/dbcmaram_app/dbcmaram_announcement.php")
    Call<AnnouncementList> getAnnouncementList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("/dbcmaram_app/dbcmaram_events.php")
    Call<EventsList> getEventsList(@Field("updated_at")String updated_at);
}
