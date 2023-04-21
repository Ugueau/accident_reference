package com.iut.app.android.accidentreference.manager;


import com.iut.app.android.accidentreference.services.AccidentsService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    final static String BASE_URL = "https://public.opendatasoft.com/api/records/1.0/search/";

    private AccidentsService accidentsService = null;

    private static ApiManager instance;

    public AccidentsService getAccidentsService() {
        return accidentsService;
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    private ApiManager() {
        createRetrofitAccident();
    }

    private void createRetrofitAccident() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofitAccident = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        accidentsService = retrofitAccident.create(AccidentsService.class);
    }

}
