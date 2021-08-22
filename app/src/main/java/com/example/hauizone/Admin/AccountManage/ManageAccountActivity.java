package com.example.hauizone.Admin.AccountManage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.hauizone.Account.User;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivityManageAccountBinding;

import java.util.ArrayList;
import java.util.List;

public class ManageAccountActivity extends AppCompatActivity {

    ActivityManageAccountBinding binding;
    AdapterAccountManage mAdapterAccountManage;
    List<User> mUserList;
    BaseDatabase mBaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_manage_account);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_account);
        setEvent();
    }

    private void setEvent() {
        mBaseDatabase = new BaseDatabase(this);
        mUserList = new ArrayList<>();
        // listView
        setDataListView();

        binding.lvManageAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageAccountActivity.this, ViewInformationAccountActivity.class);
                intent.putExtra("id", mUserList.get(position).getUserId());
                startActivity(intent);
            }
        });
        binding.lvManageAccount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ManageAccountActivity.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có muốn xóa thông tin người dùng " + mUserList.get(position).getName() + "?" );
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // xoa thong tin nguoi dung khoi database va up date lại listview

                        int check = -1;
                        check = mBaseDatabase.deleteUserByID(mUserList.get(position).getUserId());

                        if(check != -1){
                            Toast.makeText(ManageAccountActivity.this, "Xóa thông tin tài khoản thành công!", Toast.LENGTH_SHORT).show();
                            setDataListView();
                        }else{
                            Toast.makeText(ManageAccountActivity.this, "Lỗi! Không xóa được tài khoản.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                Dialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });

        binding.imgBack.setOnClickListener(v -> finish());
    }

    private void setDataListView() {
        //lay danh sach nguoi dung tu database
        getDataUserFromDatabase();
        mAdapterAccountManage = new AdapterAccountManage(this, mUserList);
        binding.lvManageAccount.setAdapter(mAdapterAccountManage);
    }

    private void getDataUserFromDatabase() {
        try{
            mUserList = mBaseDatabase.getAllUser();
        }catch(Exception e){
            Log.e("ManageAccountActivity", e.toString());
        }
    }
}