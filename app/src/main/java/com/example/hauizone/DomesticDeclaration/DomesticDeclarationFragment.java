package com.example.hauizone.DomesticDeclaration;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.example.hauizone.Admin.AdminDomesticAndEntry.AdminDomesticAndEntry;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentDomesticDeclarationBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DomesticDeclarationFragment extends Fragment {
    FragmentDomesticDeclarationBinding binding;
    final Calendar calendar = Calendar.getInstance();
    int Year = calendar.get(Calendar.YEAR);
    int Month = calendar.get(Calendar.MONTH);
    int Day = calendar.get(Calendar.DATE);
    ArrayList<String> spinnerVehicles = null;
    BaseDatabase database;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_domestic_declaration, container, false);
        View view = binding.getRoot();
        database=BaseDatabase.getInstance(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.btnSendDomestic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDomestic();
                startActivity(new Intent(getActivity(),AdminDomesticAndEntry.class));
            }
        });
        setDatimeDialog();
        setSpinner();
        setAutoCompleteStartEnd();
    }

    private void setDatimeDialog() {
        binding.dateOfBirth.setInputType(InputType.TYPE_NULL);
        binding.dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                calendar.set(Year,Month,Day);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.dateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, Year, Month, Day);

        datePickerDialog.show();
    }
    private void setSpinner(){
        ArrayAdapter spinnerVehicleAdapter = null;
        spinnerVehicles = new ArrayList<>();
        spinnerVehicles.add("M??y bay");
        spinnerVehicles.add("Xe g???n m??y");
        spinnerVehicles.add("T??u ho???");
        spinnerVehicleAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,spinnerVehicles);
        binding.spinnerVehicles.setAdapter(spinnerVehicleAdapter);
    }
    private void setAutoCompleteStartEnd(){
        String arr[] = {"H?? N???i", "Hu???", "S??i g??n",
                "Th??i B??nh", "B???c Giang", "Nam ?????nh",
                "L??m ?????ng", "Long kh??nh", "H??ng Y??n", "H?? Nam"};

        String arrDistrict[]= {"Ho??n Ki???m"," ?????ng ??a"," Ba ????nh",
                "Hai B?? Tr??ng"," Ho??ng Mai"," Thanh Xu??n", "Long Bi??n"," Nam T??? Li??m", "B???c T??? Li??m", "T??y H???"," C???u Gi???y"," H?? ????ng"
                ,"Th??nh ph??? Th??i B??nh","????ng H??ng", "Huy???n H??ng H??" ,"Huy???n Ki???n X????ng","Huy???n Qu???nh Ph???"};

        String arrTown[]= {
                "B???c S??n", "H??ng H??", "Canh T??n","Ch?? H??a"," H??ng H??","H??ng Nh??n"," H??ng B??ng",
                "H??ng Bu???m", "H??ng ????o"," H??ng Gai", "H??ng M??"," H??ng Tr???ng", "L?? Th??i T???"," Phan Chu Trinh"," Ph??c T??n"," Tr???n H??ng ?????o", "Tr??ng Ti???n"};
        binding.startPoint.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arr));
        binding.endPoint.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arr));
        binding.txtProvince.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arr));
        binding.txtDistrict.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrDistrict));
        binding.txtTown.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrTown));
    }
    private void insertDomestic()
    {
        String sex="";
        String txtContactCovid="";
        String txtKhaiHo="";
        if(binding.cbKhaiHo.isChecked())
            txtKhaiHo="Khai h???";
        else
            txtKhaiHo="Kh??ng";

        if(binding.rdNam.isChecked())
            sex="Nam";
        else
            sex="N???";

        if(binding.cbF0.isChecked())
            txtContactCovid=" Tr?????ng h???p 1 ," + txtContactCovid;

        if(binding.cbNationalityF0.isChecked())
            txtContactCovid=" Tr?????ng h???p 2 ," + txtContactCovid;

        if(binding.cbSymptonF0.isChecked())
            txtContactCovid=" Tr?????ng h???p 3" +txtContactCovid;

        database.insertDomestic(new DomesticDeclaration(
                txtKhaiHo,
                binding.spinnerVehicles.getSelectedItem().toString(),
                binding.startPoint.getText().toString(),
                binding.endPoint.getText().toString(),
                binding.txtName.getText().toString(),
                binding.dateOfBirth.getText().toString(),
                binding.txtCMND.getText().toString(),
                sex,
                binding.txtProvince.getText().toString(),
                binding.txtDistrict.getText().toString(),
                binding.txtTown.getText().toString(),
                binding.txtAddress.getText().toString(),
                binding.txtNumberhone.getText().toString(),
                binding.txtSympton.getText().toString(),
                txtContactCovid,
                0
        ));
    }
}