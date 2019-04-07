package com.anumalm.spellwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.anumalm.spellwork.utilities.Debug;
import com.anumalm.spellwork.utilities.Utils;

/**
 * Main Activity of the app.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        getViewAndHideUI();
        createDefaultSettings();
    }

    /**
     * Overrides AppCompatActivity's onWindowFocusChanged-method.
     *
     * Hides system UI if the app has focus.
     *
     * @param hasFocus                  Whether the window now has focus.
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getViewAndHideUI();
        }
    }

    /**
     * Gets the current view and hides the system UI with method from Utils.
     */
    private void getViewAndHideUI() {
        View decorView = getWindow().getDecorView();
        Utils.hideSystemUI(decorView);
    }

    /**
     * Creates some default settings for the app.
     */
    private void createDefaultSettings() {
        SharedPreferences settings = getSharedPreferences("UserSettings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("TestOne", "This is first test");
        editor.putString("TestTwo", "This is second test");
        editor.commit();
    }

    /**
     * Called when workout button is clicked.
     *
     * Starts the WorkoutActivity.
     *
     * @param v                         The click source.
     */
    public void workoutButton(View v) {
        Intent i = new Intent(this, WorkoutActivity.class);
        startActivity(i);
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
            Debug.log("ALARM", "MainActivity/manageAlarm", "start button pressed", 2);
            Intent i = new Intent(this, AlarmService.class);
            startService(i);
        } else if (v.getId() == R.id.stop) {
            Debug.log("ALARM", "MainActivity/manageAlarm", "stop button pressed", 2);
            Intent i = new Intent(this, AlarmService.class);
            stopService(i);
        }

    }
}
