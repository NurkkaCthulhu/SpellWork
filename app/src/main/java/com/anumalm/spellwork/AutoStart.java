package com.anumalm.spellwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.anumalm.spellwork.utilities.Debug;

/**
 * Starts alarms when device starts.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public class AutoStart extends BroadcastReceiver {
    Alarm alarm = new Alarm();

    /**
     * Overrides BroadcastReceiver's onReceive-method.
     *
     * Called when the device is started. Starts alarms.
     *
     * @param context               The Context in which the receiver is running.
     * @param intent                The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            alarm.setAlarm(context);
            Debug.log("ALARM", "AutoStart/onReceive", "AutoStart got yeah boi", 2);
        }
    }
}