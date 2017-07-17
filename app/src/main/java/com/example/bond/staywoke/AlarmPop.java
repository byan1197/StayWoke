package com.example.bond.staywoke;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/**
 * Created by bond on 27/06/17.
 */

public class AlarmPop extends Activity {

    Alarm alarm;
    Bundle extras;
    int reason;
    TimePicker tp;
    ToggleButton[] dotw= new ToggleButton[7];
    Spinner spinner;
    ArrayAdapter adapter;
    EditText notesEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmpopwindow);

        //retrieving intents
        extras = getIntent().getExtras();
        int id;
        reason= extras.getInt("reason");
        //0 is add
        //1 is edit
        if (reason == 1){
            id = extras.getInt("id");
        }
        else{
            id = -1;
        }

        //Instance variablezzzz and SET UP
        spinner = (Spinner)findViewById(R.id.puzzleSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.gameArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        DatabaseHelper db = new DatabaseHelper(this);
        notesEdit = (EditText) findViewById(R.id.notesField);
        String repeatStr = "";

        getWindow().setLayout((int)(width*.9), (int)(height*.9));
        Button saveButton = (Button) findViewById(R.id.saveBtn);
        Button exitButton = (Button) findViewById(R.id.exitBtn);
        tp = (TimePicker) findViewById(R.id.clockPicker);
        dotw [0] = (ToggleButton)findViewById(R.id.su);
        dotw [1] = (ToggleButton)findViewById(R.id.mo);
        dotw [2] = (ToggleButton)findViewById(R.id.tu);
        dotw [3] = (ToggleButton)findViewById(R.id.we);
        dotw [4] = (ToggleButton)findViewById(R.id.th);
        dotw [5] = (ToggleButton)findViewById(R.id.fr);
        dotw [6] = (ToggleButton)findViewById(R.id.sa);

        //set toggles
        if (reason == 1){
            Cursor res = db.getAllData();
            res.moveToFirst();
            for (int i = 0; i < id; i++){
                res.moveToNext();
            }

            repeatStr=res.getString(3);
            if (repeatStr.contains("Sun")){
                dotw[0].setChecked(true);
            }
            if (repeatStr.contains("Mon")){
                dotw[1].setChecked(true);
            }
            if (repeatStr.contains("Tu")){
                dotw[2].setChecked(true);
            }
            if (repeatStr.contains("Wed")){
                dotw[3].setChecked(true);
            }
            if (repeatStr.contains("Thu")){
                dotw[4].setChecked(true);
            }
            if (repeatStr.contains("Fri")){
                dotw[5].setChecked(true);
            }
            if (repeatStr.contains("Sat")){
                dotw[6].setChecked(true);
            }
            tp.setHour(res.getInt(1));
            tp.setMinute(res.getInt(2));
            spinner.setSelection(res.getInt(5));
            notesEdit.setText(res.getString(6));
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
                    repeatToSend += "Sun ";
                    noneChecked = false;
                }
                if (dotw[1].isChecked()){
                    repeatToSend += "Mon ";
                    noneChecked = false;
                }
                if (dotw[2].isChecked()){
                    repeatToSend += "Tue ";
                    noneChecked = false;
                }
                if (dotw[3].isChecked()){
                    repeatToSend += "Wed ";
                    noneChecked = false;
                }
                if (dotw[4].isChecked()){
                    repeatToSend += "Thu ";
                    noneChecked = false;
                }
                if (dotw[5].isChecked()){
                    repeatToSend += "Fri ";
                    noneChecked = false;
                }
                if (dotw[6].isChecked()){
                    repeatToSend += "Sat ";
                    noneChecked = false;
                }
                if (noneChecked){
                    repeatToSend = "Only Once";
                }
                alarm.setRepeat(repeatToSend);

                Intent returnIntent = new Intent();
                if (reason == 1){
                    returnIntent.putExtra("id", extras.getInt("id"));
                }
                returnIntent.putExtra("alarm", alarm);
                returnIntent.putExtra("spinner", spinner.getSelectedItemPosition());
                returnIntent.putExtra("note", String.valueOf(notesEdit.getText()));
                setResult(Activity.RESULT_OK, returnIntent);
                AlarmPop.this.finish();
            }
        });

    }

    //Minimizes keyboard when touch on screen
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


}
