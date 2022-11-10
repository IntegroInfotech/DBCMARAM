package com.integro.dbcmaram.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.integro.dbcmaram.Constants.BASE_URL;

public class ApiClients {

    public static final String BASE_URL="";
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
