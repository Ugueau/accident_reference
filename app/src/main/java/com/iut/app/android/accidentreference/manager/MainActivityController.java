package com.iut.app.android.accidentreference.manager;


import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.iut.app.android.accidentreference.model.Accidents;
import com.iut.app.android.accidentreference.model.Department;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityController {

    private final ApiManager apiManager;
    private int rows;
    private int start;

    private int range;

    private Double lat;

    private Double lon;
    public boolean locationIsActive;

    public Department chosenDep;
    private static MainActivityController instance;

    private MainActivityController() {
        apiManager = ApiManager.getInstance();
        range = 50000;
        rows = 20;
        start = 0;
        locationIsActive = true;
        chosenDep = null;
    }

    public static MainActivityController getInstance(){
        if(instance == null){
            instance = new MainActivityController();
        }
        return instance;
    }

    public void getAccidents(IAccidentDataManagerCallBack callBack) {
        if(start >= 454000){
            return;
        }
        Log.e("range", "Range: "+range);
        String geofilter = Double.toString(lat)+","+Double.toString(lon)+","+Integer.toString(range);
        Call<Accidents> callAccident = apiManager.getAccidentsService().getAccidents(start,rows,geofilter);
        callAccident.enqueue(new Callback<Accidents>() {
            @Override
            public void onResponse(Call<Accidents> call, Response<Accidents> response) {
                if (response.isSuccessful()) {
                    start += 20;
                    Accidents a = response.body();
                    callBack.getAccidentsResponseSuccess(a);

                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getAccidentsError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Accidents> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getAccidentsError("Erreur lors de la requete : " + t.getLocalizedMessage());
            }
        });

    }

    public void reload(){
        start = 0;
    }

    public void changeRange(int newRange){
        range = newRange;
    }

    public void setLocation(Double latitude, Double longitude){
        lat =latitude;
        lon = longitude;
    }

    public LatLng getRegisteredLocation(){
        return new LatLng(lat,lon);
    }

    public void getAccidentsThread(IAccidentDataManagerCallBack callBack) {


        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Accidents> response = apiManager.getAccidentsService().getAccidents().execute();
                    if (response.isSuccessful()) {
                        Accidents a = response.body();
                        Log.e("onResponse", a.getRecords().toString());
                        callBack.getAccidentsResponseSuccess(a);
                    } else {
                        Log.e("onResponse", "Not successfull : " + response.code());
                        callBack.getAccidentsError("Erreur le serveur a repondu status : " + response.code());
                    }

                } catch (IOException e) {
                    Log.e("onResponse", "Not successfull : " + e.getLocalizedMessage());
                    callBack.getAccidentsError("Erreur lors de la requete : " + e.getLocalizedMessage());
                }

            }
        };

        new Thread(r).start();
    }
}