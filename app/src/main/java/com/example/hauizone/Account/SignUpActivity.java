package com.example.hauizone.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivitySignUpBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    BaseDatabase mBaseDatabase;
    List<String> listTinh, listQuan, listPhuong ;
    String []dichBenh = {"F0", "F1", "F2", "F3", "F4", "Không mắc bệnh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        setEvent();
    }

    private void setEvent() {

        // set data spinner tinh hinh dich benh
        setSpinnerTinhTrang();
        // data autocomplete tinh/huyen/xa
        getDataList();
        setAutoComplete();
        //cap nhat
        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndAddList();
                insertDataToDatabase();
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
        binding.imgBack.setOnClickListener(v->finish());
    }

    private void insertDataToDatabase() {
        mBaseDatabase = new BaseDatabase(this);

        User user = new User();

        user.setUserName(binding.edtUsername.getText().toString());
        user.setPassword(binding.edtPassword.getText().toString());

        user.setName(binding.edtHoTen.getText().toString());
        user.setDateOfBirth(binding.edtNgaySinh.getText().toString());

        if(binding.rbNam.isChecked()){
            user.setGender(binding.rbNam.getText().toString());
        }else{
            user.setGender(binding.rbNu.getText().toString());
        }

        user.setUserProvince(binding.tvTinhThanh.getText().toString());
        user.setUserDistrict(binding.tvQuanHuyen.getText().toString());
        user.setUserWard(binding.tvPhuongXa.getText().toString());
        user.setUserStreet(binding.edtSoNha.getText().toString());
        user.setPhoneNumber(binding.edtSDT.getText().toString());
//        user.setEmail(binding.edtEmail.getText().toString());
        user.setEpidemic(binding.spDichbenh.getSelectedItem().toString());
        user.setFlag(1);

        long check = -1;
        check = mBaseDatabase.insertUser(user);
        if(check != -1){
            Toast.makeText(this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Đăng ký tài khoản thất bại!", Toast.LENGTH_SHORT).show();
        }


    }

    private void setSpinnerTinhTrang() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, dichBenh);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spDichbenh.setAdapter(arrayAdapter);
    }

    private void setAutoComplete() {
        //tinh
        ArrayAdapter<String> arrayAdapterTinh = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listTinh  );
//        arrayAdapterTinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvTinhThanh.setAdapter(arrayAdapterTinh);
        binding.tvTinhThanh.setThreshold(1);
        // quan
        ArrayAdapter<String> arrayAdapterQuan = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listQuan  );
//        arrayAdapterQuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvQuanHuyen.setAdapter(arrayAdapterQuan);
        binding.tvQuanHuyen.setThreshold(1);
        // phuong
        ArrayAdapter<String> arrayAdapterPhuong = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listPhuong  );
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

//    private void setClickBack() {
//        MainActivity main = (MainActivity) this.this;
//        main.getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.mainFragment, new AccountFragment())
//                .addToBackStack(null).commit();
//    }

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
        DatePickerDialog dateDialog=new DatePickerDialog(this, callBack, year, month, day);
        dateDialog.setTitle("Ngày sinh");
        dateDialog.show();
    }

}