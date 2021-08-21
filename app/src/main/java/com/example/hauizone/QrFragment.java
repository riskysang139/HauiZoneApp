package com.example.hauizone;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.databinding.FragmentHomeBinding;
import com.example.hauizone.databinding.FragmentQrBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class QrFragment extends Fragment {
    FragmentQrBinding binding;
    AdapterNearPlace adapterNearPlace;
    AdapterYourRoute adapterYourRoute;
    ArrayList<DataNearPlace> dataNearPlaces;
    ArrayList<DataYourRoute> dataYourRoutes;
    final static String ICON_HOSPITAL = "https://www.iconpacks.net/icons/1/free-hospital-icon-1066-thumb.png";
    final static String ICON_CHURCH = "https://d338t8kmirgyke.cloudfront.net/icons/icon_pngs/000/001/827/original/church.png";
    String arrProvice[] = {"Hà Nội", "Hà Giang", "Hội An", "TPHCM", "Bắc Giang", "Hà Nam", "Hải Phòng", "Vĩnh Phúc", "Hà Nam", "An Giang", "Bình Định", "Cao Bằng", "Đắk Nông"};

    SimpleDateFormat dft;
    EditText edtName,edtAddressDes,edtAddressGo,edtDayDes,edtDayGo;
    Spinner spinner;
    Button btnAdd,btnEdit,btnClose,btnDayDes,btnDayGo;
    Calendar calendar;
    int DayDes,MonthDes,YearDes,DayGo,MonthGo,YearGo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        DayDes = calendar.get(Calendar.DAY_OF_MONTH);
        MonthDes = calendar.get(Calendar.MONTH);
        YearDes = calendar.get(Calendar.YEAR);
        DayGo = calendar.get(Calendar.DAY_OF_MONTH);
        MonthGo = calendar.get(Calendar.MONTH);
        YearGo = calendar.get(Calendar.YEAR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_qr,container,false);
        View view=binding.getRoot();


        onData();

        binding.btnNearPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
                adapterNearPlace = new AdapterNearPlace(getContext(),dataNearPlaces);
                binding.rvQRCode.setLayoutManager(layoutManager);
                binding.rvQRCode.setAdapter(adapterNearPlace);
            }
        });
        binding.btnYourRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
                adapterYourRoute = new AdapterYourRoute(getContext(),dataYourRoutes);
                binding.rvQRCode.setLayoutManager(layoutManager);
                binding.rvQRCode.setAdapter(adapterYourRoute);

                adapterYourRoute.setOnClickItemYourRoute(new OnClickItemYourRoute() {
                    @Override
                    public void onData(DataYourRoute dataYourRoute,int position) {
                        openSetYourRouter(Gravity.CENTER,dataYourRoute,position);
                    }
                });
            }
        });

        binding.btnYourRoute.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openSetYourRouter(Gravity.CENTER);
                return true;
            }
        });

        return view;
    }

    private void onData() {
        dataNearPlaces = new ArrayList<>();
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL,"Hà Nam","0.11 km"));
        dataNearPlaces.add(new DataNearPlace(ICON_CHURCH,"Hà Nam","0.11 km"));

        dataYourRoutes = new ArrayList<>();
        try {
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));
            dataYourRoutes.add(new DataYourRoute("Nguyễn Đức Thành","Hà Nam","Hà Nội","Hà Nam",dft.parse("20/07/2021"),dft.parse("29/07/2021")));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        adapterNearPlace = new AdapterNearPlace(getContext(),dataNearPlaces);
        binding.rvQRCode.setLayoutManager(layoutManager);
        binding.rvQRCode.setAdapter(adapterNearPlace);
    }

    private void openSetYourRouter(int gravity) {


        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_yourrouter);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        onDataDialog(dialog);

        btnEdit.setVisibility(View.GONE);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String address = spinner.getSelectedItem().toString();
                String address_des = edtAddressDes.getText().toString();
                String address_go = edtAddressGo.getText().toString();
                String day_des = edtDayDes.getText().toString();
                String day_go = edtDayGo.getText().toString();

                try {
                    dataYourRoutes.add(new DataYourRoute(name, address, address_des, address_go,dft.parse(day_des),dft.parse(day_go)));
                    adapterYourRoute.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        btnDayDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDay(dialog,true);
            }
        });

        btnDayGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDay(dialog,false);
            }
        });
        dialog.show();

    }
    private void openSetYourRouter(int gravity,DataYourRoute dataYourRoute,int position) {


        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_yourrouter);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        onDataDialog(dialog);

        btnEdit.setVisibility(View.VISIBLE);

        int positionQueQuan = 0;
        for(int i=0;i<arrProvice.length;i++){
            if(arrProvice[i].equals(dataYourRoute.getAddress())){
                positionQueQuan = i;
                break;
            }
        }
        edtName.setText(dataYourRoute.getName());
        spinner.setSelection(positionQueQuan);
        edtAddressDes.setText(dataYourRoute.getAddress_des());
        edtAddressGo.setText(dataYourRoute.getAddress_go());
        edtDayDes.setText(dataYourRoute.getDay_des());
        edtDayGo.setText(dataYourRoute.getDay_go());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String address = spinner.getSelectedItem().toString();
                String address_des = edtAddressDes.getText().toString();
                String address_go = edtAddressGo.getText().toString();
                String day_des = edtDayDes.getText().toString();
                String day_go = edtDayGo.getText().toString();
                try {
                    DataYourRoute data = new DataYourRoute(name, address, address_des, address_go,dft.parse(day_des),dft.parse(day_go));
                    dataYourRoutes.set(position,data);
                    adapterYourRoute.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String address = spinner.getSelectedItem().toString();
                String address_des = edtAddressDes.getText().toString();
                String address_go = edtAddressGo.getText().toString();
                String day_des = edtDayDes.getText().toString();
                String day_go = edtDayGo.getText().toString();

                try {
                    dataYourRoutes.add(new DataYourRoute(name, address, address_des, address_go,dft.parse(day_des),dft.parse(day_go)));
                    adapterYourRoute.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        btnDayDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDay(dialog,true);
            }
        });

        btnDayGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDay(dialog,false);
            }
        });
        dialog.show();

    }
    private void onDataDialog(Dialog dialog){
        edtName = dialog.findViewById(R.id.edtTen);
        spinner = dialog.findViewById(R.id.spinner);
        edtAddressDes = dialog.findViewById(R.id.edtDiemDen);
        edtAddressGo = dialog.findViewById(R.id.edtDiemDi);
        edtDayDes = dialog.findViewById(R.id.edtNgayDen);
        edtDayGo = dialog.findViewById(R.id.edtNgayDi);
        btnAdd = dialog.findViewById(R.id.btnThem);
        btnEdit = dialog.findViewById(R.id.btnSua);
        btnClose = dialog.findViewById(R.id.btnDong);
        btnDayDes = dialog.findViewById(R.id.btnNgayDen);
        btnDayGo = dialog.findViewById(R.id.btnNgayDi);

        ArrayAdapter adapter = new ArrayAdapter(dialog.getContext(),R.layout.support_simple_spinner_dropdown_item,arrProvice);
        spinner.setAdapter(adapter);
    }
    private void processDay(Dialog dialog,Boolean checkday) {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(checkday == true){
                    edtDayDes.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    DayDes = dayOfMonth;
                    MonthDes = month;
                    YearDes = year;
                }
                else {
                    edtDayGo.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    DayGo = dayOfMonth;
                    MonthGo = month;
                    YearGo = year;
                }

            }
        };

        DatePickerDialog datePickerDialog;
        if(checkday == true){
            datePickerDialog = new DatePickerDialog(dialog.getContext(), callBack, YearDes, MonthDes, DayDes);
            datePickerDialog.setTitle("Ngày đến");
        }
        else {
            datePickerDialog = new DatePickerDialog(dialog.getContext(), callBack, YearGo, MonthGo, DayGo);
            datePickerDialog.setTitle("Ngày đi");
        }

        datePickerDialog.show();
    }

}
