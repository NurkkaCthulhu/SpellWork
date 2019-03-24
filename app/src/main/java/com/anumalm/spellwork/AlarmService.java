package com.anumalm.spellwork;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.anumalm.spellwork.utilities.Debug;


public class AlarmService extends Service {

    Alarm alarm = new Alarm();

    public void onCreate()
    {
        super.onCreate();
        Debug.log("ALARM", "AlarmService/onCreate", "Service was started", 1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        alarm.setAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.setAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }


}
