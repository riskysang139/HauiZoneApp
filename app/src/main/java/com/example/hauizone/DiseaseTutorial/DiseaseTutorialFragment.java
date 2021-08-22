package com.example.hauizone.DiseaseTutorial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hauizone.ScanQR.ItemClickListener;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentDiseaseTutorialBinding;

import java.util.ArrayList;

public class DiseaseTutorialFragment extends Fragment implements ItemClickListener {
    private FragmentDiseaseTutorialBinding binding;
    private DiseaseTutorialAdapter adapter;
    private ArrayList<DiseaseTutorial> arrayList;

    private final String linkTutorial1="https://ncovi.vnpt.vn/views/huongdan_1.html";
    private final String linkTutorial2="https://ncovi.vnpt.vn/views/huongdan_9.html";
    private final String linkTutorial3="https://ncovi.vnpt.vn/views/doivoihk.html";
    private final String linkTutorial4="https://ncovi.vnpt.vn/views/huongdan_5.html";
    private final String linkTutorial5="https://ncovi.vnpt.vn/views/huongdan_7.html";
    private final String linkTutorial6="https://ncovi.vnpt.vn/views/chungsongantoan.html";
    private final String linkTutorial7="https://ncovi.vnpt.vn/views/voice.html";
    private final String linkTutorial8="https://ncovi.vnpt.vn/views/toadam_5k.html";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_disease_tutorial, container, false);
        View view = binding.getRoot();
        release();
        adapter=new DiseaseTutorialAdapter(arrayList,this::OnClick);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.listViewTutorial.setLayoutManager(layoutManager);
        binding.listViewTutorial.setAdapter(adapter);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }


    @Override
    public void OnClick(int position) {
        switch (position)
        {
            case 0:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial1)));
                break;
            case 1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial2)));
                break;
            case 2:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial3)));
                break;
            case 3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial4)));
                break;
            case 4:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial5)));
                break;
            case 5:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial6)));
                break;
            case 6:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial7)));
                break;
            case 7:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkTutorial8)));
                break;

        }
    }
    private void release()
    {
        arrayList=new ArrayList<>();
        arrayList.add(new DiseaseTutorial("Dành cho học sinh",R.drawable.ic_student,getResources().getColor(R.color.tutorial_image_1),getResources().getColor(R.color.tutorial_main_1)));
        arrayList.add(new DiseaseTutorial("Dành cho trường học",R.drawable.ic_school,getResources().getColor(R.color.tutorial_image_2),getResources().getColor(R.color.tutorial_main_2)));
        arrayList.add(new DiseaseTutorial("Dành cho hành khách sử dụng phương tiện công cộng",R.drawable.ic_school_bus,getResources().getColor(R.color.main_color),getResources().getColor(R.color.tutorial_main_3)));
        arrayList.add(new DiseaseTutorial("Dành cho chung cư",R.drawable.ic_building,getResources().getColor(R.color.tutorial_image_4),getResources().getColor(R.color.tutorial_main_4)));
        arrayList.add(new DiseaseTutorial("Dành cho người có triệu chứng",R.drawable.ic_mask,getResources().getColor(R.color.tutorial_image_5),getResources().getColor(R.color.tutorial_main_5)));
        arrayList.add(new DiseaseTutorial("Thông Điệp 5k",R.drawable.ic_hospital,getResources().getColor(R.color.tutorial_image_1),getResources().getColor(R.color.tutorial_main_1)));
        arrayList.add(new DiseaseTutorial("Audio hướng dẫn phòng chống dịch",R.drawable.ic_headphone,getResources().getColor(R.color.tutorial_image_2),getResources().getColor(R.color.tutorial_main_2)));
        arrayList.add(new DiseaseTutorial("Toạ đàm, phòng chống dịch COVID-19",R.drawable.ic_group,getResources().getColor(R.color.main_color),getResources().getColor(R.color.tutorial_main_3)));

//        arrayList.add(new DiseaseTutorial("Dành Cho hành khách tham gia giao thông",R.drawable.haui_ncovi_logo_image));
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
