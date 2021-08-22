package com.example.hauizone.Account.Report;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.Report.Report;
import com.example.hauizone.databinding.FragmentReportBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateReport extends AppCompatActivity {
    FragmentReportBinding binding;
    BaseDatabase baseDatabase;
    final Calendar calendar = Calendar.getInstance();
    int Year = calendar.get(Calendar.YEAR);
    int Month = calendar.get(Calendar.MONTH);
    int Day = calendar.get(Calendar.DATE);
    Report report;
    ArrayList<String> typeReportList;
    ArrayAdapter<String> typeReportAdapter;
    ArrayAdapter<String> provinceAdapter,districtAdapter,wardAdapter;
    String date,name,sdt,province,district, ward,street,typeReport,content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_report);
        baseDatabase = BaseDatabase.getInstance(getBaseContext());
        binding.txtTimeDetect.setOnClickListener(view -> showDateDialog());
        setSpinnerAndAutoCompleteTextView();
        setUpDisplay();
        setDatimeDialog();
        binding.btnUpdateReport.setOnClickListener(view -> {
            setInit();
            if(!setCheckRequired()){
                showDialog();
            }
            else {
                updateData();
            }
        });
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
    private void setUpDisplay() {
        Bundle bundle = getIntent().getExtras();
        if ( bundle.get("report") == null) {
            binding.btnSubmitReport.setVisibility(View.VISIBLE);
            binding.btnUpdateReport.setVisibility(View.INVISIBLE);
            return;
        } else {
            binding.btnSubmitReport.setVisibility(View.INVISIBLE);
            binding.btnUpdateReport.setVisibility(View.VISIBLE);
            report = (Report) bundle.get("report");
            binding.txtTimeDetect.setText(report.getTimeDetectReport());
            binding.txtNameReport.setText(report.getNameReport());
            binding.txtSdtReport.setText(report.getSdtReport());
            binding.txtProvince.setText(report.getProvince());
            binding.txtDistrict.setText(report.getDistrict());
            binding.txtWards.setText(report.getWard());
            binding.txtStreet.setText(report.getStreet());
            if(report.getTypeReport().equals("Có người nghi nhiễm mắc bệnh")){
                binding.typeReport.setSelection(0);
            }else if(report.getTypeReport().equals("Có người tiếp xúc với người mắc bệnh")){
                binding.typeReport.setSelection(1);
            }else {
                binding.typeReport.setSelection(2);
            }
            binding.txtContent.setText(report.getContentReport());
        }
    }
    private void setSpinnerAndAutoCompleteTextView(){
        String[] province = {"Hà Nội" , "Hải Phòng" , "Huế" , "Vĩnh Phúc"};
        String[] district = {"Ba Đình" , "Bắc Từ Liêm" , "Vĩnh Tường" , "Văn Minh"};
        String[] ward = {"Cầu Diễn" , "Cam" , "Ngũ Kiên" , "Nguyên Xá"};
        provinceAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_expandable_list_item_1,province);
        districtAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_expandable_list_item_1,district);
        wardAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_expandable_list_item_1,ward);
        binding.txtProvince.setAdapter(provinceAdapter);
        binding.txtDistrict.setAdapter(districtAdapter);
        binding.txtWards.setAdapter(wardAdapter);
        typeReportList = new ArrayList<>();
        typeReportList.add("Có người nghi nhiễm mắc bệnh");
        typeReportList.add("Có người tiếp xúc với người mắc bệnh");
        typeReportList.add("Có người trở về từ vùng dịch");
        typeReportAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item,typeReportList);
        binding.typeReport.setAdapter(typeReportAdapter);
    }
    private boolean setCheckRequired(){
        if(isEmpty(date) && isEmpty(name) && isEmpty(sdt) && isEmpty(province) && isEmpty(district) && isEmpty(ward)){
            return false;
        }
        return true;
    }
    private boolean isEmpty(String s){
        if(s.equals("")) return true;
        else return false;
    }
    private void updateData(){
        Log.e("thang","onUpdate");
        report.setTimeDetectReport(date);
        report.setNameReport(name);
        report.setSdtReport(sdt);
        report.setProvince(province);
        report.setDistrict(district);
        report.setWard(ward);
        report.setStreet(street);
        report.setTypeReport(typeReport);
        report.setContentReport(content);
        report.setIdUser(MainActivity.INDEX);
        int rowEffect = baseDatabase.updateReport(report);
        if(rowEffect != 0){
            Toast.makeText(getBaseContext(),"Cập nhật phản ánh thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getBaseContext(),"Cập nhật phản ánh không thành công",Toast.LENGTH_SHORT).show();
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
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

    }
}
