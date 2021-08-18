package com.example.hauizone.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.Account.AccountFragment;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentInfoDeclarationBinding;

public class InfoDeclarationFragment extends Fragment {
    FragmentInfoDeclarationBinding binding;

    // rb_x_1 : co
    // rb_x_2 : khong
    // x = 1 : Nguoi bẹnh hoac nghi ngo
    // x = 2 : Mac benh COVID-19
    // x = 3 : Nguoi tu nuoc ngoai co dich
    // x = 4 : COVID-19
    // x = 5 : Nguoi co bieu hien (sot, ho, kho tho, viem phoi)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_declaration, container, false);
        View view = binding.getRoot();
        setEvents();
        return view;
    }

    private void setEvents() {
        binding.imgBack.setOnClickListener(v -> setClickBack());
    }

    private void setClickBack() {
        MainActivity main = (MainActivity) this.getContext();
        main.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, new AccountFragment())
                .addToBackStack(null).commit();
    }
    private void setRadioButton(){

    }
}