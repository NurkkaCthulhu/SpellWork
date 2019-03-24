package com.anumalm.spellwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.anumalm.spellwork.utilities.Debug;

public class AutoStart extends BroadcastReceiver
{
    Alarm alarm = new Alarm();
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            alarm.setAlarm(context);
            Debug.log("ALARM", "AutoStart/onReceive", "AutoStart got yeah boi", 2);
        }
    }
}