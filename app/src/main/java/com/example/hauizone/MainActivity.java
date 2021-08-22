package com.example.hauizone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.hauizone.Account.AccountFragment;
import com.example.hauizone.Home.HomeFragment;
import com.example.hauizone.Notification.NotificationFragment;
import com.example.hauizone.R;
import com.example.hauizone.Report.ReportFragment;
import com.example.hauizone.ScanQR.QrFragment;
import com.example.hauizone.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BaseDatabase mBaseDatabase;
    public static int INDEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBaseDatabase = new BaseDatabase(this);
        INDEX = mBaseDatabase.getIndex();
        binding.btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callReport();
            }
        });
        setUpBottomNavigation();
    }

    public void setUpBottomNavigation() {

        getSupportFragmentManager().beginTransaction().add(R.id.mainFragment, new HomeFragment()).commit();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().
                                setCustomAnimations(R.anim.enter, R.anim.exit,
                                        R.anim.enter, R.anim.exit).replace(R.id.mainFragment, new HomeFragment()).commit();
                        binding.btnFab.hide();
                        break;
                    case R.id.report:
                        getSupportFragmentManager().beginTransaction().
                                setCustomAnimations(R.anim.enter, R.anim.exit,
                                        R.anim.enter, R.anim.exit).replace(R.id.mainFragment, new ReportFragment()).commit();
                        binding.btnFab.hide();
                        break;
                    case R.id.qr_scan:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                                R.anim.enter, R.anim.exit).replace(R.id.mainFragment, new QrFragment()).commit();
                        binding.btnFab.hide();
                        break;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                                R.anim.enter, R.anim.exit).replace(R.id.mainFragment, new NotificationFragment()).commit();
                        binding.btnFab.hide();
                        break;
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                                R.anim.enter, R.anim.exit).replace(R.id.mainFragment, new AccountFragment()).commit();
                        binding.btnFab.hide();
                        break;
                }
                return true;
            }
        });
    }

    public void callReport() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + "19009095"));
        startActivity(callIntent);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                startActivity(callIntent);
            }
        } else {
            startActivity(callIntent);
        }
    }


}