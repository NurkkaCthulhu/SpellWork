package com.anumalm.spellwork;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.anumalm.spellwork.utilities.Utils;

/**
 * Screen that shows the current workouts for the user.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public class WorkoutActivity extends AppCompatActivity {
    TextView t;

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
        getViewAndHideUI();

        t = findViewById(R.id.workoutText);
        getSettings();

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
     * Gets the settings from SharedPreferences and sets them to TextView.
     *
     * Currently only has code to test that it works.
     */
    private void getSettings() {
        SharedPreferences settings = getSharedPreferences("UserSettings", 0);
        String putThisToText = settings.getString("TestOne", "").toString();
        putThisToText += settings.getString("TestTwo", "").toString();
        t.setText(putThisToText);
    }

    /**
     * Gets the current view and hides the system UI with method from Utils.
     */
    private void getViewAndHideUI() {
        View decorView = getWindow().getDecorView();
        Utils.hideSystemUI(decorView);
    }

    /**
     * Called when back button has been clicked.
     *
     * Return the user to MainActivity (-> finishes this Activity).
     *
     * @param v                 The click source.
     */
    public void backToMainActivity(View v) {
        finish();
    }
}
