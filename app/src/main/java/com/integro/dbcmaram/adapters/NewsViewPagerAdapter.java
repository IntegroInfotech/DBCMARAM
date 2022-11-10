package com.integro.dbcmaram.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.dbcmaram.NewsDetailsActivity;
import com.integro.dbcmaram.R;
import com.integro.dbcmaram.model.News;

import java.util.ArrayList;

import static com.integro.dbcmaram.Constants.DATA;

public class NewsViewPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<News> newsArrayList;
    LayoutInflater inflater;

    public NewsViewPagerAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView ivFullImage;
        TextView title;

        View view = LayoutInflater.from(context).inflate(R.layout.card_coverphots2, container, false);

        ivFullImage = (ImageView) view.findViewById(R.id.ivImage);
        title = view.findViewById(R.id.btnNewsTitle);
        title.setText(newsArrayList.get(position).getTitle());

        Glide.with(context)
                .load(newsArrayList.get(position).getImage())
                .into(ivFullImage);

        ((ViewPager) container).addView(view);

        ivFullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position", position);
                intent.putExtra(DATA, newsArrayList);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
