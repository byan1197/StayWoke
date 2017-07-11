package com.example.bond.staywoke;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/**
 * Created by bond on 26/06/17.
 */

public class AlarmFragment extends Fragment {

    OnDeleteAlarmListener onDeleteAlarmListener;
    AlarmFragment current = this;
    ImageButton editButton, deleteButton;
    Alarm alarm;
    TimePicker tp;
    DatabaseHelper db;
    boolean save = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.alarmfragment, container, false);

        //Instance varz
        db = new DatabaseHelper(view.getContext());
        editButton = (ImageButton) view.findViewById(R.id.editBtn);
        deleteButton = (ImageButton) view.findViewById(R.id.deleteBtn);
        Switch onOffSwitch = (Switch) view.findViewById(R.id.onoffsw);


        //LISTENERS
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteAlarmListener.editAlarm(current);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteAlarmListener.deleteAlarm(current);
            }
        });

        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        return view;
    }

    public void setData(int time, String repeat) {

    }

    public interface OnDeleteAlarmListener{
        public void deleteAlarm(AlarmFragment afrag);
        public void editAlarm(AlarmFragment afrag);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onDeleteAlarmListener= (OnDeleteAlarmListener)context;
        } catch(Exception e){
        }
    }
}
