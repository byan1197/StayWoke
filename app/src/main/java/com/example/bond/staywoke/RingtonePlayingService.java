package com.example.bond.staywoke;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by bond on 12/07/17.
 */

public class RingtonePlayingService extends Service{


    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.


    MediaPlayer mediaSong;
    int startId;
    boolean isRunning;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        NotificationManager notifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //SET UP AN INTENT THAT GOES TO THE MAIN ACTIVITY
        Intent gameIntent = new Intent(this.getApplicationContext(), DefaultDisable.class);
        int gameId= intent.getExtras().getInt("spinner");

        if (gameId == 0) {
            gameIntent = new Intent(this.getApplicationContext(), DefaultDisable.class);
        }
        else if (gameId == 1){
            gameIntent = new Intent(this.getApplicationContext(), Trivia.class);
        }
        else if (gameId == 2){
            gameIntent = new Intent(this.getApplicationContext(), MathGame.class);
        }
        else if (gameId == 3){
            gameIntent = new Intent(this.getApplicationContext(), RPS.class);
        }
        else if (gameId == 4){
            gameIntent = new Intent(this.getApplicationContext(), DefaultDisable.class);
        }
        else if (gameId == 5){
            gameIntent = new Intent(this.getApplicationContext(), DefaultDisable.class);
        }


        //Intent intentMainActivity = new Intent(this.getApplicationContext(), MainActivity.class);
        //make the notification parameters
        //set up a pending intent
        PendingIntent pendingIntentMainactivity = PendingIntent.getActivity(this,0,gameIntent,0);

        Notification notificationPopup = new Notification.Builder(this)
                .setContentTitle("An alarm is going off.")
                .setContentText("CLICK TO DISABLE!")
                .setSmallIcon(R.drawable.ic_access_alarms_black_24dp)
                .setContentIntent(pendingIntentMainactivity)
                .setAutoCancel(true)
                .build();
        notifyManager.notify(0,notificationPopup);


        gameIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        boolean state = intent.getExtras().getBoolean("isOn");

        if (state){
            startId=1;
        }
        else if (!state){
            startId=0;
        }
        else{
            startId = 0;
        }

        if (state)
            System.out.println("IN SERVICE: IT IS ON");
        else if (!state)
            System.out.println("IN SERVICE: IT IS OFF");

        mediaSong = MediaPlayer.create(this, R.raw.ring);

        if (!this.isRunning && startId ==1){//start the ringtone
            mediaSong = MediaPlayer.create(this, R.raw.ring);
            mediaSong.start();
            startActivity(gameIntent);
            this.isRunning=true;
            this.startId=0;
        }
        else if (this.isRunning && startId == 0){
            mediaSong.stop();
            mediaSong.reset();
            this.isRunning=false;
            this.startId=0;

        }
        else if (this.isRunning && startId==1){
            this.isRunning = true;
            this.startId = 1;
        }
        else if (!this.isRunning && startId==0){
            this.isRunning = false;
            this.startId=0;

        }
        else{
            mediaSong.stop();
            mediaSong.reset();
            this.isRunning = false;
            this.startId=0;
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        super.onDestroy();
        this.isRunning=false;

        // Tell the user we stopped.
        Toast.makeText(this, "ON Destroy Called", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
