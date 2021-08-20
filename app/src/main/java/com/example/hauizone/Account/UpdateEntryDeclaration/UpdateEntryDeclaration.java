package com.example.hauizone.Account.UpdateEntryDeclaration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentEntryDeclarationBinding;

public class UpdateEntryDeclaration extends AppCompatActivity {
    FragmentEntryDeclarationBinding binding;
    BaseDatabase baseDatabase;
    EntryDeclaration entryDeclaration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_entry_declaration);
        baseDatabase = BaseDatabase.getInstance(getBaseContext());
        setUpDisplay();
        binding.updateEntryDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    private void setUpDisplay() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        } else {
            binding.sendEntryDeclaration.setVisibility(View.INVISIBLE);
            binding.updateEntryDeclaration.setVisibility(View.VISIBLE);
            entryDeclaration = (EntryDeclaration) bundle.get("link");
            if (entryDeclaration.getSex().equals("Nam")) {
                binding.rbNam.isChecked();
            } else
                binding.rbNu.isChecked();
            binding.txtGate.setText(entryDeclaration.getGate());
            binding.txtName.setText(entryDeclaration.getName());
            binding.txtDateOfBirth.setText(entryDeclaration.getDateOfBirth());
            binding.txtNationality.setText(entryDeclaration.getNationality());
            binding.txtDateEntry.setText(entryDeclaration.getDate());
            binding.txtProvinceEntry.setText(entryDeclaration.getContactCity());
            binding.txtDistrictEntry.setText(entryDeclaration.getContactDistrict());
            binding.txtTownEntry.setText(entryDeclaration.getContactTown());
            binding.txtAddressEntry.setText(entryDeclaration.getContactAddress());
            binding.txtNumberPhoneEntry.setText(entryDeclaration.getPhoneNumber());
        }

    }


    private void updateData() {
        String sex="";
        if (binding.rbNam.isChecked())
            sex = "Nam";
        else
            sex = "Nữ";
        EntryDeclaration entryDeclaration1=entryDeclaration;
        entryDeclaration1.setSex(sex);
        entryDeclaration1.setGate(binding.txtGate.getText().toString());
        entryDeclaration1.setName(binding.txtName.getText().toString());
        entryDeclaration1.setDateOfBirth(binding.txtDateOfBirth.getText().toString());
        entryDeclaration1.setNationality(binding.txtNationality.getText().toString());
        entryDeclaration1.setDate(binding.txtDateEntry.getText().toString());
        entryDeclaration1.setContactCity(binding.txtProvinceEntry.getText().toString());
        entryDeclaration1.setContactDistrict(binding.txtDistrictEntry.getText().toString());
        entryDeclaration1.setContactTown(binding.txtTownEntry.getText().toString());
        entryDeclaration1.setContactAddress(binding.txtAddressEntry.getText().toString());
        entryDeclaration1.setPhoneNumber(binding.txtNumberPhoneEntry.getText().toString());

        baseDatabase.updateEntry(entryDeclaration1);
        finish();

    }
}