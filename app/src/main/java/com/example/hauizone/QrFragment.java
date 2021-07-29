package com.example.hauizone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.databinding.FragmentHomeBinding;
import com.example.hauizone.databinding.FragmentQrBinding;

import java.util.ArrayList;

public class QrFragment extends Fragment {
    FragmentQrBinding binding;
    AdapterNearPlace adapterNearPlace;
    AdapterYourRoute adapterYourRoute;
    ArrayList<DataNearPlace> dataNearPlaces;
    ArrayList<DataYourRoute> dataYourRoutes;
    final static String ICON_HOSPITAL = "https://www.iconpacks.net/icons/1/free-hospital-icon-1066-thumb.png";
    final static String ICON_CHURCH = "https://d338t8kmirgyke.cloudfront.net/icons/icon_pngs/000/001/827/original/church.png";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_qr,container,false);
        View view=binding.getRoot();

        data();

        binding.btnNearPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
                adapterNearPlace = new AdapterNearPlace(getContext(),dataNearPlaces);
                binding.rvQRCode.setLayoutManager(layoutManager);
                binding.rvQRCode.setAdapter(adapterNearPlace);
            }
        });
        binding.btnYourRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
                adapterYourRoute = new AdapterYourRoute(getContext(),dataYourRoutes);
                binding.rvQRCode.setLayoutManager(layoutManager);
                binding.rvQRCode.setAdapter(adapterYourRoute);
            }
        });
        return view;
    }

    private void data() {
        dataNearPlaces = new ArrayList<>();
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));

        dataYourRoutes = new ArrayList<>();
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Sài Gòn","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Bắc Giang","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Sài Gòn","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Bắc Giang","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));
        dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam","29/07/2021","20/07/2021"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        adapterNearPlace = new AdapterNearPlace(getContext(),dataNearPlaces);
        binding.rvQRCode.setLayoutManager(layoutManager);
        binding.rvQRCode.setAdapter(adapterNearPlace);
    }
}
