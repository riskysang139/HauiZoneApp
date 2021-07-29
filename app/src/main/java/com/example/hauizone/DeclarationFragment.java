package com.example.hauizone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.databinding.FragmentDeclarationBinding;

public class DeclarationFragment extends Fragment {

FragmentDeclarationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_declaration,container,false);
        View view=binding.getRoot();
        setEvents();
        return view;
    }

    private void setEvents() {
        binding.imgBack.setOnClickListener(v->setClickBack());
    }

    private void setClickBack() {
        MainActivity main = (MainActivity) this.getContext();
        main.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, new AccountFragment())
                .addToBackStack(null).commit();
    }
}