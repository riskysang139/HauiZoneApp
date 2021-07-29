package com.example.hauizone;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.databinding.FragmentPersonInformationBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonInformationFragment extends Fragment {


FragmentPersonInformationBinding binding;

    List<String> listTinh, listQuan, listPhuong ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_person_information,container,false);
        View view=binding.getRoot();
        setEvents();
        return view;
    }

    private void setEvents() {

        getDataList();
        setAutoComplete();
        //cap nhat
        binding.btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndAddList();
            }
        });


        // date_
        binding.imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBirthday();
            }
        });
        // back
        binding.imgBack.setOnClickListener(v->setClickBack());

    }

    private void setAutoComplete() {
        //tinh
        ArrayAdapter<String> arrayAdapterTinh = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, listTinh  );
//        arrayAdapterTinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvTinhThanh.setAdapter(arrayAdapterTinh);
        binding.tvTinhThanh.setThreshold(1);
        // quan
        ArrayAdapter<String> arrayAdapterQuan = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, listQuan  );
//        arrayAdapterQuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvQuanHuyen.setAdapter(arrayAdapterQuan);
        binding.tvQuanHuyen.setThreshold(1);
        // phuong
        ArrayAdapter<String> arrayAdapterPhuong = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, listPhuong  );
//        arrayAdapterPhuong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvPhuongXa.setAdapter(arrayAdapterPhuong);
        binding.tvPhuongXa.setThreshold(1);
    }

    private void checkAndAddList() {
        int check = 0;
        for (String s:listTinh) {
            if(binding.tvTinhThanh.getText().toString().equals(s)){
                check = 1;
                break;
            }
        }
        if(check == 0){
            listTinh.add(binding.tvTinhThanh.getText().toString());
        }else{
            check = 0;
        }
        //quan
        for (String s:listQuan) {
            if(binding.tvQuanHuyen.getText().toString().equals(s)){
                check = 1;
                break;
            }
        }
        if(check == 0){
            listQuan.add(binding.tvQuanHuyen.getText().toString());
        }else{
            check = 0;
        }
        // phuong
        for (String s:listPhuong) {
            if(binding.tvPhuongXa.getText().toString().equals(s)){
                check = 1;
                break;
            }
        }
        if(check == 0){
            listPhuong.add(binding.tvPhuongXa.getText().toString());
        }else{
            check = 0;
        }
        setAutoComplete();
    }

    private void getDataList() {
        listTinh = new ArrayList<String>();
        listTinh.add("Hà Nội");
        listTinh.add("Hải Phòng");
        listTinh.add("Thái Bình");
        //
        listQuan = new ArrayList<>();
        listQuan.add("Bắc Từ Liêm");
        listQuan.add("Nam Từ Liêm");
        listQuan.add("Ba Đình");
        listQuan.add("Cầu Giấy");
        //
        listPhuong = new ArrayList<>();
        listPhuong.add("Minh Khai");
        listPhuong.add("Phúc Diễn");
    }

    private void setClickBack() {
        MainActivity main = (MainActivity) this.getContext();
        main.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, new AccountFragment())
                .addToBackStack(null).commit();
    }

    public void processBirthday() {
        DatePickerDialog.OnDateSetListener callBack =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                binding.edtNgaySinh.setText(arg3+"/"+(arg2 + 1) +"/"+arg1);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) ;
        int  year = calendar.get(Calendar.YEAR);
        DatePickerDialog dateDialog=new DatePickerDialog(getContext(), callBack, year, month, day);
        dateDialog.setTitle("Ngày sinh");
        dateDialog.show();
    }

}