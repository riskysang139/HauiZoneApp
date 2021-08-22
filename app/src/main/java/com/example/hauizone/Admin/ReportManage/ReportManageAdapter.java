package com.example.hauizone.Admin.ReportManage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.Admin.AdminDomesticAndEntry.RCVEntryAdapter;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.Report.Report;

import java.util.List;

public class ReportManageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Report> reportList;
    ClickListenerEvent clickListenerEvent;

    public ReportManageAdapter(Context context, List<Report> reportList,ClickListenerEvent clickListenerEvent) {
        this.context = context;
        this.reportList = reportList;
        this.clickListenerEvent = clickListenerEvent;
    }
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ReportManageViewHolder viewHolder= (ReportManageViewHolder) holder;
        Report report=reportList.get(position);
        viewHolder.idReported.setText(report.getIdReport()+"");
        BaseDatabase baseDatabase = BaseDatabase.getInstance(context);
        String username = baseDatabase.getUserById(report.getIdUser()).getUserName();
        viewHolder.userNameReported.setText(username+"");
        viewHolder.timeDetectedReported.setText(report.getTimeDetectReport()+"");
        viewHolder.contentTypeReported.setText(report.getTypeReport());
        viewHolder.placeDetectedReported.setText(report.getStreet()+","+report.getWard()+","+report.getDistrict()+","+report.getProvince());
        viewHolder.otherContentReported.setText(report.getContentReport());
    }

    @Override
    public int getItemCount() {
        if(reportList == null){
            return 0;
        }
        return reportList.size();
    }


    public class ReportManageViewHolder extends RecyclerView.ViewHolder{
        TextView idReported,userNameReported,timeDetectedReported,placeDetectedReported,contentTypeReported,otherContentReported;
        LinearLayout layout;
        public ReportManageViewHolder(@NonNull View itemView) {
            super(itemView);
            idReported = itemView.findViewById(R.id.idReported);
            userNameReported = itemView.findViewById(R.id.userNameReported);
            timeDetectedReported = itemView.findViewById(R.id.timeDetectedReported);
            placeDetectedReported = itemView.findViewById(R.id.placeDetectedReported);
            contentTypeReported = itemView.findViewById(R.id.contentTypeReported);
            otherContentReported = itemView.findViewById(R.id.otherContentReported);
            layout = itemView.findViewById(R.id.reportItemLayout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListenerEvent.OnClick(reportList.get(getAdapterPosition()));
                }
            });
            layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListenerEvent.OnLongClick(reportList.get(getAdapterPosition()));
                    return false;
                }
            });
        }
    }
    public interface ClickListenerEvent
    {
        void OnClick(Report report);
        void OnLongClick(Report report);
    }
}
