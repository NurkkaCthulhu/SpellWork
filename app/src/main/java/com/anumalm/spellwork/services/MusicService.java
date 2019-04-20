package com.anumalm.spellwork.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.anumalm.spellwork.R;

public class MusicService extends Service {

    private MediaPlayer mp;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mp = MediaPlayer.create(this, R.raw.bgmusic);
        mp.setLooping(true);
        mp.setVolume(100, 100);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mp.stop();
        mp.release();
    }
}
