package com.example.hauizone.Admin.AdminYourRoute;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.ScanQR.DataYourRoute;
import com.example.hauizone.databinding.ActivityManageYourRouteBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YourRouteManageActivity extends AppCompatActivity implements YourRouteManageAdapter.OnClickItemYourRouteManage {

    ActivityManageYourRouteBinding binding;
    YourRouteManageAdapter yourRouteAdapter;
    BaseDatabase baseDatabase;
    List<DataYourRoute> dataYourRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_your_route);

        refreshData();
        clickView();

    }

    private void clickView() {
        binding.imgBack.setOnClickListener(v -> finish());
    }

    public void refreshData() {
        if (baseDatabase == null) baseDatabase = BaseDatabase.getInstance(this);
        if (dataYourRoutes == null) dataYourRoutes = new ArrayList<>();

        try {
            dataYourRoutes = baseDatabase.getAllYourRoute();
            if(dataYourRoutes.size() == 0){
                baseDatabase.insertYourRoute(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nam","Nam Định","05/09/2021","04/09/2021",4));
                baseDatabase.insertYourRoute(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Nam Định","Hà Nam","05/09/2021","04/09/2021",4));
                baseDatabase.insertYourRoute(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Vĩnh Phúc","Nam Định","05/09/2021","04/09/2021",4));

                baseDatabase.insertYourRoute(new DataYourRoute("Nguyễn Văn Thàng","Nam Định","Hà Nam","Nam Định","05/09/2021","04/09/2021",3));
                baseDatabase.insertYourRoute(new DataYourRoute("Nguyễn Văn Thàng","Nam Định","Nam Định","Hà Nam","05/09/2021","04/09/2021",3));
                baseDatabase.insertYourRoute(new DataYourRoute("Nguyễn Văn Thàng","Nam Định","Vĩnh Phúc","Nam Định","05/09/2021","04/09/2021",3));

                baseDatabase.insertYourRoute(new DataYourRoute("Cao Thế Thắng","Vĩnh Phúc","Hà Nam","Nam Định","05/09/2021","04/09/2021",5));
                baseDatabase.insertYourRoute(new DataYourRoute("Cao Thế Thắng","Vĩnh Phúc","Nam Định","Hà Nam","05/09/2021","04/09/2021",5));
                baseDatabase.insertYourRoute(new DataYourRoute("Cao Thế Thắng","Vĩnh Phúc","Vĩnh Phúc","Nam Định","05/09/2021","04/09/2021",5));

                baseDatabase.insertYourRoute(new DataYourRoute("Bùi Đăng Tân","Nam Định","Hà Nam","Nam Định","05/09/2021","04/09/2021",1));
                baseDatabase.insertYourRoute(new DataYourRoute("Bùi Đăng Tân","Nam Định","Nam Định","Hà Nam","05/09/2021","04/09/2021",1));
                baseDatabase.insertYourRoute(new DataYourRoute("Bùi Đăng Tân","Nam Định","Vĩnh Phúc","Nam Định","05/09/2021","04/09/2021",1));

                baseDatabase.insertYourRoute(new DataYourRoute("Trần Quang Sang","Thái Bình","Hà Nam","Nam Định","05/09/2021","04/09/2021",2));
                baseDatabase.insertYourRoute(new DataYourRoute("Trần Quang Sang","Thái Bình","Nam Định","Hà Nam","05/09/2021","04/09/2021",2));
                baseDatabase.insertYourRoute(new DataYourRoute("Trần Quang Sang","Thái Bình","Vĩnh Phúc","Nam Định","05/09/2021","04/09/2021",2));
                dataYourRoutes = baseDatabase.getAllYourRouteWithUser(MainActivity.INDEX);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.reverse(dataYourRoutes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        yourRouteAdapter = new YourRouteManageAdapter(this, dataYourRoutes, this);
        binding.rvManageYourRoute.setLayoutManager(linearLayoutManager);
        binding.rvManageYourRoute.setAdapter(yourRouteAdapter);

    }


    @Override
    public void onLongClick(DataYourRoute dataYourRoute) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn xóa lộ trình?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (baseDatabase == null)
                            baseDatabase = BaseDatabase.getInstance(YourRouteManageActivity.this);
                        baseDatabase.deleteYourRoute(dataYourRoute.getId());
                        refreshData();
                        Toast.makeText(YourRouteManageActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(YourRouteManageActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }
}