package com.anumalm.spellwork;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

        Workout wo = new JumpingJacks(10, true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        TextView top = findViewById(R.id.topTime);
        LocalTime current = java.time.LocalTime.now();
        String timeText = formatter.format(current);
        top.setText(timeText);
        ImageView iw = findViewById(R.id.topWorkoutImg);
        iw.setImageResource(((JumpingJacks) wo).getGraphic());

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
