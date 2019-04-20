package com.anumalm.spellwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.anumalm.spellwork.utilities.MusicManager;
import com.anumalm.spellwork.utilities.Utils;

public abstract class SpellworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create music player
        MusicManager.createMusicPlayer(this);
        MusicManager.playMusic();
        MusicManager.setPause(true);

        getViewAndHideUI();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
            MusicManager.playMusic();
            MusicManager.setPause(true);
            getViewAndHideUI();
        } else {
            MusicManager.pauseMusic();
        }
    }

    /**
     * Gets the current view and hides the system UI with method from Utils.
     */
    public void getViewAndHideUI() {
        View decorView = getWindow().getDecorView();
        Utils.hideSystemUI(decorView);
    }

    @Override
    public void onPause() {
        super.onPause();
        MusicManager.pauseMusic();
    }

    @Override
    public void onResume() {
        super.onResume();
        MusicManager.playMusic();
    }

}
