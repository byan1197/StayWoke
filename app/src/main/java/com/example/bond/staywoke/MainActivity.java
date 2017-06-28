package com.example.bond.staywoke;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AlarmFragment.OnDeleteAlarmListener{

    List<AlarmFragment> alarmList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance variables
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ImageButton addAlarm = (ImageButton)findViewById(R.id.addAlarmBtn);

        //Listeners
        addAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmFragment current = new AlarmFragment();
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragmentContainer, current);
                alarmList.add(current);
                fragmentTransaction.commit();
            }
        });


    }


    @Override
    public void deleteAlarm(AlarmFragment afrag) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(afrag);
        fragmentTransaction.commit();
    }
}
