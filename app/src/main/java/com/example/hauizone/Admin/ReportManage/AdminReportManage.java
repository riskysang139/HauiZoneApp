package com.example.hauizone.Admin.ReportManage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.R;
import com.example.hauizone.Report.Report;
import com.example.hauizone.databinding.ActivityManageReportBinding;

import java.util.ArrayList;

public class AdminReportManage extends AppCompatActivity {
    ActivityManageReportBinding binding;
    ReportManageAdapter reportManageAdapter;
    ArrayList<Report> reports;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_report);
        reportManageAdapter = new ReportManageAdapter(getBaseContext(), reports);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.listReportReceived.setLayoutManager(layoutManager);
        binding.listReportReceived.setAdapter(reportManageAdapter);
    }
}
