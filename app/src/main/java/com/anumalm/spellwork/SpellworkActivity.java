package com.anumalm.spellwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.anumalm.spellwork.utilities.MusicManager;
import com.anumalm.spellwork.utilities.Utils;

/**
 * Activity that all other activities extend.
 *
 * Use to minimize copied code in lifetime methods.
 *
 *  * @author      Anu Malm     anu.malm@tuni.fi
 *  * @version     2019.04.22
 *  * @since       3.0
 */
public abstract class SpellworkActivity extends AppCompatActivity {

    /**
     * AppCompatActivity's onCreate method.
     *
     * Creates MusicManager's MediaPlayer, starts playing music and sets pauseable to true.
     * MusicManager handles checks whether the player needs to be created or if music should
     * start playing again etc.
     *
     * @param savedInstanceState            Bundle that's somewhere in AppCompatActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create music player
        MusicManager.createMusicPlayer(this);
        MusicManager.playMusic();
        MusicManager.setPause(true);

        getViewAndHideUI();
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

    /**
     * Called when the activity is on pause.
     *
     * Calls MusicManager's pauseMusic-method so music does not play on the background.
     */
    @Override
    public void onPause() {
        super.onPause();
        MusicManager.pauseMusic();
    }

    /**
     * Called when activity is resumed.
     *
     * Calls MusicMAnager's playMusic-method so music starts to play again.
     */
    @Override
    public void onResume() {
        super.onResume();
        MusicManager.playMusic();
    }

}
