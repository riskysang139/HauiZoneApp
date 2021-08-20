package com.example.hauizone.Admin.AdminDomesticAndEntry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivityAdminDomesticAndEntryBinding;
import com.example.hauizone.DomesticDeclaration.DomesticDeclaration;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;

import java.util.ArrayList;

public class AdminDomesticAndEntry extends AppCompatActivity implements RCVEntryAdapter.ClickListener {
    ActivityAdminDomesticAndEntryBinding binding;
    BaseDatabase baseDatabase;
    ArrayList<DomesticDeclaration> declarationArrayList;
    ArrayList<EntryDeclaration> entryDeclarations;
    RCVDomesticAdapter domesticAndEntryAdapter;
    RCVEntryAdapter entryAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_domestic_and_entry);
        baseDatabase = BaseDatabase.getInstance(getBaseContext());


//        declarationArrayList=baseDatabase.getAllDomesTic();
//        domesticAndEntryAdapter=new RCVDomestic(declarationArrayList,getBaseContext());
//        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());
//        binding.rcvListToKhai.setLayoutManager(layoutManager);
//        binding.rcvListToKhai.setAdapter(domesticAndEntryAdapter);

        entryDeclarations = baseDatabase.getAllEntry();
        if(entryDeclarations.size()==0)
        {
            fakeData();
            entryDeclarations=baseDatabase.getAllEntry();
        }
        entryAdapter=new RCVEntryAdapter(entryDeclarations,getBaseContext(),this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.rcvListToKhai.setLayoutManager(layoutManager);
        binding.rcvListToKhai.setAdapter(entryAdapter);
        binding.btnBack.setOnClickListener(v -> finish());

    }

    private void fakeData() {
        baseDatabase.insertEntry(new EntryDeclaration("Lao Bảo","Trần Quang Sang"
                ,"13/09/2000","Nam","Việt Nam",
                "13/03/2021","Thái Bình","Hưng Hà",
                "Hưng Nhân","Khu Lái","0971410156",3));

        baseDatabase.insertEntry(new EntryDeclaration("Hải Phòng","Cao Thế Thắng"
                ,"22/12/2000","Nam","Việt Nam",
                "16/09/2021","Vĩnh Phúc","Vĩnh Tường",
                "Ngũ Kiên","Khu ABC","06554112434",4));

        baseDatabase.insertEntry(new EntryDeclaration("Cà Mau","Nguyễn Văn Thàng"
                ,"11/06/2000","Nam","Việt Nam",
                "16/09/2021","Nam Định","Hải Hậu",
                "Hòn Dấu","Khu Chùa","074156568",5));
    }

    @Override
    public void OnClick(EntryDeclaration entryDeclaration) {

    }

    @Override
    public void OnLongClick(EntryDeclaration entryDeclaration) {
        EntryDeclaration entryDeclaration1=entryDeclaration;
        AlertDialog alertDialogEntry = new AlertDialog.Builder(this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn xoá tờ khai này ?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatabase.deleteEntryByID(entryDeclaration1);
                        updateData();
                        Toast.makeText(getBaseContext() ,"Xoá thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Xoá không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialogEntry.show();
    }
    private void updateData()
    {
        entryDeclarations = baseDatabase.getAllEntry();
        entryAdapter=new RCVEntryAdapter(entryDeclarations,getBaseContext(),this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.rcvListToKhai.setLayoutManager(layoutManager);
        binding.rcvListToKhai.setAdapter(entryAdapter);
    }
}
