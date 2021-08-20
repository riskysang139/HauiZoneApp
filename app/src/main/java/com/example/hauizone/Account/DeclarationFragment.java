package com.example.hauizone.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.Account.AccountFragment;
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
        bottomNavigationView.setVisibility(View.INVISIBLE);
        View view = binding.getRoot();
        setEvents();
        return view;
    }

    private void setEvents() {

        binding.imgBack.setOnClickListener(v -> setClickBack());
        binding.entryDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragment, new ShowListEntryDeclaration())
                        .addToBackStack(null).commit();
            }
        });
    }

    private void setClickBack() {
        MainActivity main = (MainActivity) this.getContext();
        main.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, new AccountFragment())
                .addToBackStack(null).commit();
    }

}