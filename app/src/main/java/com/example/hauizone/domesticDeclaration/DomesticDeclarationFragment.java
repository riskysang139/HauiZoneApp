package com.example.hauizone.domesticDeclaration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentDomesticDeclarationBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DomesticDeclarationFragment extends Fragment {
    FragmentDomesticDeclarationBinding binding;
    final Calendar calendar = Calendar.getInstance();
    int Year = calendar.get(Calendar.YEAR);
    int Month = calendar.get(Calendar.MONTH);
    int Day = calendar.get(Calendar.DATE);
    ArrayList<String> spinnerVehicles = null;
    ArrayList<String> autoCompleteStartEnd = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_domestic_declaration, container, false);
        View view = binding.getRoot();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        setDatimeDialog();
        setSpinner();
        setAutoCompleteStartEnd();
        return view;
    }

    private void setDatimeDialog() {
        binding.dateOfBirth.setInputType(InputType.TYPE_NULL);
        binding.dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                calendar.set(Year,Month,Day);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.dateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, Year, Month, Day);

        datePickerDialog.show();
    }
    private void setSpinner(){
        ArrayAdapter spinnerVehicleAdapter = null;
        spinnerVehicles = new ArrayList<>();
        spinnerVehicles.add("Máy bay");
        spinnerVehicles.add("Xe gắn máy");
        spinnerVehicles.add("Tàu hoả");
        spinnerVehicleAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,spinnerVehicles);
        binding.spinnerVehicles.setAdapter(spinnerVehicleAdapter);
    }
    private void setAutoCompleteStartEnd(){
        autoCompleteStartEnd = new ArrayList<>();
        autoCompleteStartEnd.add("Hà Nội");
        autoCompleteStartEnd.add("Vĩnh Phúc");
        autoCompleteStartEnd.add("Đà Nẵng");
        autoCompleteStartEnd.add("Bắc Giang");
        String arr[] = {"Hà Nội", "Huế", "Sài gòn",
                "Thái Bình", "Bắc Giang", "Nam Định",
                "Lâm đồng", "Long khánh", "Hưng Yên", "Hà Nam"};
        binding.startPoint.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arr));
        binding.endPoint.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arr));
    }
}