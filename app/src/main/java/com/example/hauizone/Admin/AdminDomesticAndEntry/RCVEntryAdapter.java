package com.example.hauizone.Admin.AdminDomesticAndEntry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.R;
import com.example.hauizone.entryDeclaration.EntryDeclaration;

import java.util.List;

public class RCVEntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<EntryDeclaration> arrList;
    Context context;

    public RCVEntryAdapter(List<EntryDeclaration> arrList, Context context) {
        this.arrList = arrList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_entry,parent,false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EntryViewHolder viewHolder= (EntryViewHolder) holder;
        EntryDeclaration entryDeclaration=arrList.get(position);
        viewHolder.txtIDUser.setText(entryDeclaration.getIdUser()+"");
        viewHolder.txtGate.setText(entryDeclaration.getGate());
        viewHolder.txtName.setText(entryDeclaration.getName());
        viewHolder.txtPhoneNumber.setText(entryDeclaration.getPhoneNumber());
        viewHolder.txtAddress.setText("Số nhà, tổ / thôn : "+entryDeclaration.getContactAddress() +" Phường / Xã :" + entryDeclaration.getContactTown() +
                " Quận / Huyện :" +entryDeclaration.getContactDistrict() + " Tỉnh Thành :" +entryDeclaration.getContactCity());
        viewHolder.txtDate.setText(entryDeclaration.getDate());
        viewHolder.txtDateOB.setText(entryDeclaration.getDateOfBirth());
        viewHolder.txtSex.setText(entryDeclaration.getSex());
        viewHolder.txtNationality.setText(entryDeclaration.getNationality());
    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }
    public class EntryViewHolder extends RecyclerView.ViewHolder{
        TextView txtGate,txtName,txtDateOB,txtSex,txtNationality,txtDate,txtAddress,txtPhoneNumber,txtIDUser;
        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIDUser=itemView.findViewById(R.id.rcv_Entry_txt_userid);
            txtGate=itemView.findViewById(R.id.rcv_txtGate);
            txtName=itemView.findViewById(R.id.rcv_txt_username);
            txtDateOB=itemView.findViewById(R.id.rcv_Entry_dateOfBirth);
            txtSex=itemView.findViewById(R.id.rcvEntrySex);
            txtNationality=itemView.findViewById(R.id.rcv_nationality);
            txtDate=itemView.findViewById(R.id.rcvEntry_Date);
            txtAddress=itemView.findViewById(R.id.rcv_Entry_txtAddress);
            txtPhoneNumber=itemView.findViewById(R.id.rcv_Entry_txtPhoneNumber);
        }
    }
}
