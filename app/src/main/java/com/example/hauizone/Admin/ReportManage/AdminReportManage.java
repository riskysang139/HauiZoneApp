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
import com.example.hauizone.MainActivity;
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
            fakeData();
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
                .setTitle("Th??ng b??o")
                .setMessage("B???n c?? ch???c ch???n mu???n xo?? th??ng tin ph???n ??nh n??y ?")
                .setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatabase.deleteReport(rp);
                        updateData();
                        Toast.makeText(getBaseContext(), "Xo?? th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Xo?? kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }
    private void fakeData() {
        baseDatabase.insertReport(new Report("20/06/2021", "Cao Th??? Th???ng",
                "0974145648","H???i Ph??ng","L?? Ch??n","Ho??ng C???m",
                "Ho??ng Minh Gi??m","C?? ng?????i tr??? v??? t??? v??ng d???ch","ok",4));

        baseDatabase.insertReport(new Report("22/08/2021", "Cao Th??? Th???ng",
                "0987456789","V??nh Ph??c","V??nh T?????ng","Ng?? Ki??n",
                "L??ng M???c","C?? ng?????i ti???p x??c v???i ng?????i m???c b???nh","ok",3));
        baseDatabase.insertReport(new Report("13/11/2021", "Cao Th??? Th???ng",
                "0974145648","H?? N???i","B???c T??? Li??m","Minh Khai",
                "Nguy??n X??","C?? ng?????i tr??? v??? t??? v??ng d???ch","ok",2));

    }
}
