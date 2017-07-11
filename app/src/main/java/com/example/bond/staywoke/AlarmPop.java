package com.example.bond.staywoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/**
 * Created by bond on 27/06/17.
 */

public class AlarmPop extends Activity {

    Alarm alarm;
    TimePicker tp;
    ToggleButton[] dotw= new ToggleButton[7];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmpopwindow);

        //Instance variablezzzz and SET UP
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.8));
        Button saveButton = (Button) findViewById(R.id.saveBtn);
        Button exitButton = (Button) findViewById(R.id.exitBtn);
        tp = (TimePicker) findViewById(R.id.clockPicker);
        dotw [0] = findViewById(R.id.su);
        dotw [1] = findViewById(R.id.mo);
        dotw [2] = findViewById(R.id.tu);
        dotw [3] = findViewById(R.id.we);
        dotw [4] = findViewById(R.id.th);
        dotw [5] = findViewById(R.id.fr);
        dotw [6] = findViewById(R.id.sa);

        //Listeners
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                AlarmPop.this.finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //retrieving stuff
                alarm= new Alarm();
                alarm.setHours(tp.getHour());
                alarm.setMinutes(tp.getMinute());
                alarm.setOnOff("on");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("alarm", alarm);
                setResult(Activity.RESULT_OK, returnIntent);
                AlarmPop.this.finish();
            }
        });

    }


}
