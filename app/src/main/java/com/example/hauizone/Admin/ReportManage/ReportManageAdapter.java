package com.example.hauizone.Admin.ReportManage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.R;
import com.example.hauizone.Report.Report;

import java.util.List;

public class ReportManageAdapter extends RecyclerView.Adapter<ReportManageAdapter.ReportManageViewHolder>{
    Context context;
    List<Report> reportList;

    public ReportManageAdapter(Context context, List<Report> reportList) {
        this.context = context;
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ReportManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_manage, parent, false);
        return new ReportManageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(reportList == null){
            return 0;
        }
        return reportList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ReportManageViewHolder holder, int position) {

    }

    public class ReportManageViewHolder extends RecyclerView.ViewHolder{
        TextView idUserReported,userNameReported,timeDetectedReported,placeDetectedReported,contentTypeReported,otherContentReported;
        public ReportManageViewHolder(@NonNull View itemView) {
            super(itemView);
            idUserReported = itemView.findViewById(R.id.idUserReported);
            userNameReported = itemView.findViewById(R.id.userNameReported);
            timeDetectedReported = itemView.findViewById(R.id.timeDetectedReported);
            placeDetectedReported = itemView.findViewById(R.id.placeDetectedReported);
            contentTypeReported = itemView.findViewById(R.id.contentTypeReported);
            otherContentReported = itemView.findViewById(R.id.otherContentReported);


        }
    }
}
