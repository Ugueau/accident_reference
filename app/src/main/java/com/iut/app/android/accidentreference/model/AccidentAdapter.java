package com.iut.app.android.accidentreference.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.app.android.accidentreference.R;

import java.util.ArrayList;

public class AccidentAdapter extends BaseAdapter {

    private ArrayList<Record> accidentsList = null;

    public void setAccidentList(ArrayList<Record> accidentsList) {
        this.accidentsList = accidentsList;
    }

    @Override
    public int getCount() {
        return accidentsList.size();
    }

    @Override
    public Object getItem(int i) {
        return accidentsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ConstraintLayout layout;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (view == null) {
            layout = (ConstraintLayout) inflater.inflate(R.layout.adaptater_item_accident, viewGroup, false);
        } else {
            layout = (ConstraintLayout) view;
        }

        Record recorded = accidentsList.get(i);

        TextView tvSeverity = layout.findViewById(R.id.severity);
        TextView tvInvolvedPerson = layout.findViewById(R.id.involved_person);
        TextView tvDepartment = layout.findViewById(R.id.department);
        TextView tvRoadType = layout.findViewById(R.id.road_type);
        ImageView ivFav = layout.findViewById(R.id.fav_star);

        if(AccidentList.getInstance().isAlreadyIn(recorded.getRecordid())) {
            ivFav.setImageDrawable(ivFav.getResources().getDrawable(R.drawable.fulled_star));
        }else{
            ivFav.setImageDrawable(ivFav.getResources().getDrawable(R.drawable.empty_star));
        }
        float grav = recorded.getFields().getGrav();
        tvSeverity.setText(Float.toString(grav));

        tvInvolvedPerson.setText(Integer.toString(recorded.getFields().getNbimplique() ));

        tvDepartment.setText(recorded.getFields().getDepartement());

        tvRoadType.setText(recorded.getFields().getCategorieDeRoute());

        return layout;
    }
}
