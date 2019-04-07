package com.anumalm.spellwork;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.anumalm.spellwork.utilities.Debug;


/**
 * Alarm sends the notifications every x minutes to the device.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
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

        showNotification(context, intent);

        wl.release();
    }

    /**
     * Called to starts alarms.
     *
     * @param context           The Context that called this method.
     */
    public void setAlarm(Context context) {
        Debug.log("ALARM", "Alarm/setAlarm", "Setting alarm...", 1);

        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 1, pi); // Millisec * Second * Minute
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
     * Has custom sound and vibrates the phone when triggered.
     *
     * @param context           The Context in which the receiver is running.
     */
    private void showNotification(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New workout!")
                .setContentText("Time to get up u lazy lad");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }


}
