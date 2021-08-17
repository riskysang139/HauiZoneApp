package com.example.hauizone.Report;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentReportBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportFragment extends Fragment {
    FragmentReportBinding binding;
    final Calendar calendar = Calendar.getInstance();
    int Year = calendar.get(Calendar.YEAR);
    int Month = calendar.get(Calendar.MONTH);
    int Day = calendar.get(Calendar.DATE);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false);
        View view = binding.getRoot();
        setDatimeDialog();
        return view;
    }

    private void setDatimeDialog() {
        binding.txtTimeDetect.setInputType(InputType.TYPE_NULL);
        binding.txtTimeDetect.setOnClickListener(new View.OnClickListener() {
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
                binding.txtTimeDetect.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, Year, Month, Day);

        datePickerDialog.show();
    }


}
