package com.anumalm.spellwork.utilities;

import android.content.Context;
import android.media.MediaPlayer;
import com.anumalm.spellwork.R;


public class MusicManager {
    private static boolean currentlyPlaying = false;
    private static MediaPlayer musicPlayer;

    public static void createMusicPlayer(Context context) {
        if(musicPlayer == null) {
            musicPlayer = MediaPlayer.create(context, R.raw.bgmusic);
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(100, 100);
        }
    }

    public static void playMusic() {
        if(!currentlyPlaying) {
            currentlyPlaying = true;
            musicPlayer.start();
        }
    }

    public static void pauseMusic() {
        if(currentlyPlaying) {
            musicPlayer.pause();
        }
    }

    public static void destroyMusicPlayer() {
        if(musicPlayer != null) {
            currentlyPlaying = false;
            musicPlayer.stop();
            musicPlayer.release();
        }
    }
}