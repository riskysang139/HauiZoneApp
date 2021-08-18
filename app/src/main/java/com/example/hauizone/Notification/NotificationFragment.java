package com.example.hauizone.Notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentNotificationBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment{
    FragmentNotificationBinding binding;
    List<Notification> arrList ;
    NotificationAdapter notificationAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        View view = binding.getRoot();
        loadNotificationFragment();
        return view;
    }
    public void addDataNotificationFragment(){
        Notification notification1 = new Notification();
        Notification notification2 = new Notification();
        Notification notification3 = new Notification();
        Notification notification4 = new Notification();

        notification1.setType("HAUI NCOVI - TIN COVID");
        notification1.setDate("12/07/2021");
        notification1.setTime("12:05");
        notification1.setContent("Có 15 bệnh nhân đã được xuất viện ở bệnh viện A");
        notification1.setImageNotification(R.drawable.benhviena);

        notification2.setType("HAUI NCOVI - THÔNG BÁO");
        notification2.setDate("12/07/2021");
        notification2.setTime("12:06");
        notification2.setContent("Bạn cần khai báo y tế đúng giờ");
        notification2.setImageNotification(R.drawable.khaibao);

        notification3.setType("HAUI NCOVI - TIN COVID");
        notification3.setDate("12/07/2021");
        notification3.setTime("12:07");
        notification3.setContent("Có 49 bệnh nhân đã được xuất viện ở bệnh viện B");
        notification3.setImageNotification(R.drawable.benhvienb);

        notification4.setType("HAUI NCOVI - THÔNG BÁO");
        notification4.setDate("12/07/2021");
        notification4.setTime("12:08");
        notification4.setContent("Bản cập nhật mới đã sẵn sàng");
        notification4.setImageNotification(R.drawable.update);

        arrList.add(notification1);
        arrList.add(notification2);
        arrList.add(notification3);
        arrList.add(notification4);
    }

    public void loadNotificationFragment(){
        arrList = new ArrayList<Notification>();
        addDataNotificationFragment();
        notificationAdapter = new NotificationAdapter(getActivity(),arrList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rvNotification.setLayoutManager(layoutManager);
        binding.rvNotification.setAdapter(notificationAdapter);
       
    }
}
