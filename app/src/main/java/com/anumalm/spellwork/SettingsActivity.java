package com.anumalm.spellwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.anumalm.spellwork.utilities.Debug;
import com.anumalm.spellwork.utilities.MusicManager;
import com.anumalm.spellwork.utilities.Utils;

/**
 * Use to get custom app settings from the user.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.04.07
 * @since       1.0
 */
public class SettingsActivity extends SpellworkActivity {

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
    }

    /**
     * Called when alarm start or stop button is clicked.
     *
     * Sends the start or stop command to AlarmService.
     *
     * @param v                         The click source.
     */
    public void manageAlarm(View v) {
        if (v.getId() == R.id.start) {
            Utils.playButtonSound();
            Debug.log("ALARM", "MainActivity/manageAlarm", "start button pressed", 2);
            Intent i = new Intent(this, AlarmService.class);
            startService(i);
        } else if (v.getId() == R.id.stop) {
            Utils.playButtonSound();
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
