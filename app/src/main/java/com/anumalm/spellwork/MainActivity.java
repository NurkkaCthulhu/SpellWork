package com.anumalm.spellwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    private TextView playerCurrency;
    private SharedPreferences settings;
    private ImageView letterImg;

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
        hoccuTexts = getResources().getStringArray(R.array.greetings);
        greeting = findViewById(R.id.hoccutext);
        playerCurrency = findViewById(R.id.playerCurrencyText);
        letterImg = findViewById(R.id.letterImg);
        settings = getSharedPreferences("UserSettings", 0);

        getNewGreeting();
        updateCurrency();
        createDefaultSettings();
    }

    /**
     * onStart method from AppCompatApplication.
     *
     * Gives user a new greeting every time used comes to the MainActivity screen.
     */
    @Override
    public void onStart() {
        super.onStart();
        getNewGreeting();
        updateCurrency();
    }

    /**
     * Method for getting a random greeting from strings-resource array.
     */
    private void getNewGreeting() {
        int randomIndex = new Random().nextInt(hoccuTexts.length);
        String randomName = hoccuTexts[randomIndex];
        greeting.setText(randomName);
    }

    /**
     * Updates the current currency user has to TextView.
     */
    private void updateCurrency() {
        int currency = settings.getInt("currency", -1);
        playerCurrency.setText("" + currency);
    }

    /**
     * Creates some default settings for the app.
     */
    private void createDefaultSettings() {
        boolean settingsInit = settings.getBoolean("initted", false);
        Debug.log("ALARM", "MainActivity/createDefaultSettings", "All settings: " + settings.getAll(), 1);

        if (settingsInit) {
            letterImg.setVisibility(View.VISIBLE);
            greeting.setVisibility(View.INVISIBLE);
            Debug.log("ALARM", "MainActivity/createDefaultSettings", "No settings found, creating default ones", 2);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("alarmOn", false);
            editor.putInt("currency", 0);
            editor.putBoolean("allowWorkout", false);
            editor.putBoolean("initted", true);
            editor.apply();
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

    public void letterButton(View v) {
        Utils.playButtonSound();
        if(letterImg.getVisibility() == View.INVISIBLE) {
            letterImg.setVisibility(View.VISIBLE);
            greeting.setVisibility(View.INVISIBLE);
        } else {
            letterImg.setVisibility(View.INVISIBLE);
            greeting.setVisibility(View.VISIBLE);
        }
    }

}
