package com.example.hauizone.EntryDeclaration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

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
        Calendar calendar1 = Calendar.getInstance();
        int thisDate = calendar1.get(Calendar.DATE);
        int thisMonth = calendar1.get(Calendar.MONTH);
        int thisYear = calendar1.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(year, month-1, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.txtDateEntry.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        }, thisYear, thisMonth, thisDate);
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
            sex = "N???";
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
        Toast.makeText(getActivity(),"Khai b??o n???i ?????a th??nh c??ng",Toast.LENGTH_SHORT).show();
    }
}
