package com.example.hauizone.Report;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentReportBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReportFragment extends Fragment {
    FragmentReportBinding binding;
    final Calendar calendar = Calendar.getInstance();
    int Year = calendar.get(Calendar.YEAR);
    int Month = calendar.get(Calendar.MONTH);
    int Day = calendar.get(Calendar.DATE);
    String date,name,sdt,province,district, ward,street,typeReport,content;
    ArrayList<String> typeReportList;
    ArrayAdapter<String> typeReportAdapter;
    ArrayAdapter<String> provinceAdapter,districtAdapter,wardAdapter;
    BaseDatabase mBaseDatabase;
    Boolean checkRequired;
    Boolean isAccept = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false);
        View view = binding.getRoot();
        mBaseDatabase = BaseDatabase.getInstance(getContext());
        setAdapter();
        setDatimeDialog();
        setEvent();
        if(isAccept){
            binding.btnSubmitReport.setClickable(true);
            binding.btnUpdateReport.setClickable(true);
        }else{
            binding.btnSubmitReport.setClickable(false);
            binding.btnUpdateReport.setClickable(false);
        }
        return view;
    }

    private void setInit(){
        date = binding.txtTimeDetect.getText().toString();
        name = binding.txtNameReport.getText().toString();
        sdt = binding.txtSdtReport.getText().toString();
        province = binding.txtProvince.getText().toString();
        district = binding.txtDistrict.getText().toString();
        ward = binding.txtWards.getText().toString();
        street = binding.txtStreet.getText().toString();
        typeReport = binding.typeReport.getSelectedItem().toString();
        content = binding.txtContent.getText().toString();
    }
    private void setAdapter(){
        String[] province = {"Hà Nội" , "Hải Phòng" , "Huế" , "Vĩnh Phúc"};
        String[] district = {"Ba Đình" , "Bắc Từ Liêm" , "Vĩnh Tường" , "Văn Minh"};
        String[] ward = {"Cầu Diễn" , "Cam" , "Ngũ Kiên" , "Nguyên Xá"};
        provinceAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1,province);
        districtAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1,district);
        wardAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1,ward);
        binding.txtProvince.setAdapter(provinceAdapter);
        binding.txtDistrict.setAdapter(districtAdapter);
        binding.txtWards.setAdapter(wardAdapter);
        typeReportList = new ArrayList<>();
        typeReportList.add("Có người nghi nhiễm mắc bệnh");
        typeReportList.add("Có người tiếp xúc với người mắc bệnh");
        typeReportList.add("Có người trở về từ vùng dịch");
        typeReportAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,typeReportList);
        binding.typeReport.setAdapter(typeReportAdapter);
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
    private boolean setCheckRequired(){
        if(isEmpty(date) || isEmpty(name) || isEmpty(sdt) || isEmpty(province) || isEmpty(district) || isEmpty(ward)){
            return false;
        }
        return true;
    }
    private boolean isEmpty(String s){
        if(s.equals("")) return true;
        else return false;
    }
    private void insertReport(){
        Report report = new Report(date,name,sdt,province,district,ward,street,typeReport,content,MainActivity.INDEX);
        Boolean success = mBaseDatabase.insertReport(report);
        if(success){
            System.out.println("tạo oke");
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Thông báo");
            builder.setMessage("Tạo phản ánh thành công!");
            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(),"Tạo phản ánh thành công",Toast.LENGTH_SHORT).show();
                }
            });
            Dialog dialog = builder.create();
            dialog.show();
            Log.e("E","insertReport");

        }
        else Toast.makeText(getContext(),"Tạo phản ánh không thành công",Toast.LENGTH_SHORT).show();
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn phải nhập đầy đủ thông tin yêu cầu!");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }
    private void setEvent(){
        binding.btnSubmitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInit();
                if(!setCheckRequired()){
                    showDialog();
                }
                else {
                    insertReport();
                }
            }
        });
        binding.isAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAccept = !isAccept;
                if(isAccept){
                    binding.btnSubmitReport.setClickable(true);
                    binding.btnUpdateReport.setClickable(true);
                }else{
                    binding.btnSubmitReport.setClickable(false);
                    binding.btnUpdateReport.setClickable(false);
                }
            }
        });
    }

}
