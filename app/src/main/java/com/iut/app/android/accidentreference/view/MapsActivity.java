package com.iut.app.android.accidentreference.view;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.iut.app.android.accidentreference.R;
import com.iut.app.android.accidentreference.databinding.ActivityMapsBinding;
import com.iut.app.android.accidentreference.manager.MainActivityController;
import com.iut.app.android.accidentreference.model.AccidentList;
import com.iut.app.android.accidentreference.model.Record;
import com.iut.app.android.accidentreference.view.DetailActivity;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ClusterManager<Record> clusterManager;
    private Record oneDetailPoint;
    private LatLng localisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<Double> coord = (ArrayList<Double>)getIntent().getSerializableExtra("center");
        oneDetailPoint = (Record) getIntent().getSerializableExtra("accidentToShow");

        localisation = new LatLng(coord.get(0),coord.get(1));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        clusterManager = new ClusterManager<Record>(this, googleMap);
        if(oneDetailPoint != null){
            createClusterOfOne();
        }else{
            createCluster();
        }

        // Centrer la carte sur Paris avec un niveau de zoom de 10
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localisation, 12));

    }



    private void createCluster(){
        mMap.clear();
        clusterManager.clearItems();
        for (Record accident: AccidentList.getInstance().getList()) {
            clusterManager.addItem(accident);
        }

        clusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<Record>() {
            @Override
            public void onClusterItemInfoWindowClick(Record item) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("accidentInfo",item);
                startActivity(intent);
            }
        });

        mMap.setOnMarkerClickListener(clusterManager);
        mMap.setOnCameraIdleListener(clusterManager);

    }

    private void createClusterOfOne(){
        mMap.clear();
        clusterManager.clearItems();
        clusterManager.addItem(oneDetailPoint);

        mMap.setOnMarkerClickListener(clusterManager);
        mMap.setOnCameraIdleListener(clusterManager);

    }

}