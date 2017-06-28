package com.example.bond.staywoke;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * Created by bond on 26/06/17.
 */

public class AlarmFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.alarmfragment, container, false);

        //Instance varz
        ImageButton editButton = (ImageButton) view.findViewById(R.id.editBtn);
        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteBtn);
        Switch onOffSwitch = (Switch) view.findViewById(R.id.onoffsw);

        //LISTENERS
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AlarmFragment.this.getActivity(), AlarmPop.class));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
