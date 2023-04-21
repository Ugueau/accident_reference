package com.iut.app.android.accidentreference.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iut.app.android.accidentreference.R;
import com.iut.app.android.accidentreference.model.AccidentList;
import com.iut.app.android.accidentreference.model.Record;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Record record = (Record) getIntent().getSerializableExtra("accidentInfo");

        TextView severity = findViewById(R.id.severityDetail);
        severity.setText(Float.toString(record.getFields().getGrav()));
        TextView involved = findViewById(R.id.involvedDetail);
        involved.setText(Integer.toString(record.getFields().getNbimplique()));
        TextView dead = findViewById(R.id.dead);
        dead.setText(Integer.toString(record.getFields().getTtue()));
        TextView hospitalize = findViewById(R.id.hospitalize);
        hospitalize.setText(Integer.toString(record.getFields().getTbg()));
        TextView hurted = findViewById(R.id.hurted);
        hurted.setText(Integer.toString(record.getFields().getTbl()));

        TextView road = findViewById(R.id.road_type_detail);
        road.setText(record.getFields().getCategorieDeRoute());

        TextView light = findViewById(R.id.light);
        light.setText(record.getFields().getLumiere());

        TextView departement = findViewById(R.id.departmentDetail);
        departement.setText(record.getFields().getDepartement());

        TextView intersection = findViewById(R.id.intersection);
        intersection.setText(record.getFields().getIntersection());

        TextView collisionType = findViewById(R.id.collision);
        collisionType.setText(record.getFields().getTypeDeCollision());

        TextView agglomeration = findViewById(R.id.agglomeration);
        agglomeration.setText(record.getFields().getAgglomeration());

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                ArrayList<Double> loc = new ArrayList<Double>();
                loc.add(record.getPosition().latitude);
                loc.add(record.getPosition().longitude);

                i.putExtra("center",loc);
                i.putExtra("accidentToShow",record);
                startActivity(i);
            }
        });

        ImageView iv_fav = findViewById(R.id.fav_button);
        Boolean isFav = AccidentList.getInstance().isAlreadyIn(record.getRecordid());
        if(isFav){
            iv_fav.setImageDrawable(getDrawable(R.drawable.fulled_star));
        }
        iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isFav = AccidentList.getInstance().isAlreadyIn(record.getRecordid());
                if (isFav){
                    iv_fav.setImageDrawable(getDrawable(R.drawable.empty_star));
                    AccidentList.getInstance().removeFav(record.getRecordid());
                    Log.e("removefav", "Remove from fav: "+record.getRecordid() );
                }else{
                    iv_fav.setImageDrawable(getDrawable(R.drawable.fulled_star));
                    AccidentList.getInstance().pushOneIntoFav(record);
                    Log.e("addfav", "Add to fav: "+record.getRecordid() );
                }

            }
        });

    }
}