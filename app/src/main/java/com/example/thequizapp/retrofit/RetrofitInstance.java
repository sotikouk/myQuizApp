package com.example.thequizapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    // In emulators must use the ip address 10.0.2.2
    String baseUrl = "http://10.0.2.2:8080/api/";

    // Create and return a configured retrofit instance
    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
