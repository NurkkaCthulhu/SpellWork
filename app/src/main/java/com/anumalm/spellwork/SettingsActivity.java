package com.anumalm.spellwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.anumalm.spellwork.utilities.Utils;

/**
 * Use to get custom app settings from the user.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.04.07
 * @since       1.0
 */
public class SettingsActivity extends AppCompatActivity {

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
        getViewAndHideUI();
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
    public void backButton(View v) {
        finish();
    }
}
