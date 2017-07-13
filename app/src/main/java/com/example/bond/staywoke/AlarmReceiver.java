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

        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);
        context.startService(serviceIntent);

    }
}
