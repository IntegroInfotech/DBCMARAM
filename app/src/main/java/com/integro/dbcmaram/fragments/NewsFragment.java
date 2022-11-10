package com.integro.dbcmaram.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.dbcmaram.R;
import com.integro.dbcmaram.adapters.NewsAdapter;
import com.integro.dbcmaram.apis.ApiClients;
import com.integro.dbcmaram.apis.ApiService;
import com.integro.dbcmaram.model.News;
import com.integro.dbcmaram.model.NewsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.GridLayout.VERTICAL;

public class NewsFragment extends Fragment {

    private ApiService apiService;
    private RecyclerView rvNews;
    private ArrayList<News> newsArrayList;
    private StaggeredGridLayoutManager manager;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        apiService = ApiClients.getClient().create(ApiService.class);
        rvNews = view.findViewById(R.id.rvNews);
        newsArrayList = new ArrayList<>();
        manager = new StaggeredGridLayoutManager(2,VERTICAL);
        getNews();

        return view;
    }

    public void getNews() {
        String date = "2020-02-03 00:19:07";
        Call<NewsList> newsListCall = apiService.getNewsList(date);
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
                rvNews.setLayoutManager(manager);
                newsAdapter = new NewsAdapter(getContext(), newsArrayList);
                rvNews.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {

                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
