package com.anumalm.spellwork;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        getViewAndHideUI();

        t = findViewById(R.id.workoutText);
        getSettings();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getViewAndHideUI();
        }
    }

    private void getSettings() {
        SharedPreferences settings = getSharedPreferences("UserSettings", 0);
        String putThisToText = settings.getString("TestOne", "").toString();
        putThisToText += settings.getString("TestTwo", "").toString();
        t.setText(putThisToText);
    }

    private void getViewAndHideUI() {
        View decorView = getWindow().getDecorView();
        Utils.hideSystemUI(decorView);
    }


}
