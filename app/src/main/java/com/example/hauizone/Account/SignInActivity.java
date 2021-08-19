package com.example.hauizone.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hauizone.Admin.AdminActivity;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    BaseDatabase mBaseDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_in);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        setEvent();
    }

    private void setEvent() {
        //dang nhap
        binding.btnDangNhap.setOnClickListener(v -> ClickDangNhap());
        // dang ky
        binding.tvDangKy.setOnClickListener(v -> ClickDangKy());
        // back
//        binding.imgBack.setOnClickListener(v -> finish());
    }

    private void ClickDangKy() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void ClickDangNhap() {
        mBaseDataBase = new BaseDatabase(this);
        mBaseDataBase.setFlagOut();

        String name = binding.edtUsername.getText().toString();
        String pass = binding.edtPassword.getText().toString();

        // dang nhap voi tai khoan admin -> man hinh admin
        if(name.trim().equals("admin") && pass.trim().equals("admin")){
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        }else{
            User user = null;
            user = mBaseDataBase.getUserByUsernamePassword(name,pass);

            if( user.getName() != null || user.getUserName() != null){
                user.setFlag(1);
                Log.e("Error", user.getName() + user.getFlag());
                int check = -1;
                check = mBaseDataBase.updateUser(user);
                if(check != -1){
                    Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }else{
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            }
        }




    }
}