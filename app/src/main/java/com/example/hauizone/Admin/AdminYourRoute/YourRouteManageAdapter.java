package com.example.hauizone.Admin.AdminYourRoute;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.R;
import com.example.hauizone.ScanQR.DataYourRoute;

import java.util.List;

public class YourRouteManageAdapter extends RecyclerView.Adapter<YourRouteManageAdapter.ViewHolder> {
    Context mContext;
    List<DataYourRoute> dataYourRoutes;
    OnClickItemYourRouteManage onClickItemYourRouteManage;

    public YourRouteManageAdapter(Context mContext, List<DataYourRoute> dataYourRoutes, OnClickItemYourRouteManage onClickItemYourRouteManage) {
        this.mContext = mContext;
        this.dataYourRoutes = dataYourRoutes;
        this.onClickItemYourRouteManage = onClickItemYourRouteManage;
    }

    public interface OnClickItemYourRouteManage {
        void onLongClick(DataYourRoute dataYourRoute);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_your_route, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DataYourRoute dataYourRoute = dataYourRoutes.get(position);

        holder.tvID.setText(dataYourRoute.getIdUsername() + "");
        holder.tvName.setText(dataYourRoute.getName());
        holder.tvAddress.setText(dataYourRoute.getAddress());
        holder.tvAddress_go.setText(dataYourRoute.getAddress_go());
        holder.tvAddress_des.setText(dataYourRoute.getAddress_des());
        holder.tvDay_go.setText(dataYourRoute.getDay_go());
        holder.tvDay_des.setText(dataYourRoute.getDay_des());

        holder.tvID.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemYourRouteManage.onLongClick(dataYourRoute);
                return false;
            }
        });
        holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemYourRouteManage.onLongClick(dataYourRoute);
                return false;
            }
        });
        holder.tvAddress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemYourRouteManage.onLongClick(dataYourRoute);
                return false;
            }
        });
        holder.tvAddress_go.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemYourRouteManage.onLongClick(dataYourRoute);
                return false;
            }
        });
        holder.tvAddress_des.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemYourRouteManage.onLongClick(dataYourRoute);
                return false;
            }
        });
        holder.tvDay_go.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemYourRouteManage.onLongClick(dataYourRoute);
                return false;
            }
        });
        holder.tvDay_des.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemYourRouteManage.onLongClick(dataYourRoute);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataYourRoutes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvName, tvAddress, tvAddress_go, tvAddress_des, tvDay_go, tvDay_des;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.account_id);
            tvName = itemView.findViewById(R.id.account_name);
            tvAddress = itemView.findViewById(R.id.account_address);
            tvAddress_go = itemView.findViewById(R.id.account_address_go);
            tvAddress_des = itemView.findViewById(R.id.account_address_des);
            tvDay_go = itemView.findViewById(R.id.account_day_go);
            tvDay_des = itemView.findViewById(R.id.account_day_des);
        }
    }
}
