package com.example.hauizone.ScanQR;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hauizone.Account.User;
import com.example.hauizone.BaseDatabase;
import com.example.hauizone.MainActivity;
import com.example.hauizone.R;
import com.example.hauizone.databinding.FragmentQrBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

public class QrFragment extends Fragment {
    FragmentQrBinding binding;
    AdapterNearPlace adapterNearPlace;
    AdapterYourRoute adapterYourRoute;
    ArrayList<DataNearPlace> dataNearPlaces;
    ArrayList<DataYourRoute> dataYourRoutes;
    IntentIntegrator intentIntegrator;
    User user;
    int Day, Month, Year;
    BaseDatabase baseDatabase;
    final static String ICON_HOSPITAL = "https://www.iconpacks.net/icons/1/free-hospital-icon-1066-thumb.png";
    final static String ICON_CHURCH = "https://d338t8kmirgyke.cloudfront.net/icons/icon_pngs/000/001/827/original/church.png";

    private FloatingActionButton btnFab;
    private BottomNavigationView bottomNavigationView;


    public static QrFragment newInstance() {
        
        Bundle args = new Bundle();
        QrFragment fragment = new QrFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnFab = getActivity().findViewById(R.id.btnFab);
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigation);
        baseDatabase = BaseDatabase.getInstance(getContext());
        user = baseDatabase.getUserById(MainActivity.INDEX);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qr, container, false);
        Log.e("QR","qrCODE");
        refreshData();
        clickView();

        return binding.getRoot();
    }


    private void clickView() {
        binding.btnScanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentIntegrator = IntentIntegrator.forSupportFragment(QrFragment.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("LỘ TRÌNH CỦA BẠN");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
            }
        });

        binding.btnNearPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget();
            }
        });
        binding.btnYourRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
                clickItemRecycleView();
            }
        });


        binding.btnYourRoute.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openYourRouteFragment(YourRouteFragment.newInstance());
                return true;
            }
        });

        clickItemRecycleView();
    }
    private void clickItemRecycleView(){
        adapterYourRoute.setOnClickItemYourRoute(new OnClickItemYourRoute() {
            @Override
            public void onData(DataYourRoute dataYourRoute) {
                YourRouteFragment yourRouteFragment = YourRouteFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataYourRoute",dataYourRoute);
                yourRouteFragment.setArguments(bundle);
                openYourRouteFragment(yourRouteFragment);
            }
        });
        adapterYourRoute.setOnLongClickItemYourRoute(new OnLongClickItemYourRoute() {
            @Override
            public void onData(int position) {
                deleteData(position);
            }
        });
    }

    private void widget() {

        if(dataNearPlaces == null) dataNearPlaces = new ArrayList<>();
        if(dataNearPlaces.size() == 0){
            dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL, "Đại học công nghiệp Hà Nội", "1.02 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_CHURCH, "Phố Tây Tựu", "4.1 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL, "Phố Nguyên Xá", "5.6 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_CHURCH, "Trà tranh O2", "2.11 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL, "Lẩu 99", "3.8 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_CHURCH, "Nhà văn hóa Văn Trì 4", "0.5 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL, "Giếng Văn Trì", "0.2 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_CHURCH, "Chung cư Hateco", "0.1 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL, "Chợ Xanh", "4.2 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_CHURCH, "Phố nướng", "3.6 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_HOSPITAL, "Phở Thìn", "2.1 km"));
            dataNearPlaces.add(new DataNearPlace(ICON_CHURCH, "Bún bò Huế", "2.5 km"));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapterNearPlace = new AdapterNearPlace(getContext(), dataNearPlaces);
        binding.rvQRCode.setLayoutManager(layoutManager);
        binding.rvQRCode.setAdapter(adapterNearPlace);
    }
    private void deleteData(int position){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn xóa")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatabase.deleteYourRoute(position);
                        openYourRouteFragment(QrFragment.newInstance());
                        Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }

    private void refreshData(){
        if(baseDatabase == null) baseDatabase = BaseDatabase.getInstance(getContext());
        if(dataYourRoutes == null ) dataYourRoutes = new ArrayList<>();
        try {
            dataYourRoutes = baseDatabase.getAllYourRouteWithUser(MainActivity.INDEX);
        }catch (Exception e){
            e.printStackTrace();
        }
        Collections.reverse(dataYourRoutes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapterYourRoute = new AdapterYourRoute(getContext(),dataYourRoutes);
        binding.rvQRCode.setLayoutManager(layoutManager);
        binding.rvQRCode.setAdapter(adapterYourRoute);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject jsonObject = new JSONObject(result.getContents());
                    String name = user.getName();
                    String address = user.getUserProvince();
                    String address_des = jsonObject.getString("address_des");
                    String address_go = user.getUserProvince();

                    Calendar calendar = Calendar.getInstance();
                    Day = calendar.get(Calendar.DAY_OF_MONTH);
                    Month = calendar.get(Calendar.MONTH) + 1;
                    Year = calendar.get(Calendar.YEAR);
                    String day_go = Day + "/" + Month + "/" + Year;
                    String day_des = Day + "/" + Month + "/" + Year;

                    DataYourRoute dataYourRoute = new DataYourRoute(name,address,address_des,address_go,day_des,day_go,MainActivity.INDEX);

                    confirmQRCode(dataYourRoute);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void confirmQRCode(DataYourRoute dataYourRoute){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Thông tin địa điểm")
                .setMessage(dataYourRoute.getAddress_des())
                .setPositiveButton("Check in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(baseDatabase == null) baseDatabase = BaseDatabase.getInstance(getContext());
                        baseDatabase.insertYourRoute(dataYourRoute);
                        Toast.makeText(getContext(), "Check in thành công", Toast.LENGTH_LONG).show();
                        openYourRouteFragment(QrFragment.newInstance());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Check in không thành công", Toast.LENGTH_LONG).show();

                    }
                })
                .create();

        alertDialog.show();
    }

    private void openYourRouteFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.enter, R.anim.exit,
                        R.anim.enter, R.anim.exit).replace(R.id.mainFragment, fragment).commit();
    }
}
