package com.anumalm.spellwork;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;

public class MusicManager {
    private static boolean currentlyPlaying = false;
    private static MediaPlayer musicPlayer;

    public static void createMusicPlayer(Context context) {
        musicPlayer = MediaPlayer.create(context, R.raw.bgmusic);
        musicPlayer.setLooping(true);
        musicPlayer.setVolume(100, 100);
    }

    public static void playMusic() {
        currentlyPlaying = true;
        musicPlayer.start();
    }

    public static void pauseMusic() {
        musicPlayer.pause();
    }

    public static void stopMusic() {
        musicPlayer.stop();
        musicPlayer.release();
    }


}