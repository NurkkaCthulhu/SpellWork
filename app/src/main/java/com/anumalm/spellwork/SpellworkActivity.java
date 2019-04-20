package com.anumalm.spellwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anumalm.spellwork.utilities.MusicManager;

public abstract class SpellworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MusicManager.destroyMusicPlayer();
    }

}
