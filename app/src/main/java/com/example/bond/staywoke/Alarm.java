package com.example.bond.staywoke;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by bond on 29/06/17.
 */

@SuppressWarnings("serial")
public class Alarm implements Serializable{
    int hours, minutes;
    private String repeat, onOff;
    DatabaseHelper db;

    public Alarm(int hours, int minutes){
        repeat = "";
        onOff = "";
        this.hours = hours;
        this.minutes = minutes;
    }
    public Alarm(){
        repeat = "";
        onOff = "";
    }

    public void setHours(int hours){
        this.hours = hours;
    }
    public void setMinutes(int minutes){
        this.minutes = minutes;
    }
    public void setRepeat(String repeat){
        this.repeat=repeat;
    }
    public void setOnOff(String onOff){
        this.onOff = onOff;
    }

    public int getHours(){
        return hours;
    }
    public int getMinutes(){
        return minutes;
    }
    public String getRepeat(){
        return repeat;
    }
    public String getOnOff(){
        return onOff;
    }

}
