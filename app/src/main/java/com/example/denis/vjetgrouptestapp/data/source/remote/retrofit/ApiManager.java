package com.example.denis.vjetgrouptestapp.data.source.remote.retrofit;

import com.example.denis.vjetgrouptestapp.data.constants.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiManager sInstance;

    private ApiService mApiService;

    private ApiManager() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApiService = retrofit.create(ApiService.class);
    }

    public static ApiManager getInstance() {
        if (sInstance == null) {
            sInstance = new ApiManager();
        }
        return sInstance;
    }


    public ApiService getService() {
        return mApiService;
    }
}

