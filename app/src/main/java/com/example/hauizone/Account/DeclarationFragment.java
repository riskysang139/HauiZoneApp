package com.example.hauizone.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.Account.AccountFragment;
import com.example.hauizone.Account.Report.ShowListReport;
import com.example.hauizone.Account.UpdateEntryDeclaration.ShowListEntryDeclaration;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentDeclarationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeclarationFragment extends Fragment {

    FragmentDeclarationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_declaration, container, false);
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.GONE);
        View view = binding.getRoot();
        setEvents();
        binding.entryDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragment, new ShowListEntryDeclaration())
                        .addToBackStack(null).commit();
            }
        });
        binding.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragment, new ShowListReport())
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }

    private void setEvents() {
        binding.imgBack.setOnClickListener(v -> getActivity().onBackPressed());
    }


}