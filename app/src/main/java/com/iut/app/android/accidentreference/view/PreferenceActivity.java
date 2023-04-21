package com.iut.app.android.accidentreference.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.iut.app.android.accidentreference.R;
import com.iut.app.android.accidentreference.manager.MainActivityController;
import com.iut.app.android.accidentreference.model.AccidentList;
import com.iut.app.android.accidentreference.model.Department;

import java.util.ArrayList;

public class PreferenceActivity extends AppCompatActivity {

    private final MainActivityController mainActivityController = MainActivityController.getInstance();
    private int range = 50000;
    private ArrayList<Department> departmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        initDepartmentList();
        TextView tvIndex = findViewById(R.id.range_index);
        tvIndex.setText((range / 1000) + "km");
        SeekBar seekBar = findViewById(R.id.range);
        seekBar.setMax(500);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                range = i * 1000;
                tvIndex.setText(i + "km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mainActivityController.changeRange(range);
            }
        });

        CheckBox cbLocation = findViewById(R.id.checkBox);
        cbLocation.setChecked(mainActivityController.locationIsActive);

        cbLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mainActivityController.locationIsActive = b;
                if (mainActivityController.locationIsActive) {
                    getLocalisation();
                    mainActivityController.reload();
                    AccidentList.getInstance().getList().clear();
                }else{
                    mainActivityController.setLocation(mainActivityController.chosenDep.latitude, mainActivityController.chosenDep.longitude);
                    mainActivityController.reload();
                    AccidentList.getInstance().getList().clear();
                }
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<Department> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(mainActivityController.chosenDep != null) {
            int i = 0;
            int position = 0;
            for(Department d : departmentList){
                if(d.name.equals(mainActivityController.chosenDep.name)){
                    position = i;
                }
                i++;
            }
            spinner.setSelection(position);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mainActivityController.chosenDep = departmentList.get(i);
                if (mainActivityController.locationIsActive) {
                    getLocalisation();
                    mainActivityController.reload();
                    AccidentList.getInstance().getList().clear();
                } else {
                    mainActivityController.setLocation(mainActivityController.chosenDep.latitude, mainActivityController.chosenDep.longitude);
                    mainActivityController.reload();
                    AccidentList.getInstance().getList().clear();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    private void initDepartmentList() {
        departmentList = new ArrayList<>();
        //departmentList.add(new Department(0, "Default", 0.0, 0.0));
        departmentList.add(new Department(1, "Ain", 46.1500, 5.5500));
        departmentList.add(new Department(2, "Aisne", 49.5000, 3.5000));
        departmentList.add(new Department(3, "Allier", 46.1667, 3.0));
        departmentList.add(new Department(4, "Alpes-de-Haute-Provence", 44.0904, 6.2359));
        departmentList.add(new Department(5, "Hautes-Alpes", 44.599, 6.0799));
        departmentList.add(new Department(6, "Alpes-Maritimes", 43.7034, 7.2663));
        departmentList.add(new Department(7, "Ardèche", 44.75, 4.5));
        departmentList.add(new Department(8, "Ardennes", 49.5, 4.5));
        departmentList.add(new Department(9, "Ariège", 43.0, 1.5));
        departmentList.add(new Department(10, "Aube", 48.3333, 4.1667));
        departmentList.add(new Department(11, "Aude", 43.1667, 2.5));
        departmentList.add(new Department(12, "Aveyron", 44.3333, 2.5));
        departmentList.add(new Department(13, "Bouches-du-Rhône", 43.3, 5.4));
        departmentList.add(new Department(14, "Calvados", 49.0, -0.3333));
        departmentList.add(new Department(15, "Cantal", 45.1667, 2.5));
        departmentList.add(new Department(16, "Charente", 45.8333, 0.3333));
        departmentList.add(new Department(17, "Charente-Maritime", 46.0, -1.0));
        departmentList.add(new Department(18, "Cher", 47.0833, 2.5));
        departmentList.add(new Department(19, "Corrèze", 45.3333, 2.0));
        departmentList.add(new Department(20, "Corse", 42.0399, 9.0129));
        departmentList.add(new Department(21, "Côte-d'Or", 47.3333, 4.5833));
        departmentList.add(new Department(22, "Côtes d'Armor", 48.3333, -2.8333));
        departmentList.add(new Department(23, "Creuse", 46.4167, 2.0));
        departmentList.add(new Department(24, "Dordogne", 45.0, 0.5));
        departmentList.add(new Department(25, "Doubs", 47.0, 6.0));
        departmentList.add(new Department(26, "Drôme", 44.75, 5.0));
        departmentList.add(new Department(27, "Eure", 49.0, 1.5));
        departmentList.add(new Department(28, "Eure-et-Loir", 48.3333, 1.5));
        departmentList.add(new Department(29, "Finistère", 48.3333, -4.1667));
        departmentList.add(new Department(30, "Gard", 44.1667, 4.1667));
        departmentList.add(new Department(31, "Haute-Garonne", 43.6045, 1.4442));
        departmentList.add(new Department(32, "Gers", 43.7619, 0.6057));
        departmentList.add(new Department(33, "Gironde", 44.8378, -0.5792));
        departmentList.add(new Department(34, "Hérault", 43.5912, 3.2584));
        departmentList.add(new Department(35, "Ille-et-Vilaine", 48.1147, -1.6842));
        departmentList.add(new Department(36, "Indre", 46.7937, 1.7084));
        departmentList.add(new Department(37, "Indre-et-Loire", 47.1924, 0.8614));
        departmentList.add(new Department(38, "Isère", 45.3483, 5.7603));
        departmentList.add(new Department(39, "Jura", 46.7441, 5.6266));
        departmentList.add(new Department(40, "Landes", 43.9112, -0.9791));
        departmentList.add(new Department(41, "Loir-et-Cher", 47.6156, 1.3308));
        departmentList.add(new Department(42, "Loire", 45.7976, 4.0857));
        departmentList.add(new Department(43, "Haute-Loire", 45.0609, 3.8679));
        departmentList.add(new Department(44, "Loire-Atlantique", 47.2667, -1.5492));
        departmentList.add(new Department(45, "Loiret", 47.9022, 2.1075));
        departmentList.add(new Department(46, "Lot", 44.6139, 1.6959));
        departmentList.add(new Department(47, "Lot-et-Garonne", 44.2365, 0.4426));
        departmentList.add(new Department(48, "Lozère", 44.4681, 3.7008));
        departmentList.add(new Department(49, "Maine-et-Loire", 47.4739, -0.5975));
        departmentList.add(new Department(50, "Manche", 49.1155, -1.3030));
        departmentList.add(new Department(51, "Marne", 49.1423, 4.4642));
        departmentList.add(new Department(52, "Haute-Marne", 48.0054, 5.4471));
        departmentList.add(new Department(53, "Mayenne", 48.3039, -0.5939));
        departmentList.add(new Department(54, "Meurthe-et-Moselle", 48.8059, 6.1097));
        departmentList.add(new Department(55, "Meuse", 49.0431, 5.3667));
        departmentList.add(new Department(56, "Morbihan", 47.7186, -2.9380));
        departmentList.add(new Department(57, "Moselle", 49.1198, 6.1765));
        departmentList.add(new Department(58, "Nièvre", 47.0009, 3.4028));
        departmentList.add(new Department(59, "Nord", 50.6105, 3.1360));
        departmentList.add(new Department(60, "Oise", 49.4167, 2.4167));
        departmentList.add(new Department(61, "Orne", 48.5833, -0.1667));
        departmentList.add(new Department(62, "Pas-de-Calais", 50.4833, 2.6667));
        departmentList.add(new Department(63, "Puy-de-Dôme", 45.75, 3.0833));
        departmentList.add(new Department(64, "Pyrénées-Atlantiques", 43.25, -0.5833));
        departmentList.add(new Department(65, "Hautes-Pyrénées", 43.1667, 0.1667));
        departmentList.add(new Department(66, "Pyrénées-Orientales", 42.5, 2.75));
        departmentList.add(new Department(67, "Bas-Rhin", 48.5833, 7.75));
        departmentList.add(new Department(68, "Haut-Rhin", 47.75, 7.3333));
        departmentList.add(new Department(69, "Rhône", 45.75, 4.8333));
        departmentList.add(new Department(70, "Haute-Saône", 47.5, 6.1667));
        departmentList.add(new Department(71, "Saône-et-Loire", 46.8333, 4.5));
        departmentList.add(new Department(72, "Sarthe", 47.8333, 0.1667));
        departmentList.add(new Department(73, "Savoie", 45.5667, 6.3333));
        departmentList.add(new Department(74, "Haute-Savoie", 46.0, 6.5));
        departmentList.add(new Department(75, "Paris", 48.8567, 2.3508));
        departmentList.add(new Department(76, "Seine-Maritime", 49.5, 1.0));
        departmentList.add(new Department(77, "Seine-et-Marne", 48.5833, 2.6667));
        departmentList.add(new Department(78, "Yvelines", 48.75, 1.9167));
        departmentList.add(new Department(79, "Deux-Sèvres", 46.3333, -0.4167));
        departmentList.add(new Department(80, "Somme", 49.9167, 2.3333));
        departmentList.add(new Department(81, "Tarn", 43.8333, 2.1667));
        departmentList.add(new Department(82, "Tarn-et-Garonne", 44.0833, 1.3333));
        departmentList.add(new Department(83, "Var", 43.4167, 6.6667));
        departmentList.add(new Department(84, "Vaucluse", 43.9167, 5.1667));
        departmentList.add(new Department(85, "Vendée", 46.6667, -1.5));
        departmentList.add(new Department(86, "Vienne", 46.5833, 0.3333));
        departmentList.add(new Department(87, "Haute-Vienne", 45.8333, 1.25));
        departmentList.add(new Department(88, "Vosges", 48.1667, 6.5));
        departmentList.add(new Department(89, "Yonne", 47.7986, 3.5669));
        departmentList.add(new Department(90, "Territoire de Belfort", 47.6374, 6.8626));
        departmentList.add(new Department(91, "Essonne", 48.4682, 2.3264));
        departmentList.add(new Department(92, "Hauts-de-Seine", 48.8966, 2.1638));
        departmentList.add(new Department(93, "Seine-Saint-Denis", 48.9135, 2.4999));
        departmentList.add(new Department(94, "Val-de-Marne", 48.7798, 2.4345));
        departmentList.add(new Department(95, "Val-d'Oise", 49.0790, 2.3086));
        departmentList.add(new Department(201, "Corse-du-Sud", 41.9352, 8.8921));
        departmentList.add(new Department(202, "Haute-Corse", 42.3492, 9.4416));
    }
}