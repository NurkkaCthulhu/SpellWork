package com.anumalm.spellwork;

import android.util.Log;

public class Debug {

    private static final int DEBUG_LEVEL = 3;

    public static void log(String tag, String source, String message, int level) {
        if(level <= DEBUG_LEVEL) {
            Log.d(tag, "Problem in " + source + ": " + message);
        }
    }
}
