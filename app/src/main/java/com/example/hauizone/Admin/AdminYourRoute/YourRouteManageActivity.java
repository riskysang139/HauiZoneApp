package com.example.hauizone.Admin.AdminYourRoute;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hauizone.BaseDatabase;
import com.example.hauizone.R;
import com.example.hauizone.ScanQR.DataYourRoute;
import com.example.hauizone.databinding.ActivityManageYourRouteBinding;

import java.util.ArrayList;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
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