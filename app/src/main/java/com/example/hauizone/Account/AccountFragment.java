package com.example.hauizone.Account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentAccountBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {
    FragmentAccountBinding binding;
    BaseDatabase mBaseDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        View view = binding.getRoot();

        setEvents();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void setEvents() {
        binding.thongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity) v.getContext();
                main.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragment, new PersonInformationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        binding.toKhaiYTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity) v.getContext();
                main.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragment, new DeclarationFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
        //
        binding.dieuKhoanSuDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://tokhaiyte.vn/dieu-khoan-bao-mat")));
            }
        });
        binding.guiHoiDap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://moh.gov.vn/hoi-dap-y-te")));
            }
        });
        binding.hauiFanpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/botruongboyte.vn/")));
            }
        });


        binding.btnDangXuat.setOnClickListener(v -> close());
    }

    private void close() {
        //dialog

        mBaseDatabase = new BaseDatabase(getContext());
        mBaseDatabase.setFlagOut();

        Intent intent = new Intent(getContext(), SignInActivity.class);
        startActivity(intent);
    }
}
