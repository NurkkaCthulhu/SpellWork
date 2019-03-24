package com.anumalm.spellwork;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.anumalm.spellwork.utilities.Debug;

/**
 * Handles sending alarms to the device.
 *
 * Alarms must be started once first from MainActivity via button.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public class AlarmService extends Service {

    Alarm alarm = new Alarm();

    /**
     * Overrides Service's onCreate-method.
     * Mandatory.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Debug.log("ALARM", "AlarmService/onCreate", "Service was started", 1);
    }

    /**
     * Overrides Service's onStartCommand-method.
     *
     * Starts the alarms when service has started.
     *
     * @param intent        The Intent supplied to Context.startService(Intent), as given.
     * @param flags         Additional data about this start request.
     * @param startId       A unique integer representing this specific request to start.
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.setAlarm(this);
        return START_STICKY;
    }

    /**
     * Overrides Service's onBind-method.
     *
     * @param intent        The Intent that was used to bind to this service, as given to Context.bindService.
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
