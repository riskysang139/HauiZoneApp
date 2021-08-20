package com.example.hauizone.Account.UpdateEntryDeclaration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.Admin.AdminDomesticAndEntry.RCVDomesticAdapter;
import com.example.hauizone.Admin.AdminDomesticAndEntry.RCVEntryAdapter;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.DomesticDeclaration.DomesticDeclaration;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivityAdminDomesticAndEntryBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowListEntryDeclaration extends Fragment implements RCVEntryAdapter.ClickListener {
    ActivityAdminDomesticAndEntryBinding binding;
    BaseDatabase baseDatabase;
    ArrayList<EntryDeclaration> entryDeclarations;
    RCVEntryAdapter entryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("sang","on create view");
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_admin_domestic_and_entry, container, false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.e("sang","on resum");
        baseDatabase = BaseDatabase.getInstance(getContext());
        entryDeclarations = baseDatabase.getAllEntry();
        entryAdapter=new RCVEntryAdapter(entryDeclarations,getContext(),this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvListToKhai.setLayoutManager(layoutManager);
        binding.rcvListToKhai.setAdapter(entryAdapter);
        if(entryDeclarations.size()==0)
        {
            fakeData();
            updateData();
        }

    }
    private void fakeData() {
        baseDatabase.insertEntry(new EntryDeclaration("Lao Bảo","Trần Quang Sang","13/09/2000","Nam","Việt Nam","13/03/2021","Thái Bình","Hưng Hà","Hưng Nhân","Khu Lái","0971410156",1));
    }
    private void updateData()
    {
        entryDeclarations = baseDatabase.getAllEntry();
        entryAdapter=new RCVEntryAdapter(entryDeclarations,getContext(),this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvListToKhai.setLayoutManager(layoutManager);
        binding.rcvListToKhai.setAdapter(entryAdapter);
    }

    @Override
    public void OnClick(EntryDeclaration entryDeclaration) {
        onClicktoUpdate(entryDeclaration);
    }

    @Override
    public void OnLongClick(EntryDeclaration entryDeclaration) {
        EntryDeclaration entryDeclaration1=entryDeclaration;
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn xoá tờ khai này ?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatabase.deleteEntryByID(entryDeclaration1);
                        updateData();
                        Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Xoá không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }
    public void onClicktoUpdate(EntryDeclaration entryDeclaration) {
        Intent intent = new Intent(getActivity(), UpdateEntryDeclaration.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("link", (Serializable) entryDeclaration);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }


}
