package com.anumalm.spellwork;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import com.anumalm.spellwork.utilities.Debug;

/**
 * Alarm sends the notifications every x minutes to the device.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.04.22
 * @since       1.0
 */
public class Alarm extends BroadcastReceiver {

    /**
     * Overrides BroadcastReceiver's onReceive-method.
     *
     * Calls the sendNotification-method after the set time has passed.
     *
     * @param context           The Context in which the receiver is running.
     * @param intent            The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":mywakelocktag");
        wl.acquire(1000);

        Debug.log("ALARM", "Alarm/onReceive", "Time to send notification!", 1);

        showNotification(context);
        allowUserToWorkout(context);

        wl.release();
    }

    /**
     * Allows user to check a workout done after notification has been sent.
     *
     * @param context           The Context that we get SharedPreferences from.
     */
    private void allowUserToWorkout(Context context) {
        SharedPreferences settings = context.getSharedPreferences("UserSettings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("allowWorkout", true);
        editor.apply();
        Debug.log("WORKOUT", "Alarm/allowUserToWorkout", "alls ettinfdi" + settings.getAll(), 1);
    }

    /**
     * Called to starts alarms.
     *
     * @param context           The Context that called this method.
     */
    public void setAlarm(Context context) {
        int timerMinutes = 20;
        Debug.log("ALARM", "Alarm/setAlarm", "Setting alarm...", 1);

        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 1000 * 60 * timerMinutes,
                1000 * 60 * timerMinutes, pi);
    }

    /**
     * Called to stop alarms.
     *
     * @param context           The Context that called this method.
     */
    public void cancelAlarm(Context context) {
        Debug.log("ALARM", "Alarm/cancelAlarm", "Canceling alarm...", 1);

        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    /**
     * Method for showing a notification.
     *
     * Has custom sound and vibrates the phone when triggered on Androids before Oreo.
     *
     * @param context           The Context in which the receiver is running.
     */
    private void showNotification(Context context) {
        String channelId = "my_channel_01";
        String channelName = "Spellword alarms";

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Hoccu calls")
                        .setContentText("Time to get up nyapprentice!");

        mBuilder.setContentIntent(contentIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Add notificationChannel if Android version is Oreo or newer.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(mChannel);
        } else {
            // Set vibration to notification
            mBuilder.setVibrate(new long[] {1000,1000});
            // Set alarm sound to an annoying chirp sound
            Uri alarmSound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.alarm);
            mBuilder.setSound(alarmSound);
            mBuilder.setAutoCancel(true);
        }

        mNotificationManager.notify(1, mBuilder.build());

    }


}
