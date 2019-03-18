package com.anumalm.spellwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViewAndHideUI();
        createDefaultSettings();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getViewAndHideUI();
        }
    }

    private void getViewAndHideUI() {
        View decorView = getWindow().getDecorView();
        Utils.hideSystemUI(decorView);
    }

    private void createDefaultSettings() {
        SharedPreferences settings = getSharedPreferences("UserSettings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("TestOne", "This is first test");
        editor.putString("TestTwo", "This is second test");
        editor.commit();
    }

    public void workoutButton(View v) {
        Intent i = new Intent(this, WorkoutActivity.class);
        startActivity(i);
    }
}
