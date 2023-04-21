package com.iut.app.android.accidentreference.model;

import com.iut.app.android.accidentreference.services.AccidentsService;

import java.util.ArrayList;

public class AccidentList {
    private static ArrayList<Record> list;

    private static ArrayList<Record> fav_list;

    private static AccidentList instance;

    private AccidentList(){
        list = new ArrayList<>();
        fav_list = new ArrayList<>();
    }

    public static AccidentList getInstance(){
        if(list == null){
            instance = new AccidentList();
        }
        return instance;
    }

    public ArrayList<Record> getList(){
        return list;
    }

    public void push(ArrayList<Record> toAdd){
        list.addAll(toAdd);
    }

    public Record get(int i ){
        return list.get(i);
    }

    public ArrayList<Record> getFavList(){
        return fav_list;
    }

    public void pushIntoFav(ArrayList<Record> toAdd){
        fav_list.addAll(toAdd);
    }

    public void pushOneIntoFav(Record toAdd){
        fav_list.add(toAdd);
    }

    public Record getFav(int i ){
        return fav_list.get(i);
    }

    public void removeFav(String id){
        int i = 0;
        for(Record r : fav_list){
            if(r.getRecordid().equals(id)){
                fav_list.remove(i);
                return;
            }
            i++;
        }
    }

    public Boolean isAlreadyIn(String id){
        for(Record r : fav_list){
            if(r.getRecordid().equals(id)){
                return true;
            }
        }
        return false;
    }
}
