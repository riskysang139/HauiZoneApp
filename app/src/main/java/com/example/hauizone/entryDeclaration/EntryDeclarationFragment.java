package com.example.hauizone.entryDeclaration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentEntryDeclarationBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EntryDeclarationFragment extends Fragment {
    FragmentEntryDeclarationBinding binding;
    final Calendar calendar = Calendar.getInstance();
    private int Year = 2000, Month = 0, Day = 1;
    private DatePickerDialog datePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry_declaration, container, false);
        View view = binding.getRoot();
        binding.txtDateOfBirth.setInputType(InputType.TYPE_NULL);
        binding.txtDateEntry.setInputType(InputType.TYPE_NULL);
        binding.txtDateOfBirth.setOnClickListener(v -> showDateDialog());
        binding.txtDateEntry.setOnClickListener(v -> showDateEntryDialog());
        return view;
    }

    private void showDateEntryDialog() {
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                calendar.set(Year, Month, Day);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.txtDateEntry.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, Year, Month, Day);
        datePickerDialog.setTitle("Date Entry");
        datePickerDialog.show();
    }

    private void showDateDialog() {
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                calendar.set(Year, Month, Day);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.txtDateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, Year, Month, Day);
        datePickerDialog.setTitle("Date Of Birth");
        datePickerDialog.show();
    }
}
