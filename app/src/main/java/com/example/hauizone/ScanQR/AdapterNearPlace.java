package com.example.hauizone.ScanQR;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hauizone.R;

import java.util.List;

public class AdapterNearPlace extends RecyclerView.Adapter<AdapterNearPlace.ViewHolder> {
    Context mContext;
    List<DataNearPlace> dataNearPlaces;

    public AdapterNearPlace(Context mContext, List<DataNearPlace> dataNearPlaces) {
        this.mContext = mContext;
        this.dataNearPlaces = dataNearPlaces;
    }

    @NonNull
    @Override
    public AdapterNearPlace.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_fragment_qr_nearplace,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNearPlace.ViewHolder holder, int position) {

        DataNearPlace dataNearPlace = dataNearPlaces.get(position);
        if(dataNearPlace != null){
            Glide.with(mContext).load(dataNearPlace.getImgNearPlace()).into(holder.imgNearPlace);
            holder.tvNearPlace.setText(dataNearPlace.getNameNearPlace());
            holder.tvFar.setText(dataNearPlace.getDistance());
        }
    }

    @Override
    public int getItemCount() {
        return dataNearPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNearPlace;
        TextView tvNearPlace;
        TextView tvFar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNearPlace = itemView.findViewById(R.id.imgNearPlace);
            tvNearPlace = itemView.findViewById(R.id.tvNearPlace);
            tvFar = itemView.findViewById(R.id.tvFar);
        }
    }
}
