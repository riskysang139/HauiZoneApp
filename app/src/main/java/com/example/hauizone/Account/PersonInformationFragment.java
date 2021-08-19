package com.example.hauizone.Account;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.Account.AccountFragment;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentPersonInformationBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.hauizone.MainActivity.INDEX;

public class PersonInformationFragment extends Fragment {


    FragmentPersonInformationBinding binding;
    BaseDatabase mBaseDatabase;
    List<User> mListUser;

    List<String> listTinh, listQuan, listPhuong;
    String []dichBenh = {"F0", "F1", "F2", "F3", "F4", "Không mắc bệnh"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_information, container, false);
        View view = binding.getRoot();
        setEvents();
        return view;
    }

    private void setEvents() {

        getDataList();
        setAutoComplete();
        // spinner
        setSpinnerTinhTrang();
        //
        setDataToView();
        //cap nhat
        binding.btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndAddList();

                if(checkInput() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn phải nhập đầy đủ thông tin bên trên để cập nhật thông tin!");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    Dialog dialog = builder.create();
                    dialog.show();
                }
                else if(binding.cbCamKet.isChecked() == false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn phải tích vào cam kết bên trên để cập nhật thông tin!");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }else{
                    updateData();
                }

            }
        });

        // date_
        binding.imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBirthday();
            }
        });
        // back
        binding.imgBack.setOnClickListener(v -> setClickBack());

    }
    private int checkInput() {


        if(binding.edtHoTen.getText().toString().trim().equals("")){
            return 0;
        }
        if(binding.edtNgaySinh.getText().toString().trim().equals("")){
            return 0;
        }
        if(binding.tvTinhThanh.getText().toString().trim().equals("")){
            return 0;
        }
        if(binding.tvQuanHuyen.getText().toString().trim().equals("")){
            return 0;
        }
        if(binding.tvPhuongXa.getText().toString().trim().equals("")){
            return 0;
        }
        if(binding.edtSoNha.getText().toString().trim().equals("")){
            return 0;
        }if(binding.edtSDT.getText().toString().trim().equals("")){
            return 0;
        }

        return 1;
    }

    private void setDataToView() {

        mBaseDatabase = new BaseDatabase(getContext());
        User user = new User();
        user = mBaseDatabase.getUserById(INDEX);
                    binding.edtHoTen.setText(user.getName());
                    binding.edtNgaySinh.setText(user.getDateOfBirth());
                    binding.edtEmail.setText(user.getEmail());
                    binding.edtSDT.setText(user.getPhoneNumber());
                    binding.edtSoNha.setText(user.getUserStreet());
                    binding.tvTinhThanh.setText(user.getUserProvince());
                    binding.tvQuanHuyen.setText(user.getUserDistrict());
                    binding.tvPhuongXa.setText(user.getUserWard());

                    if(user.getGender().equals("Nam")){
                        binding.rbNam.setChecked(true);
                    }else{
                        binding.rbNu.setChecked(true);
                    }
                    int i = 0;
                    for(;i < dichBenh.length; i++){
                        if(dichBenh[i].equals(user.getEpidemic())){
                            break;
                        }
                    }
                    binding.spDichbenh.setSelection(i);
                    return;
                }




    private void updateData() {
        mBaseDatabase = new BaseDatabase(getContext());
        try {
            User user  = new User();
            user = mBaseDatabase.getUserById(INDEX);

                    user.setName(binding.edtHoTen.getText().toString());
                    user.setDateOfBirth(binding.edtNgaySinh.getText().toString());

                    if(binding.rbNam.isChecked()){
                        user.setGender(binding.rbNam.getText().toString());
                    }else{
                        user.setGender(binding.rbNu.getText().toString());
                    }

                    user.setUserProvince(binding.tvTinhThanh.getText().toString());
                    user.setUserDistrict(binding.tvQuanHuyen.getText().toString());
                    user.setUserWard(binding.tvPhuongXa.getText().toString());
                    user.setUserStreet(binding.edtSoNha.getText().toString());
                    user.setPhoneNumber(binding.edtSDT.getText().toString());
                    user.setEmail(binding.edtEmail.getText().toString());
                    user.setEpidemic(binding.spDichbenh.getSelectedItem().toString());


                    int check = -1;
                    check = mBaseDatabase.updateUser(user);
                    if(check != -1){
                        Toast.makeText(getContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Có lỗi! Cập nhật thông tin thất bại!", Toast.LENGTH_SHORT).show();
                    }

        }catch(Exception e){
            Log.e("PersonInfomationFrag", e.toString());
        }


    }

    private void setAutoComplete() {
        //tinh
        ArrayAdapter<String> arrayAdapterTinh = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, listTinh);
//        arrayAdapterTinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvTinhThanh.setAdapter(arrayAdapterTinh);
        binding.tvTinhThanh.setThreshold(1);
        // quan
        ArrayAdapter<String> arrayAdapterQuan = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, listQuan);
//        arrayAdapterQuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvQuanHuyen.setAdapter(arrayAdapterQuan);
        binding.tvQuanHuyen.setThreshold(1);
        // phuong
        ArrayAdapter<String> arrayAdapterPhuong = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, listPhuong);
//        arrayAdapterPhuong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tvPhuongXa.setAdapter(arrayAdapterPhuong);
        binding.tvPhuongXa.setThreshold(1);
    }

    private void checkAndAddList() {
        int check = 0;
        for (String s : listTinh) {
            if (binding.tvTinhThanh.getText().toString().equals(s)) {
                check = 1;
                break;
            }
        }
        if (check == 0) {
            listTinh.add(binding.tvTinhThanh.getText().toString());
        } else {
            check = 0;
        }
        //quan
        for (String s : listQuan) {
            if (binding.tvQuanHuyen.getText().toString().equals(s)) {
                check = 1;
                break;
            }
        }
        if (check == 0) {
            listQuan.add(binding.tvQuanHuyen.getText().toString());
        } else {
            check = 0;
        }
        // phuong
        for (String s : listPhuong) {
            if (binding.tvPhuongXa.getText().toString().equals(s)) {
                check = 1;
                break;
            }
        }
        if (check == 0) {
            listPhuong.add(binding.tvPhuongXa.getText().toString());
        } else {
            check = 0;
        }
        setAutoComplete();
    }

    private void getDataList() {
        listTinh = new ArrayList<String>();
        listTinh.add("Hà Nội");
        listTinh.add("Hải Phòng");
        listTinh.add("Thái Bình");
        //
        listQuan = new ArrayList<>();
        listQuan.add("Bắc Từ Liêm");
        listQuan.add("Nam Từ Liêm");
        listQuan.add("Ba Đình");
        listQuan.add("Cầu Giấy");
        //
        listPhuong = new ArrayList<>();
        listPhuong.add("Minh Khai");
        listPhuong.add("Phúc Diễn");
    }

    private void setClickBack() {
        MainActivity main = (MainActivity) this.getContext();
        main.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, new AccountFragment())
                .addToBackStack(null).commit();
    }

    public void processBirthday() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                binding.edtNgaySinh.setText(arg3 + "/" + (arg2 + 1) + "/" + arg1);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog dateDialog = new DatePickerDialog(getContext(), callBack, year, month, day);
        dateDialog.setTitle("Ngày sinh");
        dateDialog.show();
    }
    private void setSpinnerTinhTrang() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_dropdown_item_1line, dichBenh);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spDichbenh.setAdapter(arrayAdapter);
    }
}