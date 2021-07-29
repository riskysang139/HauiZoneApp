package com.example.hauizone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterYourRoute extends RecyclerView.Adapter<AdapterYourRoute.ViewHolder> {
    Context mContext;
    List<DataYourRoute> dataYourRoutes;

    public AdapterYourRoute(Context mContext, List<DataYourRoute> dataYourRoutes) {
        this.mContext = mContext;
        this.dataYourRoutes = dataYourRoutes;
    }

    @NonNull
    @Override
    public AdapterYourRoute.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_fragment_qr_yourroute,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterYourRoute.ViewHolder holder, int position) {

        DataYourRoute dataYourRoute = dataYourRoutes.get(position);
        if(dataYourRoute != null){

            holder.tvRoute.setText(dataYourRoute.getAddress_des());
            holder.tvTime.setText(dataYourRoute.getDay_go() + " => "+dataYourRoute.getDay_des());
        }
    }

    @Override
    public int getItemCount() {
        return dataYourRoutes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoute;
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRoute = itemView.findViewById(R.id.tvRoute);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
