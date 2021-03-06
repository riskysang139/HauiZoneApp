package com.example.hauizone.Account.UpdateEntryDeclaration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentEntryDeclarationBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateEntryDeclaration extends AppCompatActivity {
    FragmentEntryDeclarationBinding binding;
    BaseDatabase baseDatabase;
    EntryDeclaration entryDeclaration;
    final Calendar calendar = Calendar.getInstance();
    private int Year = 2000, Month = 0, Day = 1;
    private DatePickerDialog dateEntryPickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_entry_declaration);
        baseDatabase = BaseDatabase.getInstance(getBaseContext());
        setUpDisplay();
        binding.txtDateOfBirth.setInputType(InputType.TYPE_NULL);
        binding.txtDateEntry.setInputType(InputType.TYPE_NULL);
        binding.txtDateOfBirth.setOnClickListener(v -> showDateDialog());
        binding.txtDateEntry.setOnClickListener(v -> showDateEntryDialog());
        setUpAutoCompleteTextView();
        binding.updateEntryDeclaration.setOnClickListener(v -> updateData());
        binding.imgBack.setOnClickListener(v -> finish());
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
            sex = "N???";
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
        Toast.makeText(this,"C???p nh???p th??ng tin th??nh c??ng",Toast.LENGTH_SHORT).show();
        finish();

    }

    private void showDateEntryDialog() {
        Calendar calendar1 = Calendar.getInstance();
        int thisDate = calendar1.get(Calendar.DATE);
        int thisMonth = calendar1.get(Calendar.MONTH);
        int thisYear = calendar1.get(Calendar.YEAR);
        dateEntryPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(year, month-1, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.txtDateEntry.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        }, thisYear, thisMonth, thisDate);
        dateEntryPickerDialog.setTitle("Date Entry");
        dateEntryPickerDialog.show();
    }

    private void showDateDialog() {
        dateEntryPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                calendar.set(Year, Month, Day);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.txtDateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, Year, Month, Day);
        dateEntryPickerDialog.setTitle("Date Of Birth");
        dateEntryPickerDialog.show();
    }

    private void setUpAutoCompleteTextView() {
        String arr[] = {"H?? N???i", "Hu???", "S??i g??n",
                "Th??i B??nh", "B???c Giang", "Nam ?????nh",
                "L??m ?????ng", "Long kh??nh", "H??ng Y??n", "H?? Nam"};


        String arrDistrict[] = {"Ho??n Ki???m", " ?????ng ??a", " Ba ????nh",
                "Hai B?? Tr??ng", " Ho??ng Mai", " Thanh Xu??n", "Long Bi??n", " Nam T??? Li??m", "B???c T??? Li??m", "T??y H???", " C???u Gi???y", " H?? ????ng"
                , "Th??nh ph??? Th??i B??nh", "????ng H??ng", "Huy???n H??ng H??", "Huy???n Ki???n X????ng", "Huy???n Qu???nh Ph???"};

        String arrTown[] = {
                "B???c S??n", "H??ng H??", "Canh T??n", "Ch?? H??a", " H??ng H??", "H??ng Nh??n", " H??ng B??ng",
                "H??ng Bu???m", "H??ng ????o", " H??ng Gai", "H??ng M??", " H??ng Tr???ng", "L?? Th??i T???", " Phan Chu Trinh", " Ph??c T??n", " Tr???n H??ng ?????o", "Tr??ng Ti???n"};

        String arrGate[] = {"M??ng C??i", "H???u Ngh???", " La Lay", " B??? Y", "G??nh ??a",
                "  L??? Thanh", " Hoa L??", " Xa M??t", " M???c B??i", "Dinh B??", " Th?????ng Ph?????c", " V??nh X????ng", " T???nh Bi??n", " H?? Ti??n", " B??nh Hi???p"};

        String arrCountry[] = {"??c", "New Zealand", "Singapore", " Vi???t Nam", " Nh???t B???n", " Hong Kong", " H??n Qu???c"};

        binding.txtGate.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrGate));
        binding.txtProvinceEntry.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arr));
        binding.txtDistrictEntry.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arrDistrict));
        binding.txtTownEntry.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arrTown));

        binding.txtNationality.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arrCountry));
    }
}