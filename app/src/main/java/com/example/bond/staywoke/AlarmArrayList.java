package com.example.bond.staywoke;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bond on 10/07/17.
 */

public class AlarmArrayList implements Serializable{
    ArrayList<Alarm> alarmList;
    public AlarmArrayList(){
        alarmList=new ArrayList<>();
    }
    public Alarm getAlarm(int pos){
        return alarmList.get(pos);
    }
    public void removeAlarm(int pos){
        alarmList.remove(pos);
    }
    public void addAlarm(Alarm alarm){
        alarmList.add(alarm);
    }
    public void setAlarm(int pos, Alarm alarm){
        alarmList.set(pos, alarm);
    }

    public boolean isEmpty(){
        if (alarmList.isEmpty())
            return true;
        return false;
    }
}
