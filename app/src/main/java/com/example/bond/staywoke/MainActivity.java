package com.example.bond.staywoke;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
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
import java.util.List;

public class MainActivity extends AppCompatActivity implements AlarmFragment.OnDeleteAlarmListener{

    DatabaseHelper db;
    AlarmArrayList aal;
    ArrayList<AlarmFragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance variables
        aal = new AlarmArrayList();
        populateAlarms();
        db = new DatabaseHelper(this);
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ImageButton addAlarm = (ImageButton)findViewById(R.id.addAlarmBtn);

        //Listeners
        addAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlarmPop.class);
                startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {
                Alarm resultingAlarm = (Alarm) data.getSerializableExtra("alarm");
                addNewToDB(resultingAlarm);
                aal.addAlarm(resultingAlarm);
                AlarmFragment current = new AlarmFragment();
                fragmentList.add(current);


                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragmentContainer, current);
                fragmentTransaction.commit();

            }
        }
        else if (requestCode == 2){
            if (resultCode == Activity.RESULT_OK) {
                Alarm resultingAlarm = (Alarm) data.getSerializableExtra("alarm");
                int id = data.getIntExtra("id", 0);

                updateFragments();

            }
        }
    }

    private void populateAlarms(){
        Cursor res = db.getAllData();
        if (res.getCount() != 0){

            FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();

            while(res.moveToNext()){
                AlarmFragment current = new AlarmFragment();
                current.setData(res.getInt(1), res.getInt(2), res.getString(3));
                fragmentList.add(current);
                fragmentTransaction.add(R.id.fragmentContainer, current);
            }

            fragmentTransaction.commit();
        }
    }

    private void removeFragments(){
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
        for (AlarmFragment fragment : fragmentList){
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.commit();
    }

    private void updateFragments(){
        removeFragments();
        populateAlarms();
    }

    private void addNewToDB(Alarm alarm){
        boolean isInserted = db.insertData(alarm.getHours(), alarm.getMinutes(), alarm.getRepeat(), alarm.getOnOff());
        if (isInserted)
            Toast.makeText(MainActivity.this, "Alarm has been saved and set.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Something went wrong with saving", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteAlarm(AlarmFragment afrag) {
        int index = fragmentList.indexOf(afrag);
        fragmentList.remove(index);
        aal.removeAlarm(index);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(afrag);
        fragmentTransaction.commit();
    }

    @Override
    public void editAlarm(AlarmFragment afrag) {
        int index = fragmentList.indexOf(afrag);
        Intent intent = new Intent(getApplicationContext(), AlarmPop.class);
        intent.putExtra("id", index);
        startActivityForResult(intent, 2);

    }


}
