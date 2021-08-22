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


    }

    @Override
    protected void onResume() {
        super.onResume();
        setEvent();
    }

    private void setEvent() {
        addData();
        //dang nhap
        binding.btnDangNhap.setOnClickListener(v -> ClickDangNhap());
        // dang ky
        binding.tvDangKy.setOnClickListener(v -> ClickDangKy());
        // back
//        binding.imgBack.setOnClickListener(v -> finish());
    }

    private void addData() {
        mBaseDataBase = BaseDatabase.getInstance(getBaseContext());
        if(mBaseDataBase.getCountUser() <= 0 ){
            fakeData();
        }
    }

    private void fakeData() {
        //public User(int userId, String userName, String password, String name, String dateOfBirth, String gender, String userProvince, String userDistrict, String userWard, String userStreet, String phoneNumber, String email, String epidemic, int flag) {
        mBaseDataBase.insertUser(new User(1
                ,"tanbui"
                ,"tan1234"
                ,"Bùi Đăng Tân"
                , "01/02/2000"
                ,"Nam"
                ,"Thái Bình"
                ,"Kiến Xương"
                ,"Quốc Tuấn"
                ,"Xóm 6"
                , "0396172418"
                ,"buidangtan01042000@gmail.com"
                ,"Không mắc bệnh", 0));
        mBaseDataBase.insertUser(new User(1
                ,"sangtran"
                ,"sang1234"
                ,"Trần Quang Sang"
                , "02/02/2000"
                ,"Nam"
                ,"Thái Bình"
                ,"Hưng Hà"
                ,"a"
                ,"a"
                , "000"
                ,"email"
                ,"Không mắc bệnh", 0));
        mBaseDataBase.insertUser(new User(1
                ,"thangnguyen"
                ,"thang1234"
                ,"Nguyễn Văn Thàng"
                , "03/02/2000"
                ,"Nam"
                ,"Nam Định"
                ,"b"
                ,"b"
                ,"b"
                , "000"
                ,"email"
                ,"Không mắc bệnh", 0));
        mBaseDataBase.insertUser(new User(1
                ,"thanhnguyen"
                ,"thanh1234"
                ,"Nguyễn Đức Thành"
                , "04/02/2000"
                ,"Nam"
                ,"Hà Nam"
                ,"c"
                ,"c"
                ,"c"
                , "000"
                ,"email"
                ,"Không mắc bệnh", 0));
        mBaseDataBase.insertUser(new User(1
                ,"thangcao"
                ,"thang1234"
                ,"Cao Thế Thắng"
                , "05/02/2000"
                ,"Nam"
                ,"Vĩnh Phúc"
                ,"d"
                ,"d"
                ,"d"
                , "0000"
                ,"email"
                ,"Không mắc bệnh", 0));
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