package com.example.hauizone.Admin.AdminNotifi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hauizone.Admin.AdminDomesticAndEntry.RCVEntryAdapter;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.Notification.Notification;
import com.example.hauizone.Notification.NotificationAdapter;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivityAdminNotifiBinding;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdminNotifiActivity extends AppCompatActivity implements MyNotifiAdminAdapter.ClickListener {
        ActivityAdminNotifiBinding binding;
        RecyclerView rv_admin_notifi;
        Notification notification;

        String arr[] = {"HAUI NCOVI - TIN COVID","HAUI NCOVI - THÔNG BÁO"};
        int pos = 0, i = 0;
        ArrayAdapter<String> spin_adapter = null;
        MyNotifiAdminAdapter myNotifiAdminAdapter;
        ArrayList<Notification> arrList = null;
        BaseDatabase db = null;
        String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_notifi);
        db = BaseDatabase.getInstance(getBaseContext());
        notification = new Notification();
        rv_admin_notifi = (RecyclerView) findViewById(R.id.rv_admin_notification);
        arrList = new ArrayList<Notification>();
        arrList = db.getAllNotifi();
        if(arrList.size() == 0){
            addDataNotificationFragment();
            refreshAdapter();
        }

        myNotifiAdminAdapter = new MyNotifiAdminAdapter(arrList, getBaseContext(),this) ;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.rvAdminNotification.setLayoutManager(layoutManager);
        binding.rvAdminNotification.setAdapter(myNotifiAdminAdapter);
        getWidget();

        binding.buttonCalendar.setOnClickListener((view) -> {
            DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    binding.editTextNgayThem.setText(dayOfMonth + "/" + month + "/" + year);
                }
            };
            DatePickerDialog pic = new DatePickerDialog(AdminNotifiActivity.this, callback, 2021, 7, 28);
            pic.setTitle("Chọn ngày");
            pic.show();
        });

        binding.buttonSettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.editTextGioThem.setText(hourOfDay + ":" + minute);
                    }
                };
                TimePickerDialog time = new TimePickerDialog(AdminNotifiActivity.this, callback, 8, 33, true);
                time.setTitle("Chọn giờ");
                time.show();
            }
        });

        binding.buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        binding.buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditData();
            }
        });

    }

    public void getWidget() {
        spin_adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_activated_1, arr);
        binding.spinnerLoaiTin.setAdapter(spin_adapter);
        db = BaseDatabase.getInstance(this);
    }

    public void refreshAdapter(){
        arrList = new ArrayList<>();
        arrList = db.getAllNotifi();
        myNotifiAdminAdapter = new MyNotifiAdminAdapter(arrList, this, this);
        rv_admin_notifi.setAdapter(myNotifiAdminAdapter);
    }

    private void insertData(){
        Notification tb = new Notification(binding.spinnerLoaiTin.getSelectedItem().toString(), binding.editTextNgayThem.getText().toString(), binding.editTextGioThem.getText().toString(), binding.editTextNoiDung.getText().toString(), binding.editTextImage.getText().toString());
        db.insertNotification(tb);
        refreshAdapter();
        myNotifiAdminAdapter.notifyDataSetChanged();
    }

    private void EditData(){
        Notification a = notification;
        a.setType(binding.spinnerLoaiTin.getSelectedItem().toString());
        a.setDate(binding.editTextNgayThem.getText().toString());
        a.setTime(binding.editTextGioThem.getText().toString());
        a.setContent(binding.editTextNoiDung.getText().toString());
        a.setImageNotification(binding.editTextImage.getText().toString());
        db.updateNotificaton(a);
        refreshAdapter();
    }


    public void addDataNotificationFragment(){
        Notification notification1 = new Notification();
        Notification notification2 = new Notification();
        Notification notification3 = new Notification();
        Notification notification4 = new Notification();

        notification1.setType("HAUI NCOVI - TIN COVID");
        notification1.setDate("12/07/2021");
        notification1.setTime("12:05");
        notification1.setContent("Có 15 bệnh nhân đã được xuất viện ở bệnh viện A");
        notification1.setImageNotification("https://image.thanhnien.vn/2048/uploaded/duytinh/2021_08_18/z2692287972928_032d9fba6f809c77795821eff467793a_npea.jpg");

        notification2.setType("HAUI NCOVI - THÔNG BÁO");
        notification2.setDate("12/07/2021");
        notification2.setTime("12:06");
        notification2.setContent("Bạn cần khai báo y tế đúng giờ");
        notification2.setImageNotification("https://umcclinic.com.vn/Data/Sites/1/media/h%C6%B0%E1%BB%9Bng-d%E1%BA%ABn-kh/huongdan.jpg");

        notification3.setType("HAUI NCOVI - TIN COVID");
        notification3.setDate("12/07/2021");
        notification3.setTime("12:07");
        notification3.setContent("Có 49 bệnh nhân đã được xuất viện ở bệnh viện B");
        notification3.setImageNotification("data:image/jpeg;base64,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");

        notification4.setType("HAUI NCOVI - THÔNG BÁO");
        notification4.setDate("12/07/2021");
        notification4.setTime("12:08");
        notification4.setContent("Bản cập nhật mới đã sẵn sàng");
        notification4.setImageNotification("https://camnangkhoinghiep.vn/wp-content/uploads/2019/12/update-l%C3%A0-g%C3%AC-1140x570.jpg");

        db.insertNotification(notification1);
        db.insertNotification(notification2);
        db.insertNotification(notification3);
        db.insertNotification(notification4);
    }


    @Override
    public void onClick(Notification notification, int position) {
        this.notification = new Notification();
        this.notification = notification;
        int spinnerPosition = spin_adapter.getPosition(notification.getType());
        binding.spinnerLoaiTin.setSelection(spinnerPosition);
        binding.editTextNgayThem.setText(notification.getDate());
        binding.editTextGioThem.setText(notification.getTime());
        binding.editTextNoiDung.setText(notification.getContent());
        binding.editTextImage.setText(notification.getImageNotification());

    }

    @Override
    public void onLongClick(Notification notification, int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(AdminNotifiActivity.this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn xóa dòng này không ?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteNotifi(arrList.get(position));
                        refreshAdapter();
                        Toast.makeText(AdminNotifiActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AdminNotifiActivity.this, "Hủy xóa", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }
}