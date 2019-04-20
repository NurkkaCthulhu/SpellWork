package com.anumalm.spellwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.anumalm.spellwork.utilities.Debug;
import com.anumalm.spellwork.utilities.MusicManager;
import com.anumalm.spellwork.utilities.Utils;

import java.util.Random;

/**
 * Main Activity of the app.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.04.07
 * @since       1.0
 */
public class MainActivity extends SpellworkActivity {

    private String[] hoccuTexts;
    private TextView greeting;

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
        Utils.createButtonPlayer(this);
        createDefaultSettings();
        hoccuTexts = getResources().getStringArray(R.array.greetings);
        greeting = findViewById(R.id.hoccutext);


        int randomIndex = new Random().nextInt(hoccuTexts.length);
        String randomName = hoccuTexts[randomIndex];
        greeting.setText(randomName);
    }

    /**
     * Creates some default settings for the app.
     */
    private void createDefaultSettings() {
        SharedPreferences settings = getSharedPreferences("UserSettings", 0);
        boolean settingsInit = settings.getBoolean("initted", false);

        if (!settingsInit) {
            Debug.log("ALARM", "MainActivity/createDefaultSettings", "No settings found, creating default ones", 2);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("TestOne", "This is test");
            editor.putString("TestTwo", "This test");
            editor.putBoolean("initted", true);
            editor.commit();
        }

    }

    /**
     * Called when workout button is clicked.
     *
     * Starts the WorkoutActivity.
     *
     * @param v                         The click source.
     */
    public void workoutButton(View v) {
        Utils.playButtonSound();
        MusicManager.setPause(false);
        Intent i = new Intent(this, WorkoutActivity.class);
        startActivity(i);
    }

    /**
     * Called when settings button is clicked.
     *
     * Starts the SettingsActivity.
     *
     * @param v                         The click source.
     */
    public void settingsButton(View v) {
        Utils.playButtonSound();
        MusicManager.setPause(false);
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

}
