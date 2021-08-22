package com.example.hauizone.Admin.ReportManage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.Admin.AdminDomesticAndEntry.RCVEntryAdapter;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.R;
import com.example.hauizone.Report.Report;
import com.example.hauizone.databinding.ActivityManageReportBinding;

import java.util.ArrayList;

public class AdminReportManage extends AppCompatActivity implements ReportManageAdapter.ClickListenerEvent {
    ActivityManageReportBinding binding;
    ReportManageAdapter reportManageAdapter;
    BaseDatabase baseDatabase;
    ArrayList<Report> reportsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_report);
        baseDatabase = BaseDatabase.getInstance(getBaseContext());
        reportsList = baseDatabase.getAllReport();
        if(reportsList.size()==0)
        {
//            fakeData();
            reportsList=baseDatabase.getAllReport();
        }
        reportManageAdapter=new ReportManageAdapter(getBaseContext(),reportsList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.listReportReceived.setLayoutManager(layoutManager);
        binding.listReportReceived.setAdapter(reportManageAdapter);
        binding.btnBack.setOnClickListener(v -> finish());
    }
    private void updateData()
    {
        reportsList = baseDatabase.getAllReport();
        reportManageAdapter=new ReportManageAdapter(getBaseContext(),reportsList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.listReportReceived.setLayoutManager(layoutManager);
        binding.listReportReceived.setAdapter(reportManageAdapter);
    }

    @Override
    public void OnClick(Report report) {

    }

    @Override
    public void OnLongClick(Report report) {
        Report rp=report;
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn xoá thông tin phản ánh này ?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatabase.deleteReport(rp);
                        updateData();
                        Toast.makeText(getBaseContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Xoá không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }

}
