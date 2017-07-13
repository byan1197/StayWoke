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
import android.widget.TextClock;
import android.widget.TextView;
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
    Bundle bundle;
    Boolean isAM = true;
    TextView clockTV, repeatTV;
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
        clockTV = (TextView) view.findViewById(R.id.displayClock);
        repeatTV = (TextView) view.findViewById(R.id.displayRepeat);

        //bundle
        bundle = getArguments();
        if (bundle!= null){
            int hours = -1;
            if (bundle.getInt("hours") >12){
                hours = bundle.getInt("hours") -12;
                isAM = false;
            }else{
                isAM = true;
            }
            if (hours <= 0){
               hours = 12;
            }

            String minutes = "";
            if (bundle.getInt("minutes")<10)
                minutes = "0";
            minutes+=String.valueOf(bundle.getInt("minutes"));
            if(isAM){
                minutes+=" AM";
            }
            else
                minutes+=" PM";
            clockTV.setText(String.valueOf(hours)+":"+minutes);
            repeatTV.setText(bundle.getString("repeat"));
        }
        onOffSwitch.setChecked(true);
        onDeleteAlarmListener.onOff(bundle.getInt("id"), true, bundle.getInt("hours"), bundle.getInt("minutes"), bundle.getString("repeat"));

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
                onDeleteAlarmListener.onOff(bundle.getInt("id"), b, bundle.getInt("hours"), bundle.getInt("minutes"), bundle.getString("repeat"));
            }
        });

        return view;
    }

    public interface OnDeleteAlarmListener{
        public void deleteAlarm(AlarmFragment afrag);
        public void editAlarm(AlarmFragment afrag);
        public void onOff(int id, boolean b, int hours, int minutes, String repeat);
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
