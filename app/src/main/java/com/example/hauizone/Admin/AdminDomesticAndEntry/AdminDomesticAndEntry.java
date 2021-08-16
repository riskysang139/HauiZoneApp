package com.example.hauizone.Admin.AdminDomesticAndEntry;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivityAdminDomesticAndEntryBinding;
import com.example.hauizone.domesticDeclaration.DomesticDeclaration;
import com.example.hauizone.entryDeclaration.EntryDeclaration;

import java.util.ArrayList;

public class AdminDomesticAndEntry extends AppCompatActivity {
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
        if(entryDeclarations.size()==1)
        {
            fakeData();
        }
        entryAdapter=new RCVEntryAdapter(entryDeclarations,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.rcvListToKhai.setLayoutManager(layoutManager);
        binding.rcvListToKhai.setAdapter(entryAdapter);

    }

    private void fakeData() {
        baseDatabase.insertEntry(new EntryDeclaration("Lao Bảo","Trần Quang Sang","13/09/2000","Nam","Việt Nam","13/03/2021","Thái Bình","Hưng Hà","Hưng Nhân","Khu Lái","0971410156",1));
    }

}
