package com.example.bond.staywoke;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<AlarmFragment> alarmList = new ArrayList<AlarmFragment>();
    List<ExpandedAlarmFragment> expandedAlarmList = new ArrayList<ExpandedAlarmFragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance variables
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ImageButton addAlarm = (ImageButton)findViewById(R.id.addAlarmBtn);
        ListView alarmLV = (ListView) findViewById(R.id.fragmentContainer);


        //Listeners
        addAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmFragment current = new AlarmFragment();
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragmentContainer, current);
                alarmList.add(current);
                expandedAlarmList.add(new ExpandedAlarmFragment);
                fragmentTransaction.commit();
            }
        });

        alarmLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                AlarmFragment current = alarmList.get(i);
                fragmentTransaction.replace();

            }
        });

    }


}
