package com.example.hauizone.Admin.AdminDomesticAndEntry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.R;
import com.example.hauizone.domesticDeclaration.DomesticDeclaration;

import java.util.ArrayList;

public class RCVDomesticAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<DomesticDeclaration> arrayList;
    Context context;

    public RCVDomesticAdapter(ArrayList<DomesticDeclaration> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_domestic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        DomesticDeclaration domesticDeclaration=arrayList.get(position);
        viewHolder.txtAddress.setText("Số nhà, tổ / thôn : "+domesticDeclaration.getContactAddress() +" Phường / Xã :" + domesticDeclaration.getContactTown() +
                " Quận / Huyện :" +domesticDeclaration.getContactDistrict() + " Tỉnh Thành :" +domesticDeclaration.getContactCity());
        viewHolder.txtStatus.setText(domesticDeclaration.getCovidContact());
        viewHolder.txtPhoneNumber.setText(domesticDeclaration.getNumberPhone());
        viewHolder.txtUserName.setText(domesticDeclaration.getName());
        viewHolder.txtVehicle.setText(domesticDeclaration.getVehicle());
        viewHolder.txtNoiDi.setText(domesticDeclaration.getDeparture());
        viewHolder.txtNoiDen.setText(domesticDeclaration.getDestination());
        viewHolder.txtdateOfBirh.setText(domesticDeclaration.getDateOfBirth());
        viewHolder.txtNumberPassport.setText(domesticDeclaration.getNumberPassport());
        viewHolder.txtSex.setText(domesticDeclaration.getSex());
        viewHolder.txtStatus.setText(domesticDeclaration.getSympton());
        viewHolder.txtKhaiHo.setText(domesticDeclaration.getCkKH());
        viewHolder.txtPhanLoai.setText(domesticDeclaration.getCovidContact());
        viewHolder.txtID.setText(domesticDeclaration.getIdUsername()+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserName,txtKhaiHo,txtVehicle,txtNoiDi,txtNoiDen,txtName,txtdateOfBirh,txtNumberPassport,txtSex,txtAddress,txtPhoneNumber,txtStatus,txtPhanLoai,txtID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAddress=itemView.findViewById(R.id.rcvtxtAddress);
            txtUserName=itemView.findViewById(R.id.rcv_txt_username);
            txtKhaiHo=itemView.findViewById(R.id.rcv_txtKhaiHo);
            txtVehicle=itemView.findViewById(R.id.rcv_vehicle);
            txtNoiDi=itemView.findViewById(R.id.rcvtxtNoiDi);
            txtNoiDen=itemView.findViewById(R.id.rcv_noiden);
            txtdateOfBirh=itemView.findViewById(R.id.rcv_dateOfBirth);
            txtNumberPassport=itemView.findViewById(R.id.rcvtxtNumberPassport);
            txtSex=itemView.findViewById(R.id.rcvtxtSex);
            txtPhoneNumber=itemView.findViewById(R.id.rcvtxtPhoneNumber);
            txtStatus=itemView.findViewById(R.id.rcvtxtNoiDung);
            txtPhanLoai=itemView.findViewById(R.id.rcvtxtPhanLoai);
            txtID=itemView.findViewById(R.id.rcv_txt_userid);
        }
    }
}
