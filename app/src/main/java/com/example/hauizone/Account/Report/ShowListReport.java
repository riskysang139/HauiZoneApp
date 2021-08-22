package com.example.hauizone.Account.Report;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.Account.UpdateEntryDeclaration.UpdateEntryDeclaration;
import com.example.hauizone.Admin.AdminDomesticAndEntry.RCVEntryAdapter;
import com.example.hauizone.Admin.ReportManage.ReportManageAdapter;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.Report.Report;
import com.example.hauizone.databinding.ActivityManageReportBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowListReport extends Fragment implements ReportManageAdapter.ClickListenerEvent {
    ActivityManageReportBinding binding;
    BaseDatabase baseDatabase;
    ArrayList<Report> reportsList;
    ReportManageAdapter reportManageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("thang","on create view");
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_manage_report, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        baseDatabase = BaseDatabase.getInstance(getContext());
        reportsList = baseDatabase.getAllReportWithUser(MainActivity.INDEX);
        if(reportsList.size()==0)
        {
            reportsList = baseDatabase.getAllReport();
        }
        reportManageAdapter=new ReportManageAdapter(getContext(),reportsList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.listReportReceived.setLayoutManager(layoutManager);
        binding.listReportReceived.setAdapter(reportManageAdapter);
    }
    private void updateData()
    {
        reportsList = baseDatabase.getAllReportWithUser(MainActivity.INDEX);
        reportManageAdapter=new ReportManageAdapter(getContext(),reportsList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.listReportReceived.setLayoutManager(layoutManager);
        binding.listReportReceived.setAdapter(reportManageAdapter);
    }

    @Override
    public void OnClick(Report report) {
        onClicktoUpdate(report);
    }

    @Override
    public void OnLongClick(Report report) {
        Report rp=report;
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn xoá thông tin phản ánh này ?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatabase.deleteReport(rp);
                        updateData();
                        Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Xoá không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();

    }
    private void onClicktoUpdate(Report report) {
        Intent intent = new Intent(getActivity(), UpdateReport.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("report", (Serializable) report);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }
}
