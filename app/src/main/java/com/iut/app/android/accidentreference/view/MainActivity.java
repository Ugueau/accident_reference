package com.iut.app.android.accidentreference.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iut.app.android.accidentreference.R;
import com.iut.app.android.accidentreference.manager.IAccidentDataManagerCallBack;
import com.iut.app.android.accidentreference.manager.MainActivityController;
import com.iut.app.android.accidentreference.model.AccidentAdapter;
import com.iut.app.android.accidentreference.model.AccidentList;
import com.iut.app.android.accidentreference.model.Accidents;
import com.iut.app.android.accidentreference.model.Record;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IAccidentDataManagerCallBack {

    private final MainActivityController mainActivityController = MainActivityController.getInstance();
    private final AccidentAdapter adapter = new AccidentAdapter();

    private boolean mode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter.setAccidentList(AccidentList.getInstance().getList());


        this.getLocalisation();

        Log.e("Connection", "Connection : " + this.getConnectionStatus());

        this.getAccidents();
        ListView lv = findViewById(R.id.mainListView);
        lv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.maps_button);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                ArrayList<Double> coord = new ArrayList<>();
                coord.add(mainActivityController.getRegisteredLocation().latitude);
                coord.add(mainActivityController.getRegisteredLocation().longitude);
                i.putExtra("center", coord);
                startActivity(i);
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                getAccidents();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getId() == lv.getId()) {
                    // Calculer la position de l'élément visible le plus bas
                    int lastVisibleItem = firstVisibleItem + visibleItemCount;

                    if (lastVisibleItem == totalItemCount) {
                        // Si l'élément visible le plus bas est le dernier élément, alors le scroll est descendant
                        Log.d("ListView", "Scroll descendant");
                    }
                }
            }
        });

        Switch swt = findViewById(R.id.fav_mode);
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    adapter.setAccidentList(AccidentList.getInstance().getFavList());
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.setAccidentList(AccidentList.getInstance().getList());
                    adapter.notifyDataSetChanged();
                }
                mode = b;
            }
        });

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            if(mode){
                intent.putExtra("accidentInfo", AccidentList.getInstance().getFav(i));
            }else{
                intent.putExtra("accidentInfo", AccidentList.getInstance().get(i));
            }

            startActivity(intent);
        });

        lv.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Record selectedRecord = AccidentList.getInstance().get(i);
            if (!mode) {
                if (!AccidentList.getInstance().isAlreadyIn(selectedRecord.getRecordid())) {
                    AccidentList.getInstance().pushOneIntoFav(selectedRecord);
                    Toast.makeText(this, "Ajouté aux favoris", Toast.LENGTH_SHORT).show();
                } else {
                    AccidentList.getInstance().removeFav(selectedRecord.getRecordid());
                    Toast.makeText(this, "Retiré des favoris", Toast.LENGTH_SHORT).show();
                }
            }
            adapter.notifyDataSetChanged();
            return true;
        });

        FloatingActionButton reload_btn = findViewById(R.id.btn_reload);
        reload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccidentList.getInstance().getList().clear();
                mainActivityController.reload();
                getAccidents();
                adapter.notifyDataSetChanged();
            }
        });


        Button param_btn = findViewById(R.id.parameter_btn);
        param_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PreferenceActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void getAccidents() {
        if (getConnectionStatus()) {
            mainActivityController.getAccidents(this);
        } else {
            Toast.makeText(this, "Connection lost", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAccidentsResponseSuccess(Accidents accidents) {
        AccidentList.getInstance().push(new ArrayList<Record>(accidents.getRecords()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getAccidentsError(String message) {
        Log.e("test error", message);
    }

    private void getLocalisation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Si l'application n'a pas la permission, demander à l'utilisateur de l'accorder
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    321);
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double latitude = 46.306788;
        double longitude = 4.828771;
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        mainActivityController.setLocation(latitude, longitude);
    }

    private Boolean getConnectionStatus() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        return isMobileConn || isWifiConn;
    }
}