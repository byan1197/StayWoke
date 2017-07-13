package com.example.bond.staywoke;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AlarmFragment.OnDeleteAlarmListener{

    DatabaseHelper db;
    Calendar cal;
    ArrayList<AlarmFragment> fragmentList = new ArrayList<>();
    PendingIntent pendingIntent;
    Context context;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance variables
        this.context=this;
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        db = new DatabaseHelper(this);
        populateAlarms();
        final FragmentManager fragmentManager = getFragmentManager();
        cal = Calendar.getInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //ImageButton addAlarm = (ImageButton)findViewById(R.id.addAlarmBtn);
        FloatingActionButton addAlarm = (FloatingActionButton) findViewById(R.id.addFAB);

        //Listeners
        addAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlarmPop.class);
                //if reason is 0, that means add
                intent.putExtra("reason", 0);
                startActivityForResult(intent, 1);//1 is add
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data!= null && requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {

                Alarm resultingAlarm = (Alarm) data.getSerializableExtra("alarm");
                addNewToDB(resultingAlarm, data.getExtras().getInt("spinner"));
                updateFragments();
            }
        }
        else if (data!= null && requestCode == 2){
            if (resultCode == Activity.RESULT_OK) {
                Alarm resultingAlarm = (Alarm) data.getSerializableExtra("alarm");
                int id = data.getExtras().getInt("id");
                System.out.println("alarm time: "+ String.valueOf(resultingAlarm.getHours())+":"+String.valueOf(resultingAlarm.getHours()));
                System.out.println("id is: "+ String.valueOf(id));

                Cursor res = db.getAllData();
                res.moveToFirst();
                for (int i =0; i <id; i++){
                    res.moveToNext();
                }
                db.updateTime(res.getInt(0), resultingAlarm.getHours(), resultingAlarm.getMinutes(), resultingAlarm.getRepeat(), res.getInt(5));
                updateFragments();
            }
        }
    }

    private void populateAlarms(){
        Cursor res = db.getAllData();
        if (res == null)
            res.moveToFirst();
        if (res.getCount() != 0){
            while(res.moveToNext()){
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                AlarmFragment current = new AlarmFragment();
                fragmentList.add(current);
                Bundle bundle = new Bundle();
                bundle.putInt("id", res.getInt(0));
                bundle.putInt("hours", res.getInt(1));
                System.out.println("HOURS FROM MAIN: "+res.getInt(1));
                bundle.putInt("minutes", res.getInt(2));
                bundle.putString("repeat", res.getString(3));
                bundle.putString("onoff", res.getString(4));
                bundle.putInt("game", res.getInt(5));
                current.setArguments(bundle);
                fragmentTransaction.add(R.id.fragmentContainer, current);
                fragmentTransaction.commit();
            }
        }
        res.close();
    }

    private void removeFragments(){
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
        for (AlarmFragment fragment : fragmentList){
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.commit();
        fragmentList.clear();
    }

    private void updateFragments(){
        removeFragments();
        populateAlarms();
    }

    private void addNewToDB(Alarm alarm, int spinner){
        boolean isInserted = db.insertData(alarm.getHours(), alarm.getMinutes(), alarm.getRepeat(), alarm.getOnOff(), spinner);
        if (isInserted)
            Toast.makeText(MainActivity.this, "Alarm has been saved and set.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Something went wrong with saving", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteAlarm(AlarmFragment afrag) {
        int index = fragmentList.indexOf(afrag);
        System.out.println(index);
        fragmentList.remove(index);
        Cursor res = db.getAllData();
        res.moveToFirst();
        for (int i =0; i <index; i++){
            res.moveToNext();
        }
        db.deleteData(res.getInt(0));
        //TODO make a toast
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(afrag);
        fragmentTransaction.commit();
    }

    @Override
    public void editAlarm(AlarmFragment afrag) {

        int index = fragmentList.indexOf(afrag);
        Intent intent = new Intent(getApplicationContext(), AlarmPop.class);
        //if reason is 1, that means edit
        intent.putExtra("reason", 1);
        intent.putExtra("id", index);
        startActivityForResult(intent, 2);//2 is edit

    }

    @Override
    public void onOff(int id, boolean b, int hours, int minutes, String repeat, int spinPos) {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("spinner",spinPos);
        System.out.println("IN MAIN, SPINNER IS: "+spinPos);
        alarmIntent.putExtra("isOn", b);
        if (b){
            db.updateOnOff(id, "on");
            cal.set(Calendar.HOUR_OF_DAY, hours);
            cal.set(Calendar.MINUTE, minutes);

            //Intent to the AlarmReceiver
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        }
        if(!b){
            sendBroadcast(alarmIntent);
            db.updateOnOff(id, "off");
            alarmManager.cancel(pendingIntent);
        }
    }


}
