package com.anumalm.spellwork.utilities;

import android.content.Context;
import android.media.MediaPlayer;
import com.anumalm.spellwork.R;


public class MusicManager {
    private static boolean currentlyPlaying = false;
    private static MediaPlayer musicPlayer;
    private static boolean pause = true;

    public static void createMusicPlayer(Context context) {
        Debug.log("MUSIC", "MusicManager/create", "music manager create method", 1);
        if(musicPlayer == null) {
            Debug.log("MUSIC", "MusicManager/create", "music manager was null", 2);
            musicPlayer = MediaPlayer.create(context, R.raw.bgmusic);
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(100, 100);
        }
    }

    public static void playMusic() {
        Debug.log("MUSIC", "MusicManager/play", "trying to play music", 1);
        if(!currentlyPlaying) {
            Debug.log("MUSIC", "MusicManager/play", "music starting to play", 2);
            currentlyPlaying = true;
            musicPlayer.start();
        }
    }

    public static void pauseMusic() {
        Debug.log("MUSIC", "MusicManager/pause", "music pause method", 1);
        if(currentlyPlaying && pause) {
            Debug.log("MUSIC", "MusicManager/pause", "music paused", 2);
            currentlyPlaying = false;
            musicPlayer.pause();
        }
    }

    public static void destroyMusicPlayer() {
        Debug.log("MUSIC", "MusicManager/destroy", "music player destroy method", 1);
        if(musicPlayer != null) {
            Debug.log("MUSIC", "MusicManager/destroy", "music player was not null and will destroy", 2);
            currentlyPlaying = false;
            musicPlayer.stop();
            musicPlayer.release();
        }
    }

    public static boolean isPaused() {
        return pause;
    }

    public static void setPause(boolean pause) {
        MusicManager.pause = pause;
    }
}