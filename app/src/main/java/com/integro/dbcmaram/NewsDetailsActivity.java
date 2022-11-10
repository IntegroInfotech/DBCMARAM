package com.integro.dbcmaram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.dbcmaram.model.News;

import java.util.ArrayList;

import static com.integro.dbcmaram.Constants.DATA;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        int position = (int) getIntent().getSerializableExtra("position");
        ArrayList<News> newsArrayList = (ArrayList<News>) getIntent().getSerializableExtra(DATA);

        ImageView ivImage = findViewById(R.id.ivImage);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDescription = findViewById(R.id.tvDescription);

        Glide.with(getApplicationContext())
                .load(newsArrayList.get(position).getImage())
                .into(ivImage);

        tvDate.setText(newsArrayList.get(position).getDate());
        tvTitle.setText(newsArrayList.get(position).getTitle());
        tvDescription.setText(newsArrayList.get(position).getDescription());
    }
}
