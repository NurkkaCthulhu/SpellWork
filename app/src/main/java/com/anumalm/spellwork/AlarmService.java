package com.anumalm.spellwork;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.anumalm.spellwork.utilities.Debug;

/**
 * Handles sending alarms to the device.
 *
 * Alarms must be started once first from MainActivity via button.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.04.07
 * @since       1.0
 */
public class AlarmService extends Service {

    private Alarm alarm = new Alarm();
    private boolean alarmStarted = false;
    private SharedPreferences settings;

    /**
     * Overrides Service's onCreate-method.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Debug.log("ALARM", "AlarmService/onCreate", "Service was started", 1);
        settings = getSharedPreferences("UserSettings", 0);
    }

    /**
     * Overrides Service's onStartCommand-method.
     *
     * Checks if you want to stop or start the alarms and calls the alarm method in question.
     *
     * @param intent        The Intent supplied to Context.startService(Intent), as given.
     * @param flags         Additional data about this start request.
     * @param startId       A unique integer representing this specific request to start.
     * @return              The return value indicates what semantics the system should use for the service's current started state.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!alarmStarted) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("alarmOn", true);
            editor.apply();
            alarmStarted = true;
            alarm.setAlarm(this);
        }

        return START_STICKY;
    }

    /**
     * Overrides Service's onBind-method.
     *
     * @param intent        The Intent that was used to bind to this service, as given to Context.bindService.
     * @return              Return an IBinder through which clients can call on to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Debug.log("ALARM", "AlarmService/onCreate", "Service was stopped", 1);

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("alarmOn", false);
        editor.apply();
        alarm.cancelAlarm(this);

    }

}
