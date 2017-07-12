package com.example.bond.staywoke;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

        //retrieving intents
        Bundle extras = getIntent().getExtras();
        int id;
        int reason= extras.getInt("reason");
        //0 is add
        //1 is edit
        if (reason == 1){
            id = extras.getInt("id");
        }
        else{
            id = -1;
        }

        //Instance variablezzzz and SET UP
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        DatabaseHelper db = new DatabaseHelper(this);
        String repeatStr = "";

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

        //set toggles
        if (reason == 1){
            Cursor res = db.getAllData();
            res.moveToFirst();
            for (int i = 0; i < id; i++){
                res.moveToNext();
            }

            repeatStr=res.getString(4);
            if (repeatStr.contains("Sun")){
                dotw[0].toggle();
            }
            if (repeatStr.contains("Mon")){
                dotw[1].toggle();
            }
            if (repeatStr.contains("Tu")){
                dotw[2].toggle();
            }
            if (repeatStr.contains("Wed")){
                dotw[3].toggle();
            }
            if (repeatStr.contains("Thu")){
                dotw[4].toggle();
            }
            if (repeatStr.contains("Fri")){
                dotw[5].toggle();
            }
            if (repeatStr.contains("Sat")){
                dotw[6].toggle();
            }
        }

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
                String repeatToSend = "";
                boolean noneChecked = true;
                alarm= new Alarm();
                alarm.setHours(tp.getHour());
                alarm.setMinutes(tp.getMinute());
                alarm.setOnOff("on");
                if (dotw[0].isChecked()){
                    repeatToSend = "Sun ";
                    noneChecked = false;
                }
                if (dotw[1].isChecked()){
                    repeatToSend = "Mon ";
                    noneChecked = false;
                }
                if (dotw[2].isChecked()){
                    repeatToSend = "Tue ";
                    noneChecked = false;
                }
                if (dotw[3].isChecked()){
                    repeatToSend = "Wed ";
                    noneChecked = false;
                }
                if (dotw[4].isChecked()){
                    repeatToSend = "Thurs ";
                    noneChecked = false;
                }
                if (dotw[5].isChecked()){
                    repeatToSend = "Fri ";
                    noneChecked = false;
                }
                if (dotw[6].isChecked()){
                    repeatToSend = "Sat ";
                    noneChecked = false;
                }
                if (noneChecked){
                    repeatToSend = "Only Once";
                }
                alarm.setRepeat(repeatToSend);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("alarm", alarm);
                setResult(Activity.RESULT_OK, returnIntent);
                AlarmPop.this.finish();
            }
        });

    }


}
