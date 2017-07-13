package com.example.bond.staywoke;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by bond on 12/07/17.
 */

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("We made it here");

        boolean isOn = intent.getExtras().getBoolean("isOn");
        if (isOn)
            System.out.println("IN ALARM RECEIVER: IT IS ON");
        else
            System.out.println("IN ALARM RECEIVER: IT IS OFF");


        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);
        serviceIntent.putExtra("isDisabled", isOn);
        context.startService(serviceIntent);



    }
}
