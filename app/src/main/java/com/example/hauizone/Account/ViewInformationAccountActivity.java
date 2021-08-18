package com.example.hauizone.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivityViewInformationAccountBinding;

public class ViewInformationAccountActivity extends AppCompatActivity {

    ActivityViewInformationAccountBinding binding;

    User mUser;
    BaseDatabase mBaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_information_account);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_information_account);

        setEvent();
    }

    private void setEvent() {

        mUser = new User();
        mBaseDatabase = new BaseDatabase(this);



        Intent  intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        if(id != -1){
            try {
                mUser = mBaseDatabase.getUserById(id);
                if(mUser != null){
                    fillDataToView();
                }
            }catch(Exception e){
                Log.e("ViewInfomationAccount", e.toString());
            }
        }


        binding.imgBack.setOnClickListener(v -> finish());

    }

    private void fillDataToView() {
        binding.tvHoTen.setText(mUser.getName());
        binding.tvNgaySinh.setText(mUser.getDateOfBirth());
        binding.tvGioiTinh.setText(mUser.getGender());
        binding.tvTinhThanh.setText(mUser.getUserProvince());
        binding.tvQuanHuyen.setText(mUser.getUserDistrict());
        binding.tvPhuongXa.setText(mUser.getUserWard());
        binding.tvSoNha.setText(mUser.getUserStreet());
        binding.tvSdt.setText(mUser.getPhoneNumber());
        binding.tvEmail.setText(mUser.getEmail());
        binding.tvDichBenh.setText(mUser.getEpidemic());
    }
}