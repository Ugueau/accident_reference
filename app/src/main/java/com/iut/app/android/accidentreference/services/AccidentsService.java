package com.iut.app.android.accidentreference.services;

import com.iut.app.android.accidentreference.model.Accidents;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface AccidentsService {

    //@Headers("Authorization: Bearer 9d9c1a68-5114-31c9-8cc7-022d0810d761")
    @GET("?dataset=accidents-corporels-de-la-circulation-en-france&q=&rows=20&sort=-nbimplique&geofilter.distance=46.205359%2C5.230288%2C10000")
    Call<Accidents> getAccidents();

    @GET("?dataset=accidents-corporels-de-la-circulation-en-france&sort=-nbimplique")
    Call<Accidents> getAccidents(
            @Query("start") int start,
            @Query("rows") int rows,
            @Query("geofilter.distance") String geofilterDistance
    );
}
