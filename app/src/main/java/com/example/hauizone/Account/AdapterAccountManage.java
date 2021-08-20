package com.example.hauizone.Account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hauizone.R;

import java.util.List;

public class AdapterAccountManage extends BaseAdapter {
    List<User> userList;
    Context context;

    public AdapterAccountManage(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userList.get(position).getUserId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(convertView.getContext()).inflate(R.layout.item_manage_account,parent,false);

        //
        TextView name, birth, province, phone, epidemic;

        name = view.findViewById(R.id.account_name);
        birth = view.findViewById(R.id.account_dateOfBirth);
        province = view.findViewById(R.id.account_province);
        phone = view.findViewById(R.id.account_phone);
        epidemic = view.findViewById(R.id.account_epidemic);

        name.setText(userList.get(position).getName());
        birth.setText(userList.get(position).getDateOfBirth());
        province.setText(userList.get(position).getUserProvince());
        phone.setText(userList.get(position).getPhoneNumber());
        epidemic.setText(userList.get(position).getEpidemic());
        return view;
    }
}
