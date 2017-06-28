package com.example.bond.staywoke;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bond on 26/06/17.
 */

public class ExpandedAlarmFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.expanded_alarmfragment, container, false);

        return view;
    }
}
