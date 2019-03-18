package com.anumalm.spellwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        View decorView = getWindow().getDecorView();
        Utils.hideSystemUI(decorView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            Utils.hideSystemUI(decorView);
        }
    }

}
