package com.example.bond.staywoke;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by bond on 12/07/17.
 */

public class RingtonePlayingService extends Service{


    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.


    MediaPlayer mediaSong;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        mediaSong = MediaPlayer.create(this, R.raw.ring);
        mediaSong.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.


        // Tell the user we stopped.
        Toast.makeText(this, "ON Destroy Called", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
