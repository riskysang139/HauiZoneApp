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
        baseDatabase.insertEntry(new EntryDeclaration("Lao B???o","Tr???n Quang Sang"
                ,"13/09/2000","Nam","Vi???t Nam",
                "13/03/2021","Th??i B??nh","H??ng H??",
                "H??ng Nh??n","Khu L??i","0971410156",3));

        baseDatabase.insertEntry(new EntryDeclaration("H???i Ph??ng","Cao Th??? Th???ng"
                ,"22/12/2000","Nam","Vi???t Nam",
                "16/09/2021","V??nh Ph??c","V??nh T?????ng",
                "Ng?? Ki??n","Khu ABC","06554112434",4));

        baseDatabase.insertEntry(new EntryDeclaration("C?? Mau","Nguy???n V??n Th??ng"
                ,"11/06/2000","Nam","Vi???t Nam",
                "16/09/2021","Nam ?????nh","H???i H???u",
                "H??n D???u","Khu Ch??a","074156568",5));
    }

    @Override
    public void OnClick(EntryDeclaration entryDeclaration) {

    }

    @Override
    public void OnLongClick(EntryDeclaration entryDeclaration) {
        EntryDeclaration entryDeclaration1=entryDeclaration;
        AlertDialog alertDialogEntry = new AlertDialog.Builder(this)
                .setTitle("Th??ng b??o")
                .setMessage("B???n c?? ch???c ch???n mu???n xo?? t??? khai n??y ?")
                .setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatabase.deleteEntryByID(entryDeclaration1);
                        updateData();
                        Toast.makeText(getBaseContext() ,"Xo?? th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Xo?? kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
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
