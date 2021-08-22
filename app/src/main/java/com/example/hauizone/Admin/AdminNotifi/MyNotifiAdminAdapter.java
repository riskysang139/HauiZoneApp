package com.example.hauizone.Admin.AdminNotifi;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.hauizone.Admin.AdminDomesticAndEntry.RCVDomesticAdapter;
import com.example.hauizone.Notification.Notification;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.Notification.NotificationFragment;
import com.example.hauizone.R;


import java.util.ArrayList;

public class MyNotifiAdminAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Notification> arrayList;
    Context context;
    ClickListener clickListener;

    public MyNotifiAdminAdapter(ArrayList<Notification> arrayList, Context context, ClickListener clickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.clickListener = clickListener;
    }

    public MyNotifiAdminAdapter(ArrayList<Notification> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        return new com.example.hauizone.Admin.AdminNotifi.MyNotifiAdminAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        Notification notification = arrayList.get(position);
        viewHolder.txtType.setText(notification.getType() + "   ");
        viewHolder.txtDateTime.setText(notification.getDate() + "   " + notification.getTime());
        viewHolder.txtContent.setText(notification.getContent() + "   ");
        Glide.with(context).load(notification.getImageNotification()).into(((ViewHolder) holder).txtImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtType, txtDateTime, txtContent;
        ImageView txtImage;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType=itemView.findViewById(R.id.type_notifi);
            txtDateTime=itemView.findViewById(R.id.datetime);
            txtContent=itemView.findViewById(R.id.content_notifi);
            txtImage=itemView.findViewById(R.id.image_notifi);
            linearLayout=itemView.findViewById(R.id.layoutItem);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(arrayList.get(getAdapterPosition()),getAdapterPosition());
                }
            });
            linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onLongClick(arrayList.get(getAdapterPosition()),getAdapterPosition());
                    return false;
                }
            });
        }
    }
    public interface ClickListener
    {
        void onClick(Notification notification,int position);
        void onLongClick(Notification notification,int position);
    }
}

