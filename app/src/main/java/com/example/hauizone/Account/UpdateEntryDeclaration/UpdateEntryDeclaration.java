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
        Toast.makeText(this,"Cập nhập thông tin thành công",Toast.LENGTH_SHORT).show();
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
        String arr[] = {"Hà Nội", "Huế", "Sài gòn",
                "Thái Bình", "Bắc Giang", "Nam Định",
                "Lâm đồng", "Long khánh", "Hưng Yên", "Hà Nam"};


        String arrDistrict[] = {"Hoàn Kiếm", " Đống Đa", " Ba Đình",
                "Hai Bà Trưng", " Hoàng Mai", " Thanh Xuân", "Long Biên", " Nam Từ Liêm", "Bắc Từ Liêm", "Tây Hồ", " Cầu Giấy", " Hà Đông"
                , "Thành phố Thái Bình", "Đông Hưng", "Huyện Hưng Hà", "Huyện Kiến Xương", "Huyện Quỳnh Phụ"};

        String arrTown[] = {
                "Bắc Sơn", "Hưng Hà", "Canh Tân", "Chí Hòa", " Hưng Hà", "Hưng Nhân", " Hàng Bông",
                "Hàng Buồm", "Hàng Đào", " Hàng Gai", "Hàng Mã", " Hàng Trống", "Lý Thái Tổ", " Phan Chu Trinh", " Phúc Tân", " Trần Hưng Đạo", "Tràng Tiền"};

        String arrGate[] = {"Móng Cái", "Hữu Nghị", " La Lay", " Bờ Y", "Gánh Đa",
                "  Lệ Thanh", " Hoa Lư", " Xa Mát", " Mộc Bài", "Dinh Bà", " Thường Phước", " Vĩnh Xương", " Tịnh Biên", " Hà Tiên", " Bình Hiệp"};

        String arrCountry[] = {"Úc", "New Zealand", "Singapore", " Việt Nam", " Nhật Bản", " Hong Kong", " Hàn Quốc"};

        binding.txtGate.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrGate));
        binding.txtProvinceEntry.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arr));
        binding.txtDistrictEntry.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arrDistrict));
        binding.txtTownEntry.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arrTown));

        binding.txtNationality.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, arrCountry));
    }
}