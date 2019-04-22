package com.anumalm.spellwork.utilities;

import android.content.Context;
import android.media.MediaPlayer;
import com.anumalm.spellwork.R;

/**
 * MusicManager plays the background music in the app.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.04.22
 * @since       3.0
 */
public class MusicManager {
    private static boolean currentlyPlaying = false;
    private static MediaPlayer musicPlayer;
    private static boolean pause = true;

    /**
     * Creates the MediaPlayer that plays music.
     *
     * @param context           Context that's used to creating the MediaPlayer.
     */
    public static void createMusicPlayer(Context context) {
        //Debug.log("MUSIC", "MusicManager/create", "music manager create method", 1);
        if(musicPlayer == null) {
            //Debug.log("MUSIC", "MusicManager/create", "music manager was null", 2);
            musicPlayer = MediaPlayer.create(context, R.raw.bgmusic);
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(100, 100);
        }
    }

    /**
     * Called when you want to start playing the background music.
     */
    public static void playMusic() {
        //Debug.log("MUSIC", "MusicManager/play", "trying to play music", 1);
        if(!currentlyPlaying) {
            //Debug.log("MUSIC", "MusicManager/play", "music starting to play", 2);
            currentlyPlaying = true;
            musicPlayer.start();
        }
    }

    /**
     * Called when you want to pause the music, for example when app is in the background.
     */
    public static void pauseMusic() {
        //Debug.log("MUSIC", "MusicManager/pause", "music pause method", 1);
        if(currentlyPlaying && pause) {
            //Debug.log("MUSIC", "MusicManager/pause", "music paused", 2);
            currentlyPlaying = false;
            musicPlayer.pause();
        }
    }

    public static void destroyMusicPlayer() {
        //Debug.log("MUSIC", "MusicManager/destroy", "music player destroy method", 1);
        if(musicPlayer != null) {
            //Debug.log("MUSIC", "MusicManager/destroy", "music player was not null and will destroy", 2);
            currentlyPlaying = false;
            musicPlayer.stop();
            musicPlayer.release();
        }
    }

    /**
     * Getter for checking if the music can be paused.
     *
     * @return      is music pauseable
     */
    public static boolean isPaused() {
        return pause;
    }

    /**
     * Sets the pauseable status of the music.
     *
     * Used to prevent music pausing when moving from Activity to Activity.
     *
     * @param pause         should the music be paused if pause method is called
     */
    public static void setPause(boolean pause) {
        MusicManager.pause = pause;
    }
}