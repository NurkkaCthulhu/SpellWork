package com.anumalm.spellwork;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple splash activity to show app title.
 */
public class SplashActivity extends SpellworkActivity {

    /**
     * Overrides AppCompatActivity's onCreate method.
     *
     * @param savedInstanceState            Bundle that's somewhere in AppCompatActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Set timer for 2 sec so user can see the app's name.
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 2000);
    }
}
