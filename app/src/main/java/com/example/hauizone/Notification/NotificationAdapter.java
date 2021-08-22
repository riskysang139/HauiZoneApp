package com.example.hauizone.Notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hauizone.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    Context context;
    List<Notification> notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    public NotificationAdapter(NotificationAdapter notificationAdapter, Context baseContext) {
    }


    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NotificationAdapter.NotificationViewHolder holder, int position) {

        Notification notification = notifications.get(position);
        if(notification != null){
            holder.type_notifi.setText(notification.getType());
            holder.datetime.setText(notification.getDate() + "   " + notification.getTime());
            holder.content_notifi.setText(notification.getContent());
            Glide.with(context).load(notification.getImageNotification()).into(holder.image_notifi);
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{

        TextView type_notifi, datetime,content_notifi;
        ImageView imageView2, image_notifi;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            type_notifi = itemView.findViewById(R.id.type_notifi);
            datetime = itemView.findViewById(R.id.datetime);
            content_notifi = itemView.findViewById(R.id.content_notifi);
            imageView2 = itemView.findViewById(R.id.imageView2);
            image_notifi = itemView.findViewById(R.id.image_notifi);
        }
    }
}
