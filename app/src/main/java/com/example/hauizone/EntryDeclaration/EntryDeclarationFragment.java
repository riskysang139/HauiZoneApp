package com.example.hauizone.EntryDeclaration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentEntryDeclarationBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EntryDeclarationFragment extends Fragment {
    FragmentEntryDeclarationBinding binding;
    final Calendar calendar = Calendar.getInstance();
    private int Year = 2000, Month = 0, Day = 1;
    private DatePickerDialog datePickerDialog;
    private BaseDatabase baseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry_declaration, container, false);
        View view = binding.getRoot();
        baseDatabase = BaseDatabase.getInstance(getContext());
        setUpAutoCompleteTextView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.txtDateOfBirth.setInputType(InputType.TYPE_NULL);
        binding.txtDateEntry.setInputType(InputType.TYPE_NULL);
        binding.txtDateOfBirth.setOnClickListener(v -> showDateDialog());
        binding.txtDateEntry.setOnClickListener(v -> showDateEntryDialog());

        binding.sendEntryDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertEntry();

            }
        });
    }

    private void showDateEntryDialog() {
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                calendar.set(Year, Month, Day);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.txtDateEntry.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, Year, Month, Day);
        datePickerDialog.setTitle("Date Entry");
        datePickerDialog.show();
    }

    private void showDateDialog() {
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
        datePickerDialog.setTitle("Date Of Birth");
        datePickerDialog.show();
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

        binding.txtGate.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrGate));
        binding.txtProvinceEntry.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arr));
        binding.txtDistrictEntry.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrDistrict));
        binding.txtTownEntry.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrTown));

        binding.txtNationality.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrCountry));
    }

    private void insertEntry() {
        String sex = "";
        if (binding.rbNam.isChecked())
            sex = "Nam";
        else
            sex = "Nữ";
        baseDatabase.insertEntry(new EntryDeclaration(
                binding.txtGate.getText().toString(),
                binding.txtName.getText().toString(),
                binding.txtDateOfBirth.getText().toString(),
                sex,
                binding.txtNationality.getText().toString(),
                binding.txtDateEntry.getText().toString(),
                binding.txtProvinceEntry.getText().toString(),
                binding.txtDistrictEntry.getText().toString(),
                binding.txtTownEntry.getText().toString(),
                binding.txtAddressEntry.getText().toString(),
                binding.txtNumberPhoneEntry.getText().toString(),
                MainActivity.INDEX
        ));
    }
}
