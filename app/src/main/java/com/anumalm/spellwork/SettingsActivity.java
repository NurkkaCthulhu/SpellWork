package com.anumalm.spellwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anumalm.spellwork.utilities.Debug;
import com.anumalm.spellwork.utilities.MusicManager;
import com.anumalm.spellwork.utilities.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Use to get custom app settings from the user.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.04.22
 * @since       1.0
 */
public class SettingsActivity extends SpellworkActivity {

    private Button startButton;
    private Button stopButton;
    private SharedPreferences settings;
    private TextView alarmStartText;

    /**
     * Overrides AppCompatActivity's onCreate-method.
     *
     * Sets the layout for the screen, creates default settings and hides system UI.
     *
     * @param savedInstanceState        Bundle that's somewhere in AppCompatActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        startButton = findViewById(R.id.start);
        stopButton = findViewById(R.id.stop);
        alarmStartText = findViewById(R.id.alarmTimeText);

        settings = getSharedPreferences("UserSettings", 0);
        alarmStartText.setText("" + settings.getString("time", "00:00"));

        Debug.log("ALARM", "Settings/onCreate", "is alarmOn?" + settings.getAll(), 1);

        if(settings.getBoolean("alarmOn", true)) {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        } else {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    /**
     * Called when alarm start or stop button is clicked.
     *
     * Sends the start or stop command to AlarmService.
     *
     * @param v                         The click source.
     */
    public void manageAlarm(View v) {
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");


        SharedPreferences.Editor editor = settings.edit();
        if (v.getId() == R.id.start) {
            Utils.playButtonSound();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            String time = "Alarm started at " + dateFormat.format(currentTime);
            editor.putBoolean("alarmOn", true);
            editor.putString("time", time);
            editor.apply();
            alarmStartText.setText("" + settings.getString("time", "00:00"));
            Debug.log("ALARM", "MainActivity/manageAlarm", "start button pressed", 2);
            Intent i = new Intent(this, AlarmService.class);
            startService(i);
        } else if (v.getId() == R.id.stop) {
            Utils.playButtonSound();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            String time = "Alarm stopped at " + dateFormat.format(currentTime);
            editor.putBoolean("alarmOn", false);
            editor.putString("time", time);
            editor.apply();
            alarmStartText.setText("" + settings.getString("time", "00:00"));
            Debug.log("ALARM", "MainActivity/manageAlarm", "stop button pressed", 2);
            Intent i = new Intent(this, AlarmService.class);
            stopService(i);
        }
    }

    /**
     * Called when back button has been clicked.
     *
     * Return the user to MainActivity (-> finishes this Activity).
     *
     * @param v                 The click source.
     */
    public void backButton(View v) {
        Utils.playButtonSound();
        MusicManager.setPause(false);
        finish();
    }

}
