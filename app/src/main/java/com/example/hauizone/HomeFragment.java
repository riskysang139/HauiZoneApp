package com.example.hauizone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hauizone.databinding.FragmentHomeBinding;
import com.example.hauizone.model.Covid19;
import com.example.hauizone.model.Covid19VN;
import com.example.hauizone.model.JsonApi;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private final String mapCovid = "https://covidmaps.hanoi.gov.vn/";
    private final String detailVNlink = "https://ncovi.vnpt.vn/views/ncovi_detail.html";
    private final String detailWorldlink = "https://ncovi.vnpt.vn/views/ncovi_detail.html";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        setDate();
        setMap();
        callJsonApiWorl();
        callJsonApiVN();
        extentDetail();
    }

    public void setMap() {
        WebSettings webSettings = binding.webmap.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webmap.setWebViewClient(new Callback());
        binding.webmap.loadUrl(mapCovid);
        binding.openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapCovid)));
            }
        });
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }

    public void setDate() {
        Calendar calendar = Calendar.getInstance();
        int thisDate = calendar.get(Calendar.DATE);
        int thisMonth = calendar.get(Calendar.MONTH)+1;
        int thisYear = calendar.get(Calendar.YEAR);
        binding.tvUpdateDay.setText(thisDate + "/" + thisMonth + "/" + thisYear);
    }

    private void callJsonApiWorl() {
        JsonApi.jsonStatisticWorld.covid19("IwAR0qm_amo5CLruLfQsc5bP5zehpD3L8o1aVJGNrkLxl0ugQA8QUumUPD-RM", "a", "a", "a")
                .enqueue(new retrofit2.Callback<Covid19>() {
                    @Override
                    public void onResponse(Call<Covid19> call, Response<Covid19> response) {
                        Covid19 covid19 = response.body();
                        if (covid19 != null) {
                            binding.numberCasesWorld.setText(covid19.getCases());
                            binding.numberDeathWorld.setText(covid19.getDeaths());
                            binding.numberRecorvedWorld.setText(covid19.getRecovered());
                        }
                    }

                    @Override
                    public void onFailure(Call<Covid19> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.error_network, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void callJsonApiVN() {
        JsonApi.jsontatisticVN.covid19VN("IwAR3mEgc1zKNa53GglbAFKmXo5nnWdIDUj10qMFWV7GRxziyvyKTF_PGuMOk", "a", "a", "a", "a")
                .enqueue(new retrofit2.Callback<List<Covid19VN>>() {
                    @Override
                    public void onResponse(Call<List<Covid19VN>> call, Response<List<Covid19VN>> response) {
                        List<Covid19VN> covid19VNList = response.body();
                        if (covid19VNList != null) {
                            for (Covid19VN covid19VN : covid19VNList) {
                                if (covid19VN.getCountry().equals("Vietnam")) {
                                    binding.numberCases.setText(covid19VN.getCases());
                                    binding.numberDeath.setText(covid19VN.getDeaths());
                                    binding.numberRecorved.setText(covid19VN.getRecovered());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Covid19VN>> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.error_network, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void extentDetail() {
        binding.detailsWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(detailWorldlink)));
            }
        });
        binding.detailsVietnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(detailVNlink)));
            }
        });
        binding.openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapCovid)));
            }
        });
    }
}
