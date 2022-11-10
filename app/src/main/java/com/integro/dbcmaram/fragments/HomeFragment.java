package com.integro.dbcmaram.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.demono.AutoScrollViewPager;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.integro.dbcmaram.EventsActivity;
import com.integro.dbcmaram.MainActivity;
import com.integro.dbcmaram.R;
import com.integro.dbcmaram.WebActivity;
import com.integro.dbcmaram.adapters.NewsViewPagerAdapter;
import com.integro.dbcmaram.apis.ApiClients;
import com.integro.dbcmaram.apis.ApiService;
import com.integro.dbcmaram.model.News;
import com.integro.dbcmaram.model.NewsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.integro.dbcmaram.Constants.URL;
import static com.integro.dbcmaram.Constants.YOUTUBE_API_KEY;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ApiService apiServices;
    private AutoScrollViewPager vpNews;
    private ArrayList<News> newsArrayList;
    private NewsViewPagerAdapter newsViewPagerAdapter;

    private YouTubePlayer YPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apiServices = ApiClients.getClient().create(ApiService.class);
        newsArrayList = new ArrayList<>();
        vpNews = view.findViewById(R.id.vpNews);

        TextView aboutUs = view.findViewById(R.id.aboutUs);
        TextView announcements = view.findViewById(R.id.announcements);
        TextView upcomingEvents = view.findViewById(R.id.upcomingEvents);
        TextView portalLogin = view.findViewById(R.id.portalLogin);
        TextView departments = view.findViewById(R.id.departments);
        TextView academic = view.findViewById(R.id.academic);
        TextView magazine = view.findViewById(R.id.magazine);

        aboutUs.setOnClickListener(this);
        announcements.setOnClickListener(this);
        upcomingEvents.setOnClickListener(this);
        portalLogin.setOnClickListener(this);
        departments.setOnClickListener(this);
        academic.setOnClickListener(this);
        magazine.setOnClickListener(this);

        YouTubePlayerSupportFragment youTubePlayerFragment = new YouTubePlayerSupportFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    YPlayer = youTubePlayer;
                    YPlayer.cueVideo("tBtkZfhWF0M");
                    YPlayer.setFullscreen(false);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                // TODO Auto-generated method stub
            }
        });

        getNewsList();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aboutUs:
                String url = "http://integroinfotech.com/dbcmaram_app/about-us.php";
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra(URL, url);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.upcomingEvents:
                Intent upcomingEvents = new Intent(getContext(), EventsActivity.class);
                startActivity(upcomingEvents);
                break;

            case R.id.portalLogin:
                String url1 = "http://portal.higrade.co.in/";
                Intent intent1 = new Intent(getContext(), WebActivity.class);
                intent1.putExtra(URL, url1);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;

            case R.id.announcements:
                ((MainActivity) getActivity()).selectFragment(2);
                break;

            case R.id.departments:
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.academic:
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.magazine:
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getNewsList() {
        String date = "2019-10-26 22:00:51";
        Call<NewsList> newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                int size = response.body().getNewsArrayList().size();
                for (int i = 0; i < size; i++) {
                    newsArrayList.add(response.body().getNewsArrayList().get(i));
                }
                newsViewPagerAdapter = new NewsViewPagerAdapter(getContext(), newsArrayList);
                vpNews.setAdapter(newsViewPagerAdapter);
                vpNews.startAutoScroll(3000);
                vpNews.setCycle(true);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

