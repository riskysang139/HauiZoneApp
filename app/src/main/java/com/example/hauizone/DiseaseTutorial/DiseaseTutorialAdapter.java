package com.example.hauizone.DiseaseTutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.ScanQR.ItemClickListener;
import com.example.hauizone.R;

import java.util.ArrayList;

public class DiseaseTutorialAdapter extends RecyclerView.Adapter<DiseaseTutorialAdapter.ViewHoder> {
    ArrayList<DiseaseTutorial> arrayList;
    ItemClickListener itemClickListener;

    public DiseaseTutorialAdapter(ArrayList<DiseaseTutorial> arrayList, ItemClickListener itemClickListener) {
        this.arrayList = arrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease_tutorial,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        DiseaseTutorial diseaseTutorial=arrayList.get(position);
        holder.imageView.setImageResource(diseaseTutorial.getImage());
        holder.txtTitle.setText(diseaseTutorial.getTitle());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.OnClick(position);
            }
        });
        holder.backgroundMain.setBackgroundColor(diseaseTutorial.getBackgroundMain());
        holder.backgroundImage.setBackgroundColor(diseaseTutorial.getBackgroundImage());
    }

    @Override
    public int getItemCount() {
        if(arrayList.size()>0)
            return arrayList.size();
        else
            return 0;

    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imageView;
        RelativeLayout layoutItem,backgroundMain,backgroundImage;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.title_tutorial);
            imageView=itemView.findViewById(R.id.image_tutorial);
            layoutItem=itemView.findViewById(R.id.layoutItem);
            backgroundImage=itemView.findViewById(R.id.background_image);
            backgroundMain=itemView.findViewById(R.id.background_main);
        }
    }
}

