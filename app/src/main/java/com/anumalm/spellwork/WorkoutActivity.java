package com.anumalm.spellwork;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anumalm.spellwork.utilities.Debug;
import com.anumalm.spellwork.utilities.MusicManager;
import com.anumalm.spellwork.utilities.Utils;
import com.anumalm.spellwork.workouts.Workout;
import com.anumalm.spellwork.workouts.JumpingJacks;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Screen that shows the current workouts for the user.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public class WorkoutActivity extends SpellworkActivity {

    /**
     * Overrides AppCompatActivity's onCreate-method.
     *
     * Sets the layout for the screen, hides system UI, gets the TextView and settings.
     *
     * @param savedInstanceState        Bundle that's somewhere in AppCompatActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        RelativeLayout top = findViewById(R.id.workoutBox_top);
        RelativeLayout middle = findViewById(R.id.workoutBox_middle);
        RelativeLayout bottom = findViewById(R.id.workoutBox_bottom);

        top.setOnClickListener(v -> {
            Debug.log("WORKOUT", "WorkoutActivity/onCreate", "top clicked", 1);
            increaseCurrency();
        });
        middle.setOnClickListener(v -> {
            Debug.log("WORKOUT", "WorkoutActivity/onCreate", "middle clicked", 1);
            increaseCurrency();
        });
        bottom.setOnClickListener(v -> {
            Debug.log("WORKOUT", "WorkoutActivity/onCreate", "bottom clicked", 1);
            increaseCurrency();
        });

    }

    /**
     * Increase player's currency and save it to settings.
     */
    private void increaseCurrency() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences("UserSettings", 0);
        boolean allowWorkout = settings.getBoolean("allowWorkout", false);
        Debug.log("WORKOUT", "WorkoutActivity/increaseCurrency", "allowWorkout? " + settings.getAll(), 1);
        if(allowWorkout) {
            SharedPreferences.Editor editor = settings.edit();
            int currentCurrency = settings.getInt("currency", -1);
            currentCurrency += 5;
            editor.putInt("currency", currentCurrency);
            editor.putBoolean("allowWorkout", false);
            editor.apply();
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
