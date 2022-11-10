package com.integro.dbcmaram;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.dbcmaram.adapters.EventsAdapter;
import com.integro.dbcmaram.apis.ApiClients;
import com.integro.dbcmaram.apis.ApiService;
import com.integro.dbcmaram.model.Events;
import com.integro.dbcmaram.model.EventsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView rvEvents;
    private ArrayList<Events> eventsArrayList;
    private RecyclerView.LayoutManager manager;
    private EventsAdapter eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        apiService = ApiClients.getClient().create(ApiService.class);
        rvEvents = findViewById(R.id.rvEvents);
        eventsArrayList = new ArrayList<>();
        manager = new LinearLayoutManager(this);
        getEvents();
    }

    public void getEvents() {
        String date = "2020-02-03 00:19:07";
        Call<EventsList> eventsListCall = apiService.getEventsList(date);
        eventsListCall.enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(Call<EventsList> call, Response<EventsList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                int size = response.body().getDbcmaram_events().size();
                for (int i = 0; i < size; i++) {
                    eventsArrayList.add(response.body().getDbcmaram_events().get(i));
                }
                rvEvents.setLayoutManager(manager);
                eventsAdapter = new EventsAdapter(getApplicationContext(), eventsArrayList);
                rvEvents.setAdapter(eventsAdapter);
            }

            @Override
            public void onFailure(Call<EventsList> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
