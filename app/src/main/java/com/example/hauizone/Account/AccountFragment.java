package com.example.hauizone.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {
    FragmentAccountBinding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_account,container,false);
        View view=binding.getRoot();
        setEvents();
        return view;

    }

    private void setEvents() {
        binding.thongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity) v.getContext();
                main.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragment, new PersonInformationFragment())
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
                        .commit();

            }
        });
    }
}
