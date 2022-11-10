package com.integro.dbcmaram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.dbcmaram.R;
import com.integro.dbcmaram.model.Announcement;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder> {

    private ArrayList<Announcement> announcementArrayList;
    private Context context;

    public AnnouncementAdapter(Context context, ArrayList<Announcement> announcementArrayList) {
        this.context = context;
        this.announcementArrayList = announcementArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_announcements, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Announcement announcement = announcementArrayList.get(position);

        holder.tvDate.setText(announcement.getDate());
        holder.tvTitle.setText(announcement.getTitle());
        holder.tvDescription.setText(announcement.getDescription());
    }

    @Override
    public int getItemCount() {
        return announcementArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvTitle, tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
