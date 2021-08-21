package com.example.hauizone.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hauizone.Admin.AccountManage.ManageAccountActivity;
import com.example.hauizone.Admin.AdminDomesticAndEntry.AdminDomesticAndEntry;
import com.example.hauizone.Admin.AdminNotifi.AdminNotifiActivity;
import com.example.hauizone.Admin.ReportManage.AdminReportManage;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivityAdminBinding;


public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_admin);
        binding.btnEntryDeclare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, AdminDomesticAndEntry.class));
            }
        });
        binding.btnNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, AdminNotifiActivity.class));
            }
        });
        binding.btnManageReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminReportManage.class));
            }
        });

        binding.btnManageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ManageAccountActivity.class));
            }
        });
    }
}