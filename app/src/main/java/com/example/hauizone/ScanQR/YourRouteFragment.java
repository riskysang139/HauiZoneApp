package com.example.hauizone.ScanQR;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentYourRouteBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class YourRouteFragment extends Fragment {

    FragmentYourRouteBinding binding;
    BaseDatabase baseDatabase;

    SimpleDateFormat dft;
    Calendar calendar;
    int DayDes, MonthDes, YearDes, DayGo, MonthGo, YearGo;
    String arrProvice[] = {"Hà Nội", "Hà Giang", "Hội An", "TPHCM", "Bắc Giang", "Hà Nam", "Hải Phòng", "Vĩnh Phúc", "Hà Nam", "An Giang", "Bình Định", "Cao Bằng", "Đắk Nông"};

    private FloatingActionButton btnFab;
    private BottomNavigationView bottomNavigationView;
    DataYourRoute dataYourRoute;

    public static YourRouteFragment newInstance() {

        Bundle args = new Bundle();
        YourRouteFragment fragment = new YourRouteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseDatabase = BaseDatabase.getInstance(getContext());

        Bundle bundle = getArguments();
        if(bundle != null){
            dataYourRoute = (DataYourRoute) bundle.get("dataYourRoute");
        }
        btnFab = getActivity().findViewById(R.id.btnFab);
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigation);
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_your_route, container, false);

        widget();
        clickView();
        return binding.getRoot();
    }

    private void widget() {

        ArrayAdapter<String> adapterProvice = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, arrProvice);
        binding.spinner.setAdapter(adapterProvice);

        if(dataYourRoute != null){
            binding.btnSua.setVisibility(View.VISIBLE);
            binding.edtTen.setText(dataYourRoute.getName());
            int position=0;
            for(int i = 0 ;i<arrProvice.length;i++){
                if(arrProvice[i].equals(dataYourRoute.getAddress())){
                    position = i;
                    break;
                }
            }
            binding.spinner.setSelection(position);
            binding.edtDiemDen.setText(dataYourRoute.getAddress_des());
            binding.edtDiemDi.setText(dataYourRoute.getAddress_go());
            binding.edtNgayDen.setText(dataYourRoute.getDay_des());
            binding.edtNgayDi.setText(dataYourRoute.getDay_go());
        }else {
            binding.btnSua.setVisibility(View.GONE);
        }
    }

    private void clickView(){

        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        binding.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        binding.btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

        binding.btnNgayDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDay(true);
            }
        });
        binding.btnNgayDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDay(false);
            }
        });
    }

    private void updateData(){
        try {
            if(baseDatabase == null) baseDatabase = BaseDatabase.getInstance(getContext());
            baseDatabase.updateYourRoute(new DataYourRoute(
                    dataYourRoute.getId(),
                    binding.edtTen.getText().toString(),
                    binding.spinner.getSelectedItem().toString(),
                    binding.edtDiemDen.getText().toString(),
                    binding.edtDiemDi.getText().toString(),
                    binding.edtNgayDen.getText().toString(),
                    binding.edtNgayDi.getText().toString()
                    ));
            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            openQrFragment(QrFragment.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void insertData(){

        try {
            if(baseDatabase == null) baseDatabase = BaseDatabase.getInstance(getContext());
            baseDatabase.insertYourRoute(new DataYourRoute(
                    binding.edtTen.getText().toString(),
                    binding.spinner.getSelectedItem().toString(),
                    binding.edtDiemDen.getText().toString(),
                    binding.edtDiemDi.getText().toString(),
                    binding.edtNgayDen.getText().toString(),
                    binding.edtNgayDi.getText().toString()
            ));

            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            openQrFragment(QrFragment.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void close(){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn đóng")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openQrFragment(QrFragment.newInstance());
                        Toast.makeText(getContext(), "Đóng thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Đóng không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }

    private void processDay(Boolean checkday) {

        calendar = Calendar.getInstance();
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        DayDes = calendar.get(Calendar.DAY_OF_MONTH);
        MonthDes = calendar.get(Calendar.MONTH);
        YearDes = calendar.get(Calendar.YEAR);
        DayGo = calendar.get(Calendar.DAY_OF_MONTH);
        MonthGo = calendar.get(Calendar.MONTH);
        YearGo = calendar.get(Calendar.YEAR);

        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (checkday == true) {
                    binding.edtNgayDen.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    DayDes = dayOfMonth;
                    MonthDes = month;
                    YearDes = year;
                } else {
                    binding.edtNgayDi.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    DayGo = dayOfMonth;
                    MonthGo = month;
                    YearGo = year;
                }
            }
        };

        DatePickerDialog datePickerDialog;
        if (checkday == true) {
            datePickerDialog = new DatePickerDialog(getContext(), callBack, YearDes, MonthDes, DayDes);
            datePickerDialog.setTitle("Ngày đến");
        } else {
            datePickerDialog = new DatePickerDialog(getContext(), callBack, YearGo, MonthGo, DayGo);
            datePickerDialog.setTitle("Ngày đi");
        }

        datePickerDialog.show();
    }

    private void openQrFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.enter, R.anim.exit,
                        R.anim.enter, R.anim.exit).replace(R.id.mainFragment, fragment).commit();
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}
