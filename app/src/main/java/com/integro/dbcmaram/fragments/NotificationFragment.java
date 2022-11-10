package com.integro.dbcmaram.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.dbcmaram.R;
import com.integro.dbcmaram.adapters.AnnouncementAdapter;
import com.integro.dbcmaram.apis.ApiClients;
import com.integro.dbcmaram.apis.ApiService;
import com.integro.dbcmaram.model.Announcement;
import com.integro.dbcmaram.model.AnnouncementList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {

    private ApiService apiService;
    private LinearLayoutManager manager;
    private RecyclerView rvAnnouncements;
    private ArrayList<Announcement> announcementArrayList;
    private AnnouncementAdapter announcementAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        apiService = ApiClients.getClient().create(ApiService.class);
        announcementArrayList = new ArrayList<>();
        manager = new LinearLayoutManager(getContext());
        rvAnnouncements = view.findViewById(R.id.rvAnnouncements);
        getAnnouncements();
        return view;
    }

    private void getAnnouncements() {
        String date = "2020-01-31 21:59:38";
        Call<AnnouncementList> announcementListCall = apiService.getAnnouncementList(date);
        announcementListCall.enqueue(new Callback<AnnouncementList>() {
            @Override
            public void onResponse(Call<AnnouncementList> call, Response<AnnouncementList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                int size = response.body().getAnnouncementArrayList().size();
                for (int i = 0; i < size; i++) {
                    announcementArrayList.add(response.body().getAnnouncementArrayList().get(i));
                }
                rvAnnouncements.setLayoutManager(manager);
                announcementAdapter = new AnnouncementAdapter(getContext(), announcementArrayList);
                rvAnnouncements.setAdapter(announcementAdapter);
            }

            @Override
            public void onFailure(Call<AnnouncementList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
