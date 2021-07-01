package com.example.hauizone.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface JsonApi {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    //call json statistic in vn
    JsonApi jsontatisticVN=new Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(JsonApi.class);
    @GET("countries")
    Call<List<Covid19VN>> covid19VN(@Query("fbclid") String access_key,
                                    @Query("country") String country,
                                    @Query("cases") String cases,
                                    @Query("deaths") String deaths,
                                    @Query("recovered") String recovered);


    //call json statistic world
    JsonApi jsonStatisticWorld=new Retrofit.Builder()
            .baseUrl("https://disease.sh/v3/covid-19/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(JsonApi.class);


    @GET("all")
    Call<Covid19> covid19(@Query("fbclid") String access_key,
                          @Query("cases") String cases,
                          @Query("deaths") String deaths,
                          @Query("recovered") String recovered);

}
